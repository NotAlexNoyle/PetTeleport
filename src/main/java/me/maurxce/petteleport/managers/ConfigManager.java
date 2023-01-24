package me.maurxce.petteleport.managers;

import me.maurxce.petteleport.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static FileConfiguration config = new YamlConfiguration();
    private static FileConfiguration lang = new YamlConfiguration();
    private static final File configFile = new File(getDataFolder(), "config.yml");
    private static final File langFile = new File(getDataFolder(), "lang.yml");

    public static void loadFiles() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        if (!configFile.exists()) saveResource("config.yml");
        if (!langFile.exists()) saveResource("lang.yml");

        try {
            reloadFiles();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static File getDataFolder() {
        return Main.instance.getDataFolder();
    }

    private static void saveResource(String resource) {
        Main.instance.saveResource(resource, false);
    }

    public static void reloadFiles() throws IOException, InvalidConfigurationException {
        config.load(configFile);
        lang.load(langFile);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getLang() {
        return lang;
    }
}
