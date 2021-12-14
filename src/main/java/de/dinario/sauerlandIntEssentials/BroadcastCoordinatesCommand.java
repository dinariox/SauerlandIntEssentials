package de.dinario.sauerlandIntEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCoordinatesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // send message to all players
            player.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.DARK_GRAY + "'s coordinates are: " + ChatColor.RED + player.getLocation().getBlockX() + " " + ChatColor.GREEN + player.getLocation().getBlockY() + " " + ChatColor.BLUE + player.getLocation().getBlockZ());
        } else {
            commandSender.sendMessage(String.format("%sCommand can only be executed by a player", ChatColor.RED));
        }
        return false;
    }
}
