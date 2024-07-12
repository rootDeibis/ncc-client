package me.rootdeibis.nc;

import com.logisticscraft.occlusionculling.DataProvider;
import com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import me.rootdeibis.nc.event.EventManager;
import me.rootdeibis.nc.client.listeners.GameStartListener;
import me.rootdeibis.nc.client.listeners.GuiCustomListener;
import me.rootdeibis.nc.client.listeners.OnTickListener;
import me.rootdeibis.nc.culling.CullTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;
public class NCClient {

    private final Minecraft minecraft;
    private final EventManager eventManager;

    public static NCClient INSTANCE;

    private CullTask cullingTak;

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

    public void startCulling() {
        cullingTak = new CullTask(new OcclusionCullingInstance(128, new DataProvider() {

            private WorldClient world;

            @Override
            public boolean prepareChunk(int x, int z) {
                return (world = minecraft.theWorld) != null;
            }

            @Override
            public boolean isOpaqueFullCube(int x, int y, int z) {
                return world.isBlockNormalCube(new BlockPos(x, y, z), false);
            }

        }));

        Thread generalUpdateThread = new Thread(() -> {
            while(Minecraft.getMinecraft() != null) {
                try {
                    Thread.sleep(10);
                }
                catch(InterruptedException error) {
                    return;
                }

                cullingTak.run();
            }
        }, "Async Updates");
        generalUpdateThread.setUncaughtExceptionHandler((thread, error) -> {

        });
        generalUpdateThread.start();
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
