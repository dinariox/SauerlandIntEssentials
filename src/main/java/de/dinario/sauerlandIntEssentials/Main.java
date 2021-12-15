package de.dinario.sauerlandIntEssentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new LeaveListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new ItemBreakingListener(), this);
        pluginManager.registerEvents(new BlockPlacedListener(), this);
        pluginManager.registerEvents(new DestroySpawnerListener(), this);

        try {
            getCommand("broadcastcoordinates").setExecutor(new BroadcastCoordinatesCommand());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update ping in player list every second
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    String currentName = player.displayName().toString();
                    player.setPlayerListName("[" + player.getPing() + "ms] " + ChatColor.WHITE + currentName);
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}
