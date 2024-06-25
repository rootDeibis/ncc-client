package me.rootdeibis.nc;

import net.minecraft.client.Minecraft;

public class NCClient {

    private final Minecraft minecraft;

    private static String windowTitle = "NCClient v1.0 | Minecraft 1.8.9";;
    public NCClient(Minecraft minecraft) {
        this.minecraft = minecraft;

    }


    public Minecraft getMinecraft() {
        return minecraft;
    }

    public static String getWindowTitle() {
        return windowTitle;
    }


}
