package de.dinario.sauerlandIntEssentials;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DestroySpawnerListener implements Listener {
    // event to detect destroying of spawners
    @EventHandler
    public void onSpawnerDestroy(BlockBreakEvent event) {
        // check if the block is a spawner
        if (event.getBlock().getType().toString().contains("SPAWNER")) {
            // check if player holds a diamond pickaxe with silk touch
            if (event.getPlayer().getItemInHand().getType().toString().contains("DIAMOND_PICKAXE") && event.getPlayer().getItemInHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) == 1) {
                //drop the same spawner as EntityType
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
            }
        }
    }
}
