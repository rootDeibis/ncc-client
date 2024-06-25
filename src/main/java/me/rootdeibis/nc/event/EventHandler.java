package me.rootdeibis.nc.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventHandler {

    private final Object source;
    private final Method invoker;

    public EventHandler(Object source, Method invoker){
        this.source = source;
        this.invoker = invoker;
    }

    public void handle(Event event){
        try {
            this.invoker.invoke(this.source, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
