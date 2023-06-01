package fr.first92.octahub.event.events;

import fr.first92.octahub.Hub;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        Location loc = p.getLocation();

        Hub.getInstance().getNpcs().forEach(rs -> {
            rs.setPlayer(p);
            if(rs.getName().contains("ktp")) rs.equip(0, new ItemStack(Items.GOLDEN_SWORD));
            if(rs.getName().contains("midnight")) rs.equip(0, new ItemStack(Blocks.CHEST));
            if(rs.getName().contains("pnj")) rs.equip(0, new ItemStack(Items.REDSTONE));
            if(rs.getName().contains("murder")) rs.equip(0, new ItemStack(Items.IRON_SWORD));
        });
    }
}
