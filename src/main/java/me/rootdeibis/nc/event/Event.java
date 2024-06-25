package me.rootdeibis.nc.event;


import me.rootdeibis.nc.NCClient;

public abstract class Event {


    private boolean isCancelled = false;


    public Event call() {
        this.handle(this);

        return this;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    private void handle(Event event){
        if(!NCClient.INSTANCE.getEventManager().handlers.containsKey(event.getClass())) return;

        for (EventHandler eventHandler : NCClient.INSTANCE.getEventManager().handlers.get(event.getClass())) {
            eventHandler.handle(event);
        }
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
