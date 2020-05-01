package me.nik.resourceworld.tasks;

import me.nik.resourceworld.ResourceWorld;
import me.nik.resourceworld.utils.Messenger;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker extends BukkitRunnable {

    private final ResourceWorld plugin;

    public UpdateChecker(ResourceWorld plugin) {
        this.plugin = plugin;
    }

    public static boolean UPDATE = false;

    @Override
    public void run() {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=75994").openConnection();
            String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                System.out.println(Messenger.message("update_found"));
                UPDATE = true;
            } else {
                System.out.println(Messenger.message("update_not_found"));
            }
        } catch (IOException ignored) {
            System.out.println(Messenger.format("&cError while checking for updates, Is your server connected to the internet?"));
        }
    }
}