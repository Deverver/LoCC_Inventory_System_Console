package Model;

import java.util.ArrayList;
import java.util.List;

/*
In essence an inventory needs to nothing more than just a list of Item objects, it could be rewards from defeating a monster or boss,
it could be a shops inventory or a rewards screen from a quest - in essence it is the same.
*/

public class Inventory {
    private int containedInventoryMaxCapacity = 32;
    private List<Item> containedItems;
    private List<Item> listedInventory;

    // Constructor
    public Inventory() {
        this.containedItems = new ArrayList<>();
    }

    //We need these since we have to upgrade our inventory's capacity
    //region Commands for controlling the inventory size
    public int getContainedInventoryMaxCapacity() {
        return containedInventoryMaxCapacity;
    }

    public int setInventoryMaxCapacity(int inventoryMaxCapacity) {
        if (containedInventoryMaxCapacity != inventoryMaxCapacity) {
            containedInventoryMaxCapacity = inventoryMaxCapacity;
            return 1;
        }
            return 0;
    }
    //endregion

    public int addItem(Item item) {
        if (containedItems.size() < containedInventoryMaxCapacity) {
            containedItems.add(item);
            return 1;
        } else {
            return 0;
        }
    }

    public int removeItem(String itemName) {
        if (findContainedItemByName(itemName) != -1) {
            containedItems.remove(findContainedItemByName(itemName));
            return 1;
        }else{
            return 0;
        }
    }

    public List<Item> getContainedItems() {
        listedInventory.clear();
        // We create a copy of the Inventory to show to users, this keeps the Inventory inside the InventoryManager in control, this should prevent external changes.
        listedInventory = new ArrayList<>(containedItems);
        return listedInventory;
    }

    public int findContainedItemByName(String searchedItemName) {
        for (Item item : containedItems) {
            if (item.getItem_name().equals(searchedItemName)) {
                return containedItems.indexOf(item);
            }
        }
        return -1;
    }

    public int getSize() {
        if (containedItems.size() <= containedInventoryMaxCapacity) {
            return containedItems.size();
        }else {
            return -1;
        }
    }


}// Inventory Class End
