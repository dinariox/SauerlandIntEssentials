package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent chatEvent) {
        Player player = chatEvent.getPlayer();
        String message = chatEvent.getMessage().replace("%", "%%");
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        // add 1 hour to the date
        date.setTime(date.getTime() + 3600000);
        String currentTime = formatter.format(date);
        chatEvent.setFormat(ChatColor.GRAY + "[" + currentTime + "] " + ChatColor.BLUE + player.getName() + ChatColor.DARK_GRAY +  " » " + ChatColor.WHITE + message);
    }
}
