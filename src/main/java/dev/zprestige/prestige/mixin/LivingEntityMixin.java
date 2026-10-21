package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.FloatEvent3;
import dev.zprestige.prestige.event.FloatPositionEvent;
import dev.zprestige.prestige.event.IntEvent3;
import dev.zprestige.prestige.event.LivingEvent;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@CTransformer({LivingEntity.class})
public abstract class LivingEntityMixin {
    @CInject(
        method = {"travel"},
        target = {@CTarget("HEAD")}
    )
    private void a(Vec3d movementInput, InjectionCallback ci) {
        if (MinecraftClient.getInstance().player != null) {
            if (((LivingEntity)(Object)this).equals(MinecraftClient.getInstance().player)) {
                if (Prestige.o != null && ClientUtil.cJ(Prestige.o) != null) {
                    FloatEvent3 event = ClientUtil.cK(EventPhase.PRE, ((LivingEntity)(Object)this).getYaw(), ((LivingEntity)(Object)this).getPitch());
                    if (ClientUtil.cL(event)) {
                        FloatEvent3.c = ((LivingEntity)(Object)this).getYaw();
                        FloatEvent3.d = ((LivingEntity)(Object)this).getPitch();
                        ((LivingEntity)(Object)this).setYaw(event.a);
                        ((LivingEntity)(Object)this).setPitch(event.b);
                    }
                }
            }
        }
    }

    @CInject(
        method = {"travel"},
        target = {@CTarget("RETURN")}
    )
    private void b(Vec3d movementInput) {
        if (MinecraftClient.getInstance().player != null) {
            if (((LivingEntity)(Object)this).equals(MinecraftClient.getInstance().player)) {
                if (Prestige.o != null && ClientUtil.cM(Prestige.o) != null) {
                    FloatEvent3 event = ClientUtil.cN(EventPhase.POST, ((LivingEntity)(Object)this).getYaw(), ((LivingEntity)(Object)this).getPitch());
                    if (ClientUtil.cO(event)) {
                        ((LivingEntity)(Object)this).setYaw(FloatEvent3.c);
                        ((LivingEntity)(Object)this).setPitch(FloatEvent3.d);
                    }
                }
            }
        }
    }

    @CInject(
        method = {"getHandSwingDuration"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void c(InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.cP(Prestige.eventBus, IntEvent3.class)) {
            IntEvent3 event = ClientUtil.cQ(0);
            if (ClientUtil.cR(event)) {
                ci.setReturnValue(event.a);
            }
        }
    }

    @CInject(
        method = {"handleStatus"},
        target = {@CTarget("HEAD")}
    )
    private void d(byte status) {
        if (Prestige.eventBus != null && ClientUtil.cS(Prestige.eventBus, LivingEvent.class)) {
            if (status == 30) {
                ClientUtil.cU(ClientUtil.cT((LivingEntity)(Object)this));
            }
        }
    }

    @CInject(
        method = {"jump"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;addVelocityInternal(Lnet/minecraft/util/math/Vec3d;)V"
        )},
        cancellable = true
    )
    private void e(InjectionCallback ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity == MinecraftClient.getInstance().player && Prestige.o != null && ClientUtil.cV(Prestige.o) != null) {
            float yaw = entity.getYaw();
            FloatPositionEvent event = ClientUtil.cW(yaw);
            if (ClientUtil.cX(event)) {
                yaw = ClientUtil.cY(event);
                float f = yaw * (float) (Math.PI / 180.0);
                entity.setVelocity(entity.getVelocity().add(-MathHelper.sin(f) * 0.2F, 0.0, MathHelper.cos(f) * 0.2F));
                entity.velocityDirty = true;
                ci.cancel();
            }
        }
    }
}
