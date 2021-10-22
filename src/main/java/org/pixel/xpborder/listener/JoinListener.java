package org.pixel.xpborder.listener;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.pixel.xpborder.Main;
import org.pixel.xpborder.config.ConfigHandler;

public class JoinListener implements Listener {
    private final Main main;
    public JoinListener(Main main) { this.main = main; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final ConfigHandler ch = new ConfigHandler(main);
        YamlConfiguration config = ch.getConfig();
        if(!config.contains("highestLevel")) {config.set("highestLevel", 1);}
        if(!config.contains("xpAmount")) {config.set("xpAmount", 0);}
        if(!config.contains("gameOver")) {config.set("gameOver", false);}
        ch.saveConfig();
        Player p = event.getPlayer();

        if (config.getBoolean("gameOver")) {
            p.setGameMode(GameMode.SPECTATOR);
            return;
        }

        if (!(p.hasPlayedBefore()) && p.getLevel() == 0){
            p.setLevel(1);
        }

        p.setExp(config.getInt("xpAmount"));
        p.setLevel(config.getInt("highestLevel"));
    }

}
