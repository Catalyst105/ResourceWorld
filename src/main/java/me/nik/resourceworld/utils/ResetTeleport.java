package me.nik.resourceworld.utils;

import me.nik.resourceworld.api.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ResetTeleport extends Manager {

    public void resetTP() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getName().equals(configString("world.settings.world_name"))) {
                Location loc = Bukkit.getWorld(configString("world.settings.main_spawn_world")).getSpawnLocation();
                p.teleport(loc);
                p.sendMessage(Messenger.message("teleported_message"));
            }
        }
    }
}
