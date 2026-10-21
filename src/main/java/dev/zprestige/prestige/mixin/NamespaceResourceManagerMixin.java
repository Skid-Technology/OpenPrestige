package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.util.ClientUtil;
import java.util.Optional;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.util.Identifier;

@CTransformer({NamespaceResourceManager.class})
public class NamespaceResourceManagerMixin {
    @CInject(
        method = {"getResource"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void a(Identifier identifier, InjectionCallback callbackInfoReturnable) {
        if (identifier.getNamespace().equals("prestige")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dx(identifier)));
        }
    }
}
