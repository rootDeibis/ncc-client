package me.rootdeibis.nc.client.listeners;

import me.guichaguri.betterfps.BetterFpsClient;
import me.rootdeibis.nc.event.Listener;
import me.rootdeibis.nc.event.TargetHandler;
import me.rootdeibis.nc.event.events.GameStartEvent;
import me.rootdeibis.nc.utils.font.FontUtils;
import net.minecraft.client.Minecraft;

public class GameStartListener implements Listener {

    @TargetHandler
    public void onGameStart(GameStartEvent e){
        FontUtils.init();
        BetterFpsClient.start(Minecraft.getMinecraft());
    }
}
