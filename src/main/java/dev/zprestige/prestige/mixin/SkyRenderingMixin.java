package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.ColorEvent;
import dev.zprestige.prestige.util.ClientUtil;
import java.awt.Color;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.world.ClientWorld;

@CTransformer({SkyRendering.class})
public class SkyRenderingMixin {
    @CInject(
        method = {"updateRenderState"},
        target = {@CTarget("TAIL")}
    )
    private void a(ClientWorld world, float tickDelta, Camera camera, SkyRenderState state, InjectionCallback ci) {
        if (ClientUtil.dY(Prestige.eventBus, ColorEvent.class)) {
            int skyPacked = state.skyColor;
            Color initialSky = ClientUtil.dZ(skyPacked >> 16 & 0xFF, skyPacked >> 8 & 0xFF, skyPacked & 0xFF);
            int hzPacked = state.sunriseAndSunsetColor;
            Color initialHorizon = ClientUtil.ea(hzPacked >> 16 & 0xFF, hzPacked >> 8 & 0xFF, hzPacked & 0xFF);
            ColorEvent event = ClientUtil.eb(initialSky, initialHorizon);
            if (ClientUtil.ec(event)) {
                if (ClientUtil.ed(event) != null) {
                    Color c = ClientUtil.ee(event);
                    state.skyColor = 0xFF000000 | (c.getRed() & 0xFF) << 16 | (c.getGreen() & 0xFF) << 8 | c.getBlue() & 0xFF;
                }

                if (ClientUtil.ef(event) != null) {
                    Color h = ClientUtil.eg(event);
                    int origAlpha = hzPacked >> 24 & 0xFF;
                    state.sunriseAndSunsetColor = origAlpha << 24 | (h.getRed() & 0xFF) << 16 | (h.getGreen() & 0xFF) << 8 | h.getBlue() & 0xFF;
                }
            }
        }
    }
}
