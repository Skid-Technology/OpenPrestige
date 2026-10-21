package dev.zprestige.prestige.engine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import net.fabricmc.loader.api.FabricLoader;
import net.lenni0451.classtransform.TransformerManager;
import net.lenni0451.classtransform.utils.FailStrategy;
import net.lenni0451.classtransform.utils.tree.IClassProvider;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/**
 * Applies the ClassTransform transformers of the Prestige client (the original native loader installed them through an agent)
 * to the Minecraft classes, from inside Sponge Mixin: for every target class one anchor mixin (dev.zprestige.prestige.engine.mixin.N../I..)
 * makes Mixin call {@link #preApply}, which runs the ClassTransform transformation on the live Mixin ClassNode.
 *
 * Namespaces: the transformer classes reference Minecraft by Yarn names in strings (annotation values) and by the names of the
 * jar variant in bytecode. In the Gradle dev client the runtime names are Yarn ("named": the mapper is void), in a production
 * install they are Intermediary (the bundled Yarn tiny maps the Yarn strings to Intermediary).
 */
public final class EnginePlugin implements IMixinConfigPlugin {
    private static final String HOOKS = "Lnet/lenni0451/classtransform/annotations/injection/";
    private TransformerManager manager;
    private String namespace = "named";
    private int expectedTargets;

    @Override public void onLoad(String mixinPackage) {
        try {
            String forced = System.getProperty("prestige.engine.namespace");   // test override ("named" / "intermediary")
            if (forced != null) namespace = forced;
            else try { namespace = FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace(); } catch (Throwable t) { namespace = "named"; }
            boolean named = namespace.equals("named");
            ClassLoader loader = EnginePlugin.class.getClassLoader();
            byte[] tiny = named ? null : resource("prestige-engine/mappings.tiny");
            List<String> transformers = lines("prestige-engine/transformers.list");
            expectedTargets = lines(named ? "prestige-engine/targets.named.list" : "prestige-engine/targets.intermediary.list").size();
            manager = new TransformerManager(new ResourceClassProvider(loader, new HashSet<>(transformers)), tiny == null ? null : new ByteArrayInputStream(tiny));
            manager.setFailStrategy(FailStrategy.CANCEL);
            for (String name : transformers) manager.addTransformer(name);
            if (manager.getTransformedClasses().size() != expectedTargets)
                throw new IllegalStateException("Expected " + expectedTargets + " Minecraft targets, got " + manager.getTransformedClasses().size());
            EngineState.installed = true;
            System.out.println("[Engine] namespace=" + namespace + " REGISTERED=" + transformers.size() + " TARGETS=" + expectedTargets);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to prepare the Prestige game hooks", e);
        }
    }

    /** Class bytes from the mod class path; the callbacks (void CInject/CModifyArgs handlers) get the readiness gate on the way. */
    private static final class ResourceClassProvider implements IClassProvider {
        private final ClassLoader loader;
        private final Set<String> transformers;
        ResourceClassProvider(ClassLoader loader, Set<String> transformers) { this.loader = loader; this.transformers = transformers; }

        @Override public byte[] getClass(String name) throws ClassNotFoundException {
            String path = name.replace('.', '/') + ".class";
            try (InputStream in = loader.getResourceAsStream(path)) {
                if (in == null) throw new ClassNotFoundException(name + " (missing resource " + path + ")");
                byte[] bytes = in.readAllBytes();
                return transformers.contains(name.replace('/', '.')) ? gate(bytes) : bytes;
            } catch (IOException e) {
                throw new ClassNotFoundException(name, e);
            }
        }

        @Override public Map<String, Supplier<byte[]>> getAllClasses() {
            throw new UnsupportedOperationException("Wildcard class enumeration is not used by the engine");
        }

        /** Prepends "if (!EngineState.ready) return; EngineState.onCallback(id);" to every void callback. */
        private static byte[] gate(byte[] bytes) {
            ClassNode node = new ClassNode();
            new ClassReader(bytes).accept(node, 0);
            int count = 0;
            for (MethodNode method : node.methods) {
                if (!hasHook(method)) continue;
                if (Type.getReturnType(method.desc).getSort() != Type.VOID) {
                    System.out.println("[Engine] note: non-void callback not gated: " + node.name + "." + method.name);
                    continue;
                }
                InsnList gate = new InsnList();
                LabelNode run = new LabelNode();
                gate.add(new FieldInsnNode(Opcodes.GETSTATIC, "dev/zprestige/prestige/engine/EngineState", "ready", "Z"));
                gate.add(new JumpInsnNode(Opcodes.IFNE, run));
                gate.add(new InsnNode(Opcodes.RETURN));
                gate.add(run);
                gate.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                gate.add(new LdcInsnNode(node.name.substring(node.name.lastIndexOf('/') + 1) + "." + method.name));
                gate.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "dev/zprestige/prestige/engine/EngineState", "onCallback", "(Ljava/lang/String;)V", false));
                method.instructions.insert(gate);
                count++;
            }
            if (count == 0) return bytes;
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            node.accept(writer);
            return writer.toByteArray();
        }

        private static boolean hasHook(MethodNode m) {
            return hasHook(m.visibleAnnotations) || hasHook(m.invisibleAnnotations);
        }

        private static boolean hasHook(List<AnnotationNode> list) {
            if (list == null) return false;
            for (AnnotationNode a : list)
                if (a.desc.equals(HOOKS + "CInject;") || a.desc.equals(HOOKS + "CModifyArgs;")) return true;
            return false;
        }
    }

    private static byte[] resource(String name) throws IOException {
        try (InputStream in = EnginePlugin.class.getClassLoader().getResourceAsStream(name)) {
            if (in == null) throw new IllegalStateException("Missing engine resource: " + name);
            return in.readAllBytes();
        }
    }

    private static List<String> lines(String name) throws IOException {
        return new String(resource(name), StandardCharsets.UTF_8).lines().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }

    @Override public void preApply(String targetName, ClassNode target, String mixin, IMixinInfo info) {
        if (!manager.getTransformedClasses().contains(targetName)) throw new IllegalStateException("Unexpected target: " + targetName);
        if (!EngineState.transformed.add(targetName)) throw new IllegalStateException("Duplicate transformation: " + targetName);

        // ClassTransform parses the bytes it is given; hand it a copy but let its output land in the LIVE Mixin node, so the method
        // nodes Mixin (and MixinExtras) already reference stay valid.
        ClassWriter writer = new ClassWriter(0);
        target.accept(writer);
        ClassNode[] liveTarget = {target};
        manager.addRawTransformer(targetName, (engine, parsed) -> {
            if (liveTarget[0] == null) return parsed;
            if (!parsed.name.equals(liveTarget[0].name)) throw new IllegalStateException("Unexpected target for " + targetName);
            ClassNode live = liveTarget[0];
            liveTarget[0] = null;
            return live;
        });
        try {
            byte[] verified = manager.transform(targetName, writer.toByteArray(), true);
            if (verified == null) throw new IllegalStateException("Transformation failed for " + targetName + "; see ClassTransform diagnostics");
            // the transformed live node needs new max stack/locals: take them from the re-serialised class
            ClassNode calculated = new ClassNode();
            new ClassReader(verified).accept(calculated, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            for (MethodNode method : target.methods) {
                MethodNode sizing = calculated.methods.stream().filter(m -> m.name.equals(method.name) && m.desc.equals(method.desc)).findFirst().orElseThrow();
                method.maxLocals = sizing.maxLocals;
                method.maxStack = sizing.maxStack;
            }
        } finally {
            liveTarget[0] = null;
        }
        System.out.println("[Engine] TRANSFORMED=" + targetName);
    }

    /** The anchor mixins for the runtime namespace (Yarn "N" set in the dev client, Intermediary "I" set in production). */
    @Override public List<String> getMixins() {
        String prefix = namespace.equals("named") ? "N" : "I";
        List<String> out = new ArrayList<>();
        for (int i = 0; i < expectedTargets; i++) out.add(String.format("%s%02d", prefix, i));
        return out;
    }

    @Override public String getRefMapperConfig() { return null; }
    @Override public boolean shouldApplyMixin(String target, String mixin) { return true; }
    @Override public void acceptTargets(Set<String> own, Set<String> other) {}
    @Override public void postApply(String target, ClassNode node, String mixin, IMixinInfo info) {}
}
