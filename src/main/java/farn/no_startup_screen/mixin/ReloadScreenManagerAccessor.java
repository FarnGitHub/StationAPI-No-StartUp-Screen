package farn.no_startup_screen.mixin;

import net.modificationstation.stationapi.api.client.resource.ReloadScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ReloadScreenManager.class)
public interface ReloadScreenManagerAccessor {

    @Invoker("onFinish")
    static void finishedReload() {
        throw new AssertionError();
    }

}
