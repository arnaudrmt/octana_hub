package fr.first92.octahub.event;

import fr.first92.octahub.Hub;
import fr.first92.octahub.event.events.*;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public void registerEvents() {

        Hub hub = Hub.getInstance();
        PluginManager pm = hub.getServer().getPluginManager();

        pm.registerEvents(new JoinEvent(), hub);
        pm.registerEvents(new QuitEvent(), hub);
        pm.registerEvents(new ChatEvent(), hub);
        pm.registerEvents(new MoveEvent(), hub);
        pm.registerEvents(new WeatherEvent(), hub);
        pm.registerEvents(new DamageEvent(), hub);
        pm.registerEvents(new EntitySpawn(), hub);
    }
}
