package fr.first92.octahub.armorstand;

import fr.first92.octahub.Hub;
import fr.first92.octahub.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class ArmorStandsHandler {

    public void load(){

        Bukkit.getWorlds().forEach(w -> {

            w.getEntities().stream().filter(e -> e instanceof ArmorStand).filter(Entity::isCustomNameVisible).forEach(as -> {

                NPC npc = new NPC(null, "§e§l" + as.getCustomName().toUpperCase(), as.getLocation());

                if(as.getCustomName().equalsIgnoreCase("lobby")) {

                    Bukkit.getWorlds().forEach(rs -> rs.setSpawnLocation((int) as.getLocation().getX(),
                            (int) as.getLocation().getY(), (int) as.getLocation().getZ()));
                }

                if(as.getCustomName().equalsIgnoreCase("ktp2017")) npc.setSkin("YAKUZA_HANAYAMA");
                if(as.getCustomName().equalsIgnoreCase("murder")) npc.setSkin("HeyManNiceShot72");
                if(as.getCustomName().equalsIgnoreCase("npc")) npc.setSkin("pnj");
                if(as.getCustomName().equalsIgnoreCase("midnight")) npc.setSkin("9vs");

                if(!as.getCustomName().equalsIgnoreCase("lobby")) {
                    Hub.getInstance().getNpcs().add(npc);
                }

                as.remove();
            });
        });
    }
}