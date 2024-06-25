package me.rootdeibis.sixnine.client;


import me.guichaguri.betterfps.BetterFpsClient;
import me.rootdeibis.nc.event.EventManager;
import me.rootdeibis.nc.utils.font.FontUtils;

import net.minecraft.client.Minecraft;


public class NCClient {

    public static EventManager eventManager;


    public static void run() {
        FontUtils.init();
        BetterFpsClient.start(Minecraft.getMinecraft());
    }


}
