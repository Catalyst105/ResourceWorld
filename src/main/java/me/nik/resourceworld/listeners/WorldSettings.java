package me.nik.resourceworld.listeners;

import me.nik.resourceworld.ResourceWorld;
import me.nik.resourceworld.api.Manager;
import me.nik.resourceworld.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldSettings extends Manager {
    public WorldSettings(ResourceWorld plugin) {
        super(plugin);

        if (configBoolean("world.settings.always_day")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (new WorldUtils().worldExists()) {
                        Bukkit.getWorld(configString("world.settings.world_name")).setTime(1000);
                    } else cancel();
                }
            }.runTaskTimer(plugin, 1200, 1200);
        }
    }

    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Player) return;
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase(configString("world.settings.world_name"))) return;
        if (!configBoolean("world.settings.disable_entity_spawning")) return;
        e.setCancelled(true);
    }

    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (!p.getWorld().getName().equalsIgnoreCase(configString("world.settings.world_name"))) return;
        if (configBoolean("world.settings.disable_suffocation_damage")) {
            if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                e.setCancelled(true);
            }
        }
        if (configBoolean("world.settings.disable_drowning_damage")) {
            if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                e.setCancelled(true);
            }
        }
    }
}
