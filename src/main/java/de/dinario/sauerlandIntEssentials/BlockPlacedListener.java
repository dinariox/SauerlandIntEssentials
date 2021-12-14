package de.dinario.sauerlandIntEssentials;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class BlockPlacedListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().getAmount() == 1 && event.getItemInHand().getType().isBlock()) {
            int brokenItem = -1;
            int replaceItem = -1;
            for (int i = 0; i < event.getPlayer().getInventory().getSize(); i++) {
                ItemStack item = event.getPlayer().getInventory().getItem(i);
                if (item == null) {
                    item = new ItemStack(Material.AIR);
                }
                if (!Objects.equals(event.getItemInHand(), event.getPlayer().getInventory().getItem(i)) && event.getItemInHand().getType() == item.getType()) {
                    replaceItem = i;
                }
                if (Objects.equals(event.getItemInHand(), event.getPlayer().getInventory().getItem(i))) {
                    brokenItem = i;
                }
            }
            if (brokenItem != -1 && replaceItem != -1) {
                event.getPlayer().getInventory().setItem(brokenItem, event.getPlayer().getInventory().getItem(replaceItem));
                event.getPlayer().getInventory().setItem(replaceItem, new ItemStack(Material.AIR));
            }
        }
    }
}

