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
    private final Main main;
    public ExpChangeListener(Main main) { this.main = main; }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        final ConfigHandler ch = new ConfigHandler(main);
        YamlConfiguration config = ch.getConfig();
        if(!config.contains("highestLevel")) {config.set("highestLevel", 1);}
        if(!config.contains("xpAmount")) {config.set("xpAmount", 0);}
        if(!config.contains("gameOver")) {config.set("gameOver", false);}
        ch.saveConfig();

        Player ep = event.getPlayer();
        float xp = ep.getExp();

        config.set("xpAmount", xp);
        ch.saveConfig();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!(p == ep)) {
                p.setExp(xp);
            }
        }
    }

}
