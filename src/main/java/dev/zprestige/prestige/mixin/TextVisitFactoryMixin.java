package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CModifyArgs;
import net.lenni0451.classtransform.utils.CArgs;
import net.minecraft.text.TextVisitFactory;

@CTransformer({TextVisitFactory.class})
public class TextVisitFactoryMixin {
    @CModifyArgs(
        method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
            ordinal = 0
        )}
    )
    private static void a(CArgs cArgs) {
        String text = (String)cArgs.get(0);
        if (text != null) {
            if (!ClientUtil.el(Prestige.eventBus, StringEvent2.class)) {
                return;
            }

            StringEvent2 event = ClientUtil.em("", text);
            if (ClientUtil.en(event)) {
                cArgs.set(0, text.replace(ClientUtil.eo(event), ClientUtil.ep(event)));
            }
        }
    }
}
