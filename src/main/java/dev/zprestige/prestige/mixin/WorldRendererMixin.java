package dev.zprestige.prestige.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent30;
import dev.zprestige.prestige.render.GlState;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.util.ClientUtil;
import java.util.List;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector4f;

@CTransformer({WorldRenderer.class})
public class WorldRendererMixin {
    @CShadow("capturedFrustum")
    private Frustum frustum;

    // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
    // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
    @CInject(
        method = {"render"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/FrameGraphBuilder;run(Lnet/minecraft/client/util/ObjectAllocator;Lnet/minecraft/client/render/FrameGraphBuilder$Profiler;)V",
            shift = CTarget.Shift.AFTER
        )}
    )
    public void a(
        ObjectAllocator allocator,
        RenderTickCounter tickCounter,
        boolean renderBlockOutline,
        Camera camera,
        Matrix4f theCameraRotation,
        Matrix4f actualRealProjMatrix,
        Matrix4f projectionMatrix,
        GpuBufferSlice fogBuffer,
        Vector4f fogColor,
        boolean renderSky
    ) {
        if (Prestige.eventBus != null) {
            List<Runnable> delayedActions = ClientUtil.eA();
            boolean renderWork = ClientUtil.eB();
            boolean hudWork = ClientUtil.eC();
            if (hudWork) {
                MatrixStack.d.set(actualRealProjMatrix);
                if (!renderWork) {
                    ClientUtil.eD();
                }
            }

            if (renderWork || !delayedActions.isEmpty()) {
                GlState cap = ClientUtil.eE();
                boolean var19 = false /* VF: Semaphore variable */;

                label53: {
                    try {
                        var19 = true;
                        ClientUtil.eF(delayedActions);
                        if (!renderWork) {
                            var19 = false;
                            break label53;
                        }

                        Matrix4fStack actualRealModView = RenderSystem.getModelViewStack();
                        MatrixCameraData context = ClientUtil.eI(actualRealProjMatrix, ClientUtil.eH(actualRealModView), camera);
                        ClientUtil.eJ(context);
                        var19 = false;
                    } finally {
                        if (var19) {
                            ClientUtil.eL(cap);
                        }
                    }

                    ClientUtil.eK(cap);
                    return;
                }

                ClientUtil.eG(cap);
            }
        }
    }

    @CInject(
        method = {"setupFrustum"},
        target = {@CTarget("RETURN")}
    )
    private void b(Matrix4f matrix4f, Matrix4f matrix4f2, Vec3d vec3d, InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.eM(Prestige.eventBus, TickEvent22.class)) {
            TickEvent22 event = ClientUtil.eN((Frustum)ci.getReturnValue());
            ClientUtil.eO(event);
        }
    }

    @CInject(
        method = {"reload"},
        target = {@CTarget("HEAD")}
    )
    private void c(InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.eP(Prestige.eventBus, TickEvent30.class)) {
            ClientUtil.eR(ClientUtil.eQ());
        }
    }
}
