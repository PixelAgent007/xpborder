package org.pixel.xpborder.listener;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.pixel.xpborder.config.ConfigHandler;

public class JoinListener implements Listener {
    private final ConfigHandler ch;
    public JoinListener(ConfigHandler ch) { this.ch = ch; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        YamlConfiguration config = ch.getConfig();
        Player p = event.getPlayer();

        if (config.getBoolean("gameOver")) {
            p.setGameMode(GameMode.SPECTATOR);
            return;
        }

        p.setLevel(config.getInt("highestLevel"));
        p.setExp(config.getInt("xpAmount"));
        ch.saveConfig(config);
    }

}
