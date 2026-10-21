package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.event.FloatDoubleEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World.ExplosionSourceType;
import net.minecraft.world.explosion.ExplosionBehavior;

@CTransformer({ServerWorld.class})
public class ServerWorldMixin {
    @CInject(
        method = {"createExplosion"},
        target = {@CTarget("HEAD")}
    )
    public void a(
        Entity entity,
        DamageSource damageSource,
        ExplosionBehavior behavior,
        double x,
        double y,
        double z,
        float power,
        boolean createFire,
        ExplosionSourceType explosionSourceType,
        ParticleEffect smallParticle,
        ParticleEffect largeParticle,
        RegistryEntry<SoundEvent> soundEvent,
        InjectionCallback ci
    ) {
        FloatDoubleEvent event = ClientUtil.dW(power, x, y, z);
        ClientUtil.dX(event);
    }
}
