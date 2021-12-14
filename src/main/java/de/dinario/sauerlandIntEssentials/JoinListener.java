package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final String[] messages = {
            ChatColor.GREEN + "Hey, seht wer da ist: " + ChatColor.GOLD + "%s!",
            ChatColor.GREEN + "Na wer ist denn das? " + ChatColor.GOLD + "%s!",
            ChatColor.GOLD + "%s" + ChatColor.GREEN + " ist wieder da!",
            ChatColor.GREEN + "Guckt mal, ein wildes " + ChatColor.GOLD + "%s " + ChatColor.GREEN + "aus dem hohen Gras erscheint!",
            ChatColor.GREEN + "Der Kaiser rief - und " + ChatColor.GOLD + "%s " + ChatColor.GREEN + "kam.",
            ChatColor.GOLD + "%s" + ChatColor.GREEN + " ist gerade aufgetaucht!",
            ChatColor.GREEN + "Schön, dich zu sehen " + ChatColor.GOLD + "%s" + ChatColor.GOLD + ".",
            ChatColor.GREEN + "Willkommen " + ChatColor.GOLD + "%s" + ChatColor.GREEN + "! Sag hallo!",
            ChatColor.GREEN + "Willkommen " + ChatColor.GOLD + "%s" + ChatColor.GREEN + "! Wir hoffen, du hast Pizza mitgebracht.",
            ChatColor.GREEN + "Heißen wir " + ChatColor.GOLD + "%s" + ChatColor.GREEN + " herzlich willkommen!",
            ChatColor.GREEN + "print(\"hello " + ChatColor.GOLD + "%s" + ChatColor.GREEN + "\")"
    };

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        playerJoinEvent.setJoinMessage(getRandomJoinMessage(player.getName()));
    }

    private String getRandomJoinMessage(String playerName) {
        int rnd = (int) (Math.random() * messages.length);
        return String.format(messages[rnd], playerName);
    }
}
