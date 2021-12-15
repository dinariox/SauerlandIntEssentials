package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveListener implements Listener {
    private final String[] messages = {
            ChatColor.LIGHT_PURPLE + "Tschüss " + ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + ", war schön mit dir.",
            ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + " hat uns vorerst verlassen.",
            ChatColor.LIGHT_PURPLE + "Mach's gut " + ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + ", bis zum nächsten Mal!",
            ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + " hat genug für heute.",
            ChatColor.LIGHT_PURPLE + "Bye bye " + ChatColor.GOLD + "%s",
            ChatColor.LIGHT_PURPLE + "Bis bald " + ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + ".",
            ChatColor.LIGHT_PURPLE + "Verabschiedet euch von " + ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + "!",
            ChatColor.GOLD + "%s" + ChatColor.LIGHT_PURPLE + " hat gerade den Server verlassen."
    };

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        // add 1 hour to the date
        date.setTime(date.getTime() + 3600000);
        String currentTime = formatter.format(date);
        playerQuitEvent.setQuitMessage(ChatColor.DARK_GRAY + "[" + currentTime + "] " + getRandomQuitMessage(player.getName()));
    }

    private String getRandomQuitMessage(String playerName) {
        int rnd = (int) (Math.random() * messages.length);
        return String.format(messages[rnd], playerName);
    }
}
