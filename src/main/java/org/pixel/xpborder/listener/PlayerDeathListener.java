package org.pixel.xpborder.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.pixel.xpborder.config.ConfigHandler;

public class PlayerDeathListener implements Listener {
    private final ConfigHandler ch;
    public PlayerDeathListener(ConfigHandler ch) { this.ch = ch; }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        YamlConfiguration config = ch.getConfig();
        config.set("gameOver", true);
        Player p = event.getEntity();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "----------------------------------------------------");
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Challenge vorbei – Defeat !");
        Bukkit.broadcastMessage("Ui ui ui, " + p.getName() + " ist gestorben!");
        Bukkit.broadcastMessage("Die Challenge ist jetzt vorbei!");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "----------------------------------------------------");
    }

    public class PlayerRespawnListener implements Listener {
        @EventHandler
        public void onPlayerRespawn(PlayerRespawnEvent event) {
            Player p = event.getPlayer();
            p.setGameMode(GameMode.SPECTATOR);
        }
    }
}
