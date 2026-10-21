package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent4;
import dev.zprestige.prestige.event.EntityEvent5;
import dev.zprestige.prestige.event.EntityEvent6;
import dev.zprestige.prestige.event.PlayerStringEvent;
import dev.zprestige.prestige.event.TickEvent2;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

@CTransformer({EntityRenderer.class})
public abstract class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    @CInject(
        method = {"getAndUpdateRenderState"},
        target = {@CTarget("HEAD")}
    )
    public void a(T entity, float tickDelta) {
        if (entity == MinecraftClient.getInstance().player) {
            if (Prestige.eventBus != null && Prestige.o != null && ClientUtil.aT(Prestige.o) != null && ClientUtil.aU(Prestige.eventBus, EntityEvent4.class)) {
                ClientUtil.aW(ClientUtil.aV(entity, EventPhase.PRE));
            }
        }
    }

    @CInject(
        method = {"getAndUpdateRenderState"},
        target = {@CTarget("RETURN")}
    )
    public void b(T entity, float tickDelta) {
        if (entity == MinecraftClient.getInstance().player) {
            if (Prestige.eventBus != null && Prestige.p != null && ClientUtil.aX(Prestige.p) && ClientUtil.aY(Prestige.eventBus, EntityEvent4.class)) {
                ClientUtil.ba(ClientUtil.aZ(entity, EventPhase.POST));
            }
        }
    }

    @CInject(
        method = {"getSkyLight"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void c(T entity, BlockPos pos, InjectionCallback cir) {
        if (ClientUtil.bb(Prestige.eventBus, TickEvent2.class)) {
            TickEvent2 event = ClientUtil.bc();
            if (ClientUtil.bd(event)) {
                cir.setReturnValue(15);
            }
        }
    }

    @CInject(
        method = {"updateRenderState"},
        target = {@CTarget("RETURN")}
    )
    public void d(T entity, S state, float tickDelta) {
        if (Prestige.eventBus != null && state.displayName != null) {
            boolean hasLabelText = ClientUtil.be(Prestige.eventBus, EntityEvent5.class);
            boolean hasHealthIndicators = ClientUtil.bf(Prestige.eventBus, PlayerStringEvent.class);
            boolean hasRenderLabel = ClientUtil.bg(Prestige.eventBus, EntityEvent6.class);
            if (hasLabelText || hasHealthIndicators || hasRenderLabel) {
                Text originalDisplayName = state.displayName;
                Text displayName = originalDisplayName;
                if (entity instanceof PlayerEntity player && entity.isAlive() && (hasLabelText || hasHealthIndicators)) {
                    EntityEvent5 labelTextEvent = ClientUtil.bh(entity, originalDisplayName);
                    boolean labelTextCancelled = ClientUtil.bi(labelTextEvent);
                    displayName = ClientUtil.bj(labelTextEvent);
                    if (!labelTextCancelled) {
                        PlayerStringEvent event = ClientUtil.bk(player, "");
                        if (ClientUtil.bl(event)) {
                            displayName = Text.of(displayName.getString() + ClientUtil.bm(event));
                        }
                    }
                }

                if (hasRenderLabel) {
                    EntityEvent6 event = ClientUtil.bn(originalDisplayName, displayName, entity);
                    if (ClientUtil.bo(event)) {
                        state.displayName = null;
                        return;
                    }
                }

                state.displayName = displayName != null && !displayName.getString().isEmpty() ? displayName : null;
            }
        }
    }
}
