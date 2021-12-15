package de.dinario.sauerlandIntEssentials;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.block.Block;
import java.util.ArrayList;
import org.bukkit.entity.EntityType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class DestroySpawnerListener implements Listener {
    // event to detect destroying of spawners
    @EventHandler
    public void onSpawnerDestroy(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location blockLoc = block.getLocation();
        // check if the block is a spawner
        if (event.getBlock().getType().toString().contains("SPAWNER")) {
            // check if player holds a diamond pickaxe with silk touch
            if (event.getPlayer().getItemInHand().getType().toString().contains("DIAMOND_PICKAXE") && event.getPlayer().getItemInHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) == 1) {
                event.setExpToDrop(0);

                CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

                // Das Item erstellen, was gedroppt werden kann
                ItemStack dropItem = new ItemStack(Material.SPAWNER, 1);
                // Metadaten des Items bekommen
                ItemMeta meta = dropItem.getItemMeta();

                // Den Namen des zu spawnenden Mobs bekommen
                EntityType spawnedType = creatureSpawner.getSpawnedType();
                String creatureTypeName = spawnedType.name().toLowerCase();

                // Den ersten Buchstaben groß, den Rest klein lassen
                creatureTypeName = creatureTypeName.substring(0,1).toUpperCase() + creatureTypeName.substring(1);
                meta.setDisplayName(ChatColor.RESET + creatureTypeName + " Spawner");

                // Eine Liste von lores erstellen und eine mit dem Namen des zu spawnenden Mobs hinzufügen
                ArrayList<String> lore = new ArrayList<>();
                lore.add(creatureTypeName);
                meta.setLore(lore);
                dropItem.setItemMeta(meta);

                // Das Item an der Position des abgebauten Blocks spawnen
                player.getWorld().dropItemNaturally(blockLoc, dropItem);
            }
        }
    }
}
