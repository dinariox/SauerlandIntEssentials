package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent chatEvent) {
        Player player = chatEvent.getPlayer();
        String message = chatEvent.getMessage().replace("%", "%%");
        chatEvent.setFormat(ChatColor.BLUE + player.getName() + ChatColor.DARK_GRAY +  " » " + ChatColor.WHITE + message);
    }
}
