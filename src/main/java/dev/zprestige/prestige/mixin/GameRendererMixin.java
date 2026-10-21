package dev.zprestige.prestige.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.FloatEvent4;
import dev.zprestige.prestige.event.IntEvent;
import dev.zprestige.prestige.event.TickEvent20;
import dev.zprestige.prestige.render.GlState;
import dev.zprestige.prestige.util.ClientUtil;
import java.util.List;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profilers;
import org.joml.Matrix4f;

@CTransformer({GameRenderer.class})
public class GameRendererMixin {
    @CShadow("client")
    public MinecraftClient client;
    @CShadow("guiRenderer")
    private GuiRenderer guiRenderer;

    // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
    // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
    @CInject(
        method = {"render"},
        target = {@CTarget("TAIL")}
    )
    public void a(RenderTickCounter tickCounter, boolean tick) {
        List<Runnable> delayedActions = ClientUtil.bp();
        boolean renderWork = Prestige.eventBus != null && ClientUtil.bq();
        if (!renderWork && delayedActions.isEmpty()) {
            ClientUtil.br();
            ClientUtil.bs();
        } else {
            GlState cap = ClientUtil.bt();
            boolean var11 = false /* VF: Semaphore variable */;

            label53: {
                try {
                    var11 = true;
                    ClientUtil.bu(delayedActions);
                    if (!renderWork) {
                        ClientUtil.bv();
                        ClientUtil.bw();
                        var11 = false;
                        break label53;
                    }

                    Window window = this.client.getWindow();
                    Matrix4f proj = ClientUtil.bB(
                        ClientUtil.bA(ClientUtil.bz(ClientUtil.by(this.guiRenderer))),
                        (float)window.getFramebufferWidth() / window.getScaleFactor(),
                        (float)window.getFramebufferHeight() / window.getScaleFactor()
                    );
                    MatrixCameraData context = ClientUtil.bD(proj, ClientUtil.bC().setTranslation(0.0F, 0.0F, -10000.0F), null);
                    ClientUtil.bE(context);
                    var11 = false;
                } finally {
                    if (var11) {
                        ClientUtil.bG(cap);
                    }
                }

                ClientUtil.bF(cap);
                return;
            }

            ClientUtil.bx(cap);
        }
    }

    @CInject(
        target = {@CTarget("HEAD")},
        method = {"renderWorld"}
    )
    private void b(RenderTickCounter tickCounter) {
        if (ClientUtil.bH()) {
            Camera camera = this.client.gameRenderer.getCamera();
            MatrixStack matrixStack = ClientUtil.bI();
            RenderSystem.getModelViewStack().pushMatrix().mul(matrixStack.peek().getPositionMatrix());
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0F));
            dev.zprestige.prestige.render.MatrixStack.e.set(RenderSystem.getModelViewMatrix());
            dev.zprestige.prestige.render.MatrixStack.f.set(matrixStack.peek().getPositionMatrix());
            RenderSystem.getModelViewStack().popMatrix();
        }
    }

    @CInject(
        target = {@CTarget("HEAD")},
        method = {"tiltViewWhenHurt"},
        cancellable = true
    )
    private void c(MatrixStack matrices, float tickDelta, InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.bJ(Prestige.eventBus, TickEvent20.class)) {
            TickEvent20 event = ClientUtil.bK();
            if (ClientUtil.bL(event)) {
                ci.cancel();
            }
        }
    }

    @CInject(
        target = {@CTarget("HEAD")},
        method = {"tick"}
    )
    private void d() {
        if (Prestige.eventBus != null && ClientUtil.bM(Prestige.eventBus, IntEvent.class)) {
            IntEvent event = ClientUtil.bN(0);
            ClientUtil.bO(event);

            for (int i = 0; i < ClientUtil.bP(event); i++) {
                ClientUtil.bR(ClientUtil.bQ((GameRenderer)(Object)this)).tickFloatingItemTimer();
            }
        }
    }

    @CInject(
        method = {"updateCrosshairTarget"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void e(float tickDelta, InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.bS(Prestige.eventBus, FloatEvent4.class)) {
            Entity entity2 = this.client.getCameraEntity();
            if (entity2 != null) {
                if (this.client.world != null) {
                    Profilers.get().push("pick");
                    this.client.targetedEntity = null;
                    double d = ClientUtil.bT();
                    FloatEvent4 reachEvent = ClientUtil.bU(0.0F);
                    boolean call = ClientUtil.bV(reachEvent);
                    if (call) {
                        d += ClientUtil.bW(reachEvent);
                    }

                    this.client.crosshairTarget = entity2.raycast(d, tickDelta, false);
                    Vec3d vec3d = entity2.getCameraPosVec(tickDelta);
                    boolean bl = false;
                    double e = d;
                    if (this.client.player.isCreative()) {
                        e = 6.0;
                        d = 6.0;
                    } else {
                        if (d > 3.0 && !call) {
                            bl = true;
                        }

                        d = d;
                    }

                    e *= e;
                    if (this.client.crosshairTarget != null) {
                        e = this.client.crosshairTarget.getPos().squaredDistanceTo(vec3d);
                    }

                    Vec3d vec3d2 = entity2.getRotationVec(1.0F);
                    Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
                    Box box = entity2.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
                    EntityHitResult entityHitResult = ProjectileUtil.raycast(entity2, vec3d, vec3d3, box, ClientUtil.bX(), e);
                    if (entityHitResult != null) {
                        Entity entity22 = entityHitResult.getEntity();
                        Vec3d vec3d4 = entityHitResult.getPos();
                        double g = vec3d.squaredDistanceTo(vec3d4);
                        if (bl && g > 9.0) {
                            this.client.crosshairTarget = BlockHitResult.createMissed(
                                vec3d4, Direction.getFacing(vec3d2.x, vec3d2.y, vec3d2.z), BlockPos.ofFloored(vec3d4)
                            );
                        } else if (g < e || this.client.crosshairTarget == null) {
                            this.client.crosshairTarget = entityHitResult;
                            if (entity22 instanceof LivingEntity || entity22 instanceof ItemFrameEntity) {
                                this.client.targetedEntity = entity22;
                            }
                        }
                    }

                    Profilers.get().pop();
                    ci.cancel();
                }
            }
        }
    }
}
