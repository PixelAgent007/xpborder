package org.pixel.xpborder.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.pixel.xpborder.Main;
import java.io.File;

public class ConfigHandler {
    private Main main;
    public ConfigHandler(Main main) {
        this.main = main;
    }

    private File getConfigAsFile() {
        return new File(this.main.getDataFolder(), "config.yml");
    }
    public YamlConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(this.getConfigAsFile());
    }
    public void saveConfig(YamlConfiguration config) {
        try {
            config.save(this.getConfigAsFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDefaults(YamlConfiguration config) {
        if(!config.contains("highestLevel")) {config.set("highestLevel", 1);}
        if(!config.contains("xpAmount")) {config.set("xpAmount", 0);}
        if(!config.contains("gameOver")) {config.set("gameOver", false);}
        this.saveConfig(config);
    }
}
