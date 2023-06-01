package fr.first92.octahub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;

public class ServerSetup {

    public void setUp() {

        Bukkit.getWorlds().forEach(rs -> {

            rs.setTime(6000);
            rs.setPVP(false);
            rs.setDifficulty(Difficulty.EASY);

            rs.setGameRuleValue("doDaylightCycle", "false");
            rs.setGameRuleValue("doWeatherCycle", "false");
            rs.setGameRuleValue("doMobSpawning", "false");
            rs.setGameRuleValue("mobGriefing", "false");
            rs.setGameRuleValue("announceAdvancements", "false");
        });

        Bukkit.getServer().setDefaultGameMode(GameMode.ADVENTURE);
    }
}
