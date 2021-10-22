package org.pixel.xpborder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.pixel.xpborder.config.ConfigHandler;
import org.pixel.xpborder.listener.ExpChangeListener;
import org.pixel.xpborder.listener.ExpLevelChangeListener;
import org.pixel.xpborder.listener.JoinListener;
import org.pixel.xpborder.listener.PlayerDeathListener;

public class Main extends JavaPlugin {

    // Instancing Classes
    private final ConfigHandler ch = new ConfigHandler(this);
    private final ExpChangeListener el = new ExpChangeListener(ch);
    private final ExpLevelChangeListener ell = new ExpLevelChangeListener(ch);
    private final JoinListener jl = new JoinListener(ch);
    private final PlayerDeathListener dl = new PlayerDeathListener(ch);
    private final PlayerDeathListener.PlayerRespawnListener rl = dl.new PlayerRespawnListener();

    @Override
    public void onEnable() {
        YamlConfiguration config = ch.getConfig();
        config.setDefaults(config);

        for (World world : Bukkit.getWorlds()) {
            WorldBorder border = world.getWorldBorder();
            border.setDamageAmount(40.0);
            border.setCenter(world.getSpawnLocation().getX(), world.getSpawnLocation().getZ());
            border.setSize(config.getInt("highestLevel"));
        }

        // Registering Events
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(el, this);
        pm.registerEvents(ell, this);
        pm.registerEvents(jl, this);
        pm.registerEvents(dl, this);
        pm.registerEvents(rl, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
