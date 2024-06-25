package me.rootdeibis.nc.event.events;

import me.rootdeibis.nc.event.Event;

public class FontRenderEvent extends Event {

    private String text;
    public FontRenderEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
