package farn.no_startup_screen;

import farn.no_startup_screen.mixin.ReloadScreenManagerAccessor;
import net.modificationstation.stationapi.api.tick.TickScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoStartUpScreen {
    public static boolean skipReloadScreenCheck = true;
    public static Logger LOGGER = LogManager.getLogger("No Startup's Screen");

    public static void runFinishedThread() {
        TickScheduler.CLIENT_RENDER_END.distributed(() -> {
            ReloadScreenManagerAccessor.finishedReload();
            NoStartUpScreen.skipReloadScreenCheck = false;
            NoStartUpScreen.LOGGER.info("Finished loading");
        });
    }
}
