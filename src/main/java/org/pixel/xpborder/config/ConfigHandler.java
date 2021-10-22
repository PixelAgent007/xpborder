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
    public void saveConfig() {
        try {
            this.getConfig().save(this.getConfigAsFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
