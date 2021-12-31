package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BroadcastCoordinatesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // send message to all players
            SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
            Date date = new Date(System.currentTimeMillis());
            // add 1 hour to the date
            date.setTime(date.getTime() + 3600000);
            String currentTime = formatter.format(date);
            player.getServer().broadcastMessage(ChatColor.DARK_GRAY + "[" + currentTime + "] " + ChatColor.GOLD + player.getName() + ChatColor.DARK_GRAY + "'s coordinates are: " + ChatColor.RED + player.getLocation().getBlockX() + " " + ChatColor.GREEN + player.getLocation().getBlockY() + " " + ChatColor.BLUE + player.getLocation().getBlockZ());
        } else {
            commandSender.sendMessage(String.format("%sCommand can only be executed by a player", ChatColor.RED));
        }
        return false;
    }
}
