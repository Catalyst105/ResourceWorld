package me.nik.resourceworld.listeners.blockregen;

import me.nik.resourceworld.ResourceWorld;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class BlockRegen implements Listener {

    private final ResourceWorld plugin;

    private final int delay;
    private final String world;
    private final List<String> blocks;

    public BlockRegen(ResourceWorld plugin) {
        this.plugin = plugin;
        this.delay = plugin.getConfig().getInt("world.settings.block_regeneration.regeneration_delay") * 1200;
        this.world = plugin.getConfig().getString("world.settings.world_name");
        this.blocks = plugin.getConfig().getStringList("world.settings.block_regeneration.blocks");
    }


    /*
    THIS IS ABSOLUTELY TERRIBLE, I need to recode this...
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.getBlock().getWorld().getName().equalsIgnoreCase(world)) return;
        final Material type = e.getBlock().getType();
        for (String block : blocks) {
            if (type.toString().equalsIgnoreCase(block)) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        e.getBlock().setType(type);
                    }
                }.runTaskLater(plugin, delay);
            }
        }
    }
}