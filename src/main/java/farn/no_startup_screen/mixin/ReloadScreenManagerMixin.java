package farn.no_startup_screen.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import net.modificationstation.stationapi.api.resource.CompositeResourceReload;
import net.modificationstation.stationapi.api.resource.ResourceReload;
import net.modificationstation.stationapi.impl.client.resource.ReloadScreenApplicationExecutor;
import net.modificationstation.stationapi.impl.client.resource.ReloadScreenManagerImpl;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.concurrent.Executor;

@Mixin(ReloadScreenManager.class)
public abstract class ReloadScreenManagerMixin {

    @Shadow
    private static @NotNull Executor applicationExecutor;

    @Shadow
    @SuppressWarnings("all")
    private static @NotNull Optional<ResourceReload> currentReload;

    @WrapMethod(method="openEarly")
    private static void openEarlyNoStartUp(Operation<Void> original) {
        ReloadScreenManagerImpl.isMinecraftDone = false;
        applicationExecutor = ReloadScreenApplicationExecutor.INSTANCE;
        currentReload = Optional.of(new CompositeResourceReload());
    }

    @WrapMethod(method="isReloadStarted")
    private static boolean isReloadStartedUnused(Operation<Boolean> original) {
        return true;
    }

    @WrapMethod(method="isReloadComplete")
    private static boolean isReloadCompleteNoScreen(Operation<Boolean> original) {
        return currentReload.isPresent() && currentReload.orElse(null).isComplete();
    }
}
