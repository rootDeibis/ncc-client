package me.rootdeibis.nc.event;


public abstract class Event {


    private boolean isCancelled = false;


    public Event call() {
        return this;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }


    public boolean isCancelled() {
        return isCancelled;
    }
}
