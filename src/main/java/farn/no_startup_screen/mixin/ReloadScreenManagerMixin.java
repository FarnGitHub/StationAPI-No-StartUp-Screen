package farn.no_startup_screen.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.no_startup_screen.NoStartUpFlag;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReloadScreenManager.class)
public abstract class ReloadScreenManagerMixin {

    @Inject(method="openEarly()V", at = @At(value = "FIELD", target = "Lnet/modificationstation/stationapi/api/client/resource/ReloadScreenManager;currentReload:Ljava/util/Optional;", shift = At.Shift.AFTER), cancellable = true)
    private static void cancelStartUpScreenThread(CallbackInfo ci) {
        StationAPI.LOGGER.info("Canceling Startup Screen Thread");
        ci.cancel();
    }

    @WrapMethod(method="isReloadStarted")
    private static boolean preventFreezing(Operation<Boolean> original) {
        return NoStartUpFlag.skipCheckReloadScreenExist || original.call();
    }
}
