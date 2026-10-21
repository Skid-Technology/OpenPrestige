package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.DoubleEvent3;
import dev.zprestige.prestige.event.EntityEvent2;
import dev.zprestige.prestige.event.FloatPositionEvent;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@CTransformer({Entity.class})
public abstract class EntityMixin {
    @CInject(
        method = {"updateVelocity"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(float speed, Vec3d movementInput, InjectionCallback ci) {
        Entity entity = (Entity)(Object)this;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (entity == mc.player && Prestige.o != null && ClientUtil.aA(Prestige.o) != null) {
            FloatPositionEvent event = ClientUtil.aB(entity.getYaw(), movementInput);
            if (ClientUtil.aC(event)) {
                float yaw = ClientUtil.aD(event);
                Vec3d input = ClientUtil.aE(event) != null ? ClientUtil.aF(event) : movementInput;
                double d = input.lengthSquared();
                Vec3d vec3d;
                if (d < 1.0E-7) {
                    vec3d = Vec3d.ZERO;
                } else {
                    Vec3d scaled = (d > 1.0 ? input.normalize() : input).multiply(speed);
                    float f = MathHelper.sin(yaw * (float) (Math.PI / 180.0));
                    float g = MathHelper.cos(yaw * (float) (Math.PI / 180.0));
                    vec3d = ClientUtil.aG(scaled.x * g - scaled.z * f, scaled.y, scaled.z * g + scaled.x * f);
                }

                entity.setVelocity(entity.getVelocity().add(vec3d));
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"getBoundingBox"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void b(InjectionCallback cir) {
        Entity entity = (Entity)(Object)this;
        if (entity != null) {
            if (ClientUtil.aH(Prestige.eventBus, EntityEvent2.class)) {
                Box box = (Box)cir.getReturnValue();
                EntityEvent2 event = ClientUtil.aI(entity, box);
                if (ClientUtil.aJ(event)) {
                    cir.setReturnValue(ClientUtil.aK(event));
                }
            }
        }
    }

    @CInject(
        method = {"setVelocityClient"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void c(Vec3d velocity, InjectionCallback ci) {
        Entity entity = (Entity)(Object)this;
        if (entity == MinecraftClient.getInstance().player) {
            DoubleEvent3 event = ClientUtil.aL(velocity.x, velocity.y, velocity.z);
            if (ClientUtil.aM(event)) {
                entity.setVelocity(ClientUtil.aN(event), ClientUtil.aO(event), ClientUtil.aP(event));
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"getRotationVec"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void d(float tickDelta, InjectionCallback cir) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null && mc.player != null && (Object)this == mc.player && Prestige.o != null) {
            RenderBatch rot = ClientUtil.aQ(Prestige.o);
            if (rot != null) {
                cir.setReturnValue(mc.player.getRotationVector(ClientUtil.aR(rot), ClientUtil.aS(rot)));
            }
        }
    }
}
