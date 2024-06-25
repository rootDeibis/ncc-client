package me.rootdeibis.nc.event.events;

import me.rootdeibis.nc.event.Event;
import net.minecraft.client.gui.GuiScreen;

public class DisplayGuiScreenEvent extends Event {

    private GuiScreen guiScreen;

    public DisplayGuiScreenEvent(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }

    public void change(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }

    public GuiScreen getGuiScreen() {
        return guiScreen;
    }
}
