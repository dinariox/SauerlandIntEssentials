package de.dinario.sauerlandIntEssentials;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.ArrayList;
public class BlockPlacedListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ArrayList<Material> seeds = new ArrayList<Material>();
        seeds.add(Material.BEETROOT_SEEDS);
        seeds.add(Material.MELON_SEEDS);
        seeds.add(Material.PUMPKIN_SEEDS);
        seeds.add(Material.SUGAR_CANE);
        seeds.add(Material.WHEAT_SEEDS);
        seeds.add(Material.CARROT);
        seeds.add(Material.POTATO);
        seeds.add(Material.NETHER_WART);
        seeds.add(Material.SWEET_BERRIES);
        seeds.add(Material.BONE_MEAL);
        if (event.getItemInHand().getAmount() == 1 && (event.getItemInHand().getType().isBlock() || seeds.contains(event.getItemInHand().getType()))) {
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

