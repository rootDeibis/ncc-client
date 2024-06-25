package me.rootdeibis.nc.client.listeners;

import me.rootdeibis.nc.event.Listener;
import me.rootdeibis.nc.event.TargetHandler;
import me.rootdeibis.nc.event.events.DisplayGuiScreenEvent;
import me.rootdeibis.sixnine.client.gui.main.GuiMainScreen;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiCustomListener implements Listener {

    @TargetHandler
    public void onDisplayScreen(DisplayGuiScreenEvent e) {

        if(e.getGuiScreen() instanceof GuiMainMenu) {
           e.change(new GuiMainScreen());
        }
    }
}
