package me.rootdeibis.nc;

import me.rootdeibis.nc.event.EventManager;
import me.rootdeibis.nc.client.listeners.GameStartListener;
import me.rootdeibis.nc.client.listeners.GuiCustomListener;
import me.rootdeibis.nc.client.listeners.OnTickListener;
import net.minecraft.client.Minecraft;

public class NCClient {

    private final Minecraft minecraft;
    private final EventManager eventManager;

    public static NCClient INSTANCE;

    private static final String windowTitle = "NCClient v1.0 | Minecraft 1.8.9";
    public NCClient(Minecraft minecraft) {
        INSTANCE = this;
        this.minecraft = minecraft;
        this.eventManager = new EventManager();


        this.registerEvents();

    }

    public void registerEvents(){

        this.eventManager.register(new GameStartListener());
        this.eventManager.register(new GuiCustomListener());
        this.eventManager.register(new OnTickListener());

    }

    public Minecraft getMinecraft() {
        return minecraft;
    }

    public static String getWindowTitle() {
        return windowTitle;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
