package fr.first92.octahub.event.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRainStart(WeatherChangeEvent e) {
        if (!e.isCancelled() && e.toWeatherState()) {

            Bukkit.getWorlds().forEach(rs -> e.setCancelled(true));
        }
    }
}
