package me.rootdeibis.sixnine.client;


import me.guichaguri.betterfps.BetterFps;
import me.guichaguri.betterfps.BetterFpsClient;
import me.rootdeibis.nc.event.EventManager;
import me.rootdeibis.sixnine.client.gui.Notification;
import me.rootdeibis.sixnine.client.gui.notification.NotificationManager;
import me.rootdeibis.sixnine.utils.font.FontUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Timer;
import java.util.TimerTask;


public class NCClient {

    public static EventManager eventManager;


    public static void run() {
        FontUtils.init();
        BetterFpsClient.start(Minecraft.getMinecraft());
    }


}
