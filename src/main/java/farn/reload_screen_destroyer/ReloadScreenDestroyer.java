package farn.reload_screen_destroyer;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.event.init.InitFinishedEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ReloadScreenDestroyer {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    @EventListener
    public void onGameFinished(InitFinishedEvent event) {
        ((Minecraft) FabricLoader.getInstance().getGameInstance()).textureManager.reload();
    }
}
