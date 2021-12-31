package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        DeathPositionLogger.getInstance().addDeathPosition(event.getPlayer().getUniqueId().toString(), event.getPlayer().getLocation());

        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        // add 1 hour to the date
        date.setTime(date.getTime() + 3600000);
        String currentTime = formatter.format(date);
        event.setDeathMessage(ChatColor.DARK_GRAY + "[" + currentTime + "] " + ChatColor.BLUE + "Oh no! " + ChatColor.RED + event.getDeathMessage());
        // Tell the player where they died
        event.getEntity().sendMessage(ChatColor.GRAY + "You died at " + ChatColor.RED + event.getEntity().getLocation().getBlockX() + " " + event.getEntity().getLocation().getBlockY() + " " + event.getEntity().getLocation().getBlockZ());
    }
}
