package me.maurxce.petteleport.commands;

import me.maurxce.petteleport.managers.ConfigManager;
import me.maurxce.petteleport.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Reload implements CommandExecutor {

    private final FileConfiguration lang = ConfigManager.getLang();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!hasPermission(sender)) {
            String noPermission = lang.getString("no-permission");
            if (stringExists(noPermission)) sender.sendMessage(ChatUtils.translate(noPermission));

            return true;
        }

        try {
            ConfigManager.reloadFiles();

            if (sender instanceof Player) {
                String success = lang.getString("reload-success");
                if (stringExists(success)) sender.sendMessage(ChatUtils.translate(success));
            }

            Bukkit.getLogger().info("Successfully reloaded files.");
        } catch (IOException | InvalidConfigurationException e) {
            if (sender instanceof Player) {
                String error = lang.getString("reload-error");
                if (stringExists(error)) sender.sendMessage(ChatUtils.translate(error));
            }

            Bukkit.getLogger().warning("Failed to reload files.");
            e.printStackTrace();
        }

        return true;
    }

    private boolean hasPermission(CommandSender sender) {
        return (sender instanceof Player && sender.hasPermission("pettp.reload"));
    }

    private boolean stringExists(String string) {
        return string != null && !string.equals("");
    }
}
