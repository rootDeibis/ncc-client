package me.rootdeibis.nc.event.events;

import me.rootdeibis.nc.event.Event;

public class FontRenderEvent extends Event {

    private final String text;
    public FontRenderEvent(String text) {
        this.text = text;
    }
}
