package me.nik.resourceworld.utils;

import me.nik.resourceworld.files.Config;
import org.bukkit.Bukkit;

import java.io.File;

public class WorldUtils {

    public boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null)
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
        }
        return directory.delete();
    }

    public boolean worldExists() {
        return Bukkit.getWorld(Config.get().getString("world.settings.world_name")) != null;
    }

    public boolean netherExists() {
        return Bukkit.getWorld(Config.get().getString("nether_world.settings.world_name")) != null;
    }

    public boolean endExists() {
        return Bukkit.getWorld(Config.get().getString("end_world.settings.world_name")) != null;
    }
}
