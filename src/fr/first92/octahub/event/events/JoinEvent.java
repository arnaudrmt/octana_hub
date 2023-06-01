package fr.first92.octahub.event.events;

import fr.first92.commons.Account;
import fr.first92.octaapi.network.data.AccountAccess;
import fr.first92.octahub.Hub;
import fr.first92.octahub.armorstand.ArmorStandsHandler;
import fr.first92.octahub.scoreboard.MainScoreboard;
import fr.first92.octahub.utils.PacketReader;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        World w = p.getWorld();
        Account account = new AccountAccess(p).getAccount();

        PacketReader pr = new PacketReader(p);
        pr.inject();

        p.setGameMode(GameMode.ADVENTURE);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);

        Hub.getInstance().getNpcs().forEach(rs -> {rs.setPlayer(p); rs.spawn();});

        p.teleport(new Location(p.getWorld(), w.getSpawnLocation().getX(), w.getSpawnLocation().getY(),
                w.getSpawnLocation().getZ(), 180, 0));

        if(account.getRank().getPower() < 70) {
            e.setJoinMessage(account.getRank().getColor() + account.getRank().getPrefix() + p.getDisplayName() +
                    " §6has joined the hub!");
        } else e.setJoinMessage("");

        new MainScoreboard(p).create();
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        new ArmorStandsHandler().load();
    }
}
