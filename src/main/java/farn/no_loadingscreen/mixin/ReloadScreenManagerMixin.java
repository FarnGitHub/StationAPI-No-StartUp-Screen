package farn.no_loadingscreen.mixin;

import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import net.modificationstation.stationapi.api.resource.CompositeResourceReload;
import net.modificationstation.stationapi.api.resource.ResourceReload;
import net.modificationstation.stationapi.impl.client.resource.ReloadScreenApplicationExecutor;
import net.modificationstation.stationapi.impl.client.resource.ReloadScreenManagerImpl;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Drawable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.concurrent.Executor;

@Mixin(ReloadScreenManager.class)
public abstract class ReloadScreenManagerMixin {

    @Shadow
    private static @NotNull Executor applicationExecutor;

    @Shadow
    private static @NotNull Optional<ResourceReload> currentReload;

    @Shadow
    static void onFinish() {
        throw new AssertionError();
    }

    @Overwrite
    public static void openEarly() throws LWJGLException {
        ReloadScreenManagerImpl.isMinecraftDone = false;
        applicationExecutor = ReloadScreenApplicationExecutor.INSTANCE;
        currentReload = Optional.of(new CompositeResourceReload());
    }

    @Overwrite
    public static boolean isReloadStarted() {
        return true;
    }

    @Overwrite
    public static boolean isReloadComplete() {
        return currentReload.isPresent() && currentReload.orElse(null/*safe*/).isComplete();
    }

    @Overwrite
    private static void onStartup(
            final Minecraft minecraft,
            final Drawable drawable
    ) {
        onFinish();
    }
}
