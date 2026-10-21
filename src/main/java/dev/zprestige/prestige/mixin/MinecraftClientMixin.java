package dev.zprestige.prestige.mixin;

import net.lenni0451.classtransform.annotations.CTransformer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@CTransformer({MinecraftClient.class})
public class MinecraftClientMixin extends Screen {
    public MinecraftClientMixin(Text title) {
        super(title);
    }
}
