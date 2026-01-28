package farn.no_startup_screen.mixin;

import farn.no_startup_screen.NoStartUpFlag;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method="init", at = @At("TAIL"))
    public void afterInit(CallbackInfo ci) {
        ReloadScreenManagerAccessor.finishedReload();
        NoStartUpFlag.skipCheckReloadScreenExist = false;
    }
}
