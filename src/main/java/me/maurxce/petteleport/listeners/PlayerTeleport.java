package me.maurxce.petteleport.listeners;

import me.maurxce.petteleport.Main;
import me.maurxce.petteleport.managers.ConfigManager;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport implements Listener {

    Main main = Main.instance;
    private final FileConfiguration config = ConfigManager.getConfig();

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        if (!player.hasPermission("pettp.enable")) return;

        Chunk teleportedFrom = e.getFrom().getChunk();
        if (teleportedFrom.isForceLoaded()) return;

        int keepChunkLoadedSeconds = config.getInt("keep-chunk-loaded");
        if (keepChunkLoadedSeconds <= 0) return;

        teleportedFrom.setForceLoaded(true);
        main.getServer().getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                if (teleportedFrom.isLoaded()) teleportedFrom.setForceLoaded(false);
            }
        }, 20L * keepChunkLoadedSeconds);
    }
}
