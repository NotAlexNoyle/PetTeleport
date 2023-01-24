package me.maurxce.petteleport;

import me.maurxce.petteleport.commands.Reload;
import me.maurxce.petteleport.listeners.PlayerTeleport;
import me.maurxce.petteleport.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.loadFiles();
        getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
        getCommand("reload").setExecutor(new Reload());
    }

    @Override
    public void onDisable() {
       instance = null;
    }
}
