package farn.no_startup_screen.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.no_startup_screen.NoStartUpScreen;
import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ReloadScreenManager.class)
public abstract class ReloadScreenManagerMixin {

    @SuppressWarnings("all")
    @Shadow
    private static @NotNull Optional<Thread> thread;

    /*
        Author : farnfarn02
        Reason : Replace Startup thread with something that wouldn't crash the game on some device
    */
    @Inject(method="openEarly()V", at = @At(value = "FIELD", target = "Lnet/modificationstation/stationapi/api/client/resource/ReloadScreenManager;currentReload:Ljava/util/Optional;", shift = At.Shift.AFTER), cancellable = true)
    private static void cancelStartUpScreenThread(CallbackInfo ci) {
        NoStartUpScreen.LOGGER.info("Canceling StationAPI Startup Screen's Thread");
        thread = Optional.of(new Thread(NoStartUpScreen::runFinishedThread));
        thread.ifPresent(Thread::start);
        ci.cancel();
    }

    /*
        Author : farnfarn02
        Reason : Prevent freezing on stationapi.mixin.resourceloader.client.MinecraftMixin
    */
    @WrapMethod(method="isReloadStarted")
    private static boolean preventFreezing(Operation<Boolean> original) {
        return NoStartUpScreen.skipReloadScreenCheck || original.call();
    }
}
