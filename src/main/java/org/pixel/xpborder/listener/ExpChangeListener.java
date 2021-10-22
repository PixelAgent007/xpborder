package org.pixel.xpborder.listener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.pixel.xpborder.Main;
import org.pixel.xpborder.config.ConfigHandler;

public class ExpChangeListener implements Listener {
    private final ConfigHandler ch;
    public ExpChangeListener(ConfigHandler ch) { this.ch = ch; }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        YamlConfiguration config = ch.getConfig();
        Player ep = event.getPlayer();
        float xp = ep.getExp();

        config.set("xpAmount", xp);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!(p == ep)) {
                p.setExp(xp);
            }
        }
        ch.saveConfig(config);
    }

}
