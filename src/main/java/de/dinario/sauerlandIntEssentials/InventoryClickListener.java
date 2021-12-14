package de.dinario.sauerlandIntEssentials;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClick() == ClickType.SHIFT_RIGHT) {
            // prevent the default action
            event.setCancelled(true);
            try {
                if (event.getClickedInventory().getType() == InventoryType.CHEST || event.getClickedInventory().getType() == InventoryType.PLAYER) {
                    sortInventory(event.getClickedInventory());
                }
            } catch (Exception e) {
                // Whatever
                System.out.println(e);
            }
        }
    }

    private void sortInventory(Inventory inventory) {
        ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
        InventoryType invType = inventory.getType();
        if (invType == InventoryType.PLAYER) {
            for (int i = 9; i < inventory.getSize() - 5; i++) {
                ItemStack item = inventory.getItem(i);
                if (item == null) {
                    item = new ItemStack(Material.AIR);
                }
                itemList.add(item);
                inventory.clear(i);
            }
        } else {
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item == null) {
                    item = new ItemStack(Material.AIR);
                }
                itemList.add(item);
                inventory.clear(i);
            }
        }
        ArrayList<ItemStack> combinedList = combineItems(itemList);
        ArrayList<ItemStack> sortedList = bubbleSort(combinedList);
        sortedList = combineItems(sortedList);
        int it = invType == InventoryType.PLAYER ? 9 : 0;
        for (ItemStack item : sortedList) {
            inventory.setItem(it, item);
            it++;
        }
        itemList.clear();
        sortedList.clear();
    }

    private ArrayList<ItemStack> combineItems(ArrayList<ItemStack> itemList) {
        ArrayList<ItemStack> combinedItem = itemList;
        for (int i = 0; i < combinedItem.size() - 1; i++) {
            for (int j = 1; j < combinedItem.size() - 1; j++) {
                if (combinedItem.get(i).getType() == combinedItem.get(j).getType() && combinedItem.get(i).getType() != Material.AIR && i != j) {
                    if (combinedItem.get(i).getAmount() + combinedItem.get(j).getAmount() <= combinedItem.get(i).getMaxStackSize()) {
                        combinedItem.get(i).setAmount(combinedItem.get(i).getAmount() + combinedItem.get(j).getAmount());
                        combinedItem.get(j).setType(Material.AIR);
                    } else {
                        if (combinedItem.get(i).getAmount() != combinedItem.get(i).getMaxStackSize() && combinedItem.get(j).getAmount() != combinedItem.get(j).getMaxStackSize()) {
                            combinedItem.get(j).setAmount(combinedItem.get(i).getAmount() + combinedItem.get(j).getAmount() - combinedItem.get(i).getMaxStackSize());
                            combinedItem.get(i).setAmount(combinedItem.get(i).getMaxStackSize());
                        }
                    }
                }
            }
        }
        return combinedItem;
    }

    private ArrayList<ItemStack> bubbleSort(ArrayList<ItemStack> itemList) {
        ArrayList<ItemStack> sortedItemlist = itemList;
        for (int i = 0; i < sortedItemlist.size() - 1; i++) {
            for (int j = 1; j < sortedItemlist.size() - 1; j++) {
                if (compare(sortedItemlist.get(j - 1), sortedItemlist.get(j)) < 0) {
                    ItemStack temp = itemList.get(j - 1);
                    sortedItemlist.set(j - 1, sortedItemlist.get(j));
                    sortedItemlist.set(j, temp);
                }
            }
        }
        for (int i = sortedItemlist.size() - 1; i >= 0; i--) {
            if (sortedItemlist.get(i).getType() == Material.AIR) {
                sortedItemlist.remove(i);
                sortedItemlist.add(new ItemStack(Material.AIR));
            }
        }
        return sortedItemlist;
    }


    private int compare(ItemStack itemStack1, ItemStack itemStack2) {
        return (itemName(itemStack2)).compareTo(itemName(itemStack1));
    }

    private String itemName(ItemStack item) {
        return item.getType().name();
    }

}
