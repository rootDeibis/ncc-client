package me.rootdeibis.nc.client.listeners;

import me.rootdeibis.nc.event.Listener;
import me.rootdeibis.nc.event.TargetHandler;
import me.rootdeibis.nc.event.events.TickEvent;

public class OnTickListener implements Listener {

    @TargetHandler
    public void onHnadle(TickEvent event) {

        System.out.println("Hola");

    }

}
