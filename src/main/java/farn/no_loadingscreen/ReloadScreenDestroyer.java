package farn.no_loadingscreen;

import net.modificationstation.stationapi.impl.client.resource.ReloadScreenApplicationExecutor;

import java.lang.reflect.Field;
import java.util.Queue;

public class ReloadScreenDestroyer {

    public static Runnable runner;

    public static Queue<Runnable> getTask() {
        try {
            Field theDamnField = ReloadScreenApplicationExecutor.class.getDeclaredField("tasks");
            theDamnField.setAccessible(true);
            return (Queue<Runnable>) theDamnField.get(ReloadScreenApplicationExecutor.INSTANCE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
