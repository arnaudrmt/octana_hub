package fr.first92.octahub.event.events;

import fr.first92.octahub.Hub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        Hub.getInstance().getNpcs().forEach(rs -> {rs.setPlayer(p); rs.destroy();});

        e.setQuitMessage("");
    }
}
