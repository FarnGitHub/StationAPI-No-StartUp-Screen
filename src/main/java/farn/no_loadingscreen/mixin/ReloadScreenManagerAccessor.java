package farn.no_loadingscreen.mixin;

import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import org.lwjgl.opengl.Drawable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ReloadScreenManager.class)
public interface ReloadScreenManagerAccessor {

    @Invoker("onStartup")
    static void onStartThatShitCrasher(
            final Minecraft minecraft,
            final Drawable drawable) {
        throw new AssertionError();
    }
}
