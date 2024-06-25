package me.rootdeibis.nc.event;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {
    public HashMap<Class<?>, ArrayList<EventHandler>> handlers = new HashMap<>();


    public EventManager(){}


    public void register(Listener listener) {

        List<Method> methodTargets = Arrays.stream(listener.getClass().getDeclaredMethods()).filter(m ->
                m.isAnnotationPresent(TargetHandler.class)

        ).collect(Collectors.toList());

        if(methodTargets.size() > 0){

            for(Method method : methodTargets) {
                Class<?> eventType = method.getParameterTypes()[0];
                method.setAccessible(true);

                if(!this.handlers.containsKey(eventType)) this.handlers.put(eventType, new ArrayList<>());

                this.handlers.get(eventType).add(new EventHandler(listener, method));


            }

        }


    }


}
