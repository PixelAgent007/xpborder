package org.pixel.xpborder.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import org.pixel.xpborder.Main;
import org.pixel.xpborder.config.ConfigHandler;

public class ExpLevelChangeListener implements Listener {
    private final Main main;
    public ExpLevelChangeListener(Main main) { this.main = main; }

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent event) {
        final ConfigHandler ch = new ConfigHandler(main);
        int lvl = event.getNewLevel();
        YamlConfiguration config = ch.getConfig();
        if(!config.contains("highestLevel")) {config.set("highestLevel", 1);}
        if(!config.contains("xpAmount")) {config.set("xpAmount", 0);}
        if(!config.contains("gameOver")) {config.set("gameOver", false);}
        ch.saveConfig();
        config.set("highestLevel", lvl);
        ch.saveConfig();

        for (World world : Bukkit.getWorlds()) {
            WorldBorder border = world.getWorldBorder();
            border.setDamageAmount(40.0);
            border.setCenter(world.getSpawnLocation());
            border.setSize(config.getInt("highestLevel"), 1);
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!(p == event.getPlayer())) {
                p.setLevel(lvl);
            }
        }
    }

}
