package dev.zprestige.prestige.mixin;

import com.mojang.authlib.GameProfile;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.StringEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.SkinTextures;

@CTransformer({PlayerListEntry.class})
public class PlayerListEntryMixin {
    @CShadow("profile")
    public GameProfile profile;

    @CInject(
        method = {"getSkinTextures"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void a(InjectionCallback ci) {
        if (this.profile != null && ClientUtil.dD(Prestige.eventBus, StringEvent.class)) {
            StringEvent event = ClientUtil.dF((SkinTextures)ci.getReturnValue(), ClientUtil.dE(this.profile));
            if (ClientUtil.dG(event)) {
                ci.setReturnValue(ClientUtil.dH(event));
            }
        }
    }
}
