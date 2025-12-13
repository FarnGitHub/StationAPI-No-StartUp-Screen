package farn.reload_screen_destroyer.mixin;

import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import org.lwjgl.LWJGLException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ReloadScreenManager.class)
public class ReloadScreenManagerMixin {

    @Overwrite
    public static void openEarly() throws LWJGLException {

    }

    @Overwrite
    public static boolean isReloadStarted() {
        return true;
    }

    @Overwrite
    public static boolean isReloadComplete() {
        return true;
    }
}
