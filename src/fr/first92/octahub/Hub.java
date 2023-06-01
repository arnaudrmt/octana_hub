package fr.first92.octahub;

import fr.first92.octaapi.utils.Chunks;
import fr.first92.octaapi.utils.ScoreboardSign;
import fr.first92.octahub.armorstand.ArmorStandsHandler;
import fr.first92.octahub.event.EventsManager;
import fr.first92.octahub.npc.NPC;
import fr.first92.octahub.utils.ServerSetup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hub extends JavaPlugin {

    private static Hub instance;
    private Map<Player, ScoreboardSign> boards = new HashMap<>();
    private List<NPC> npcs = new ArrayList<>();

    @Override
    public void onEnable() {

        instance = this;

        new Chunks().around(new Location(Bukkit.getWorld("world"), -8, 71, 66).getChunk(), 20);

        new ServerSetup().setUp();

        new ArmorStandsHandler().load();

        new EventsManager().registerEvents();
    }

    public static Hub getInstance() {
        return instance;
    }

    public Map<Player, ScoreboardSign> getBoards() {
        return boards;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }
}
