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

    public void setInventoryMaxCapacity(int inventoryMaxCapacity) {
        containedInventoryMaxCapacity = inventoryMaxCapacity;
    }
    //endregion

    public boolean addItem(Item item) {
        if (containedItems.size() < containedInventoryMaxCapacity) {
            containedItems.add(item);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(String itemName) {
        return containedItems.remove(removeItem(itemName));
    }// not sure why but I cannot make an else statement to include an error, guess we will have to do so when the method is called

    public List<Item> getContainedItems() {
        listedInventory.clear();
        // We create a copy of the Inventory to show to users, this keeps the Inventory inside the InventoryManager in control, this should prevent external changes.
        listedInventory = new ArrayList<>(containedItems);
        return listedInventory;
    }

    public List<Item> findContainedItemByName(String searchedItemName) {
        listedInventory = new ArrayList<>();
        for (Item item : containedItems) {
            if (item.getItem_name().equals(searchedItemName)) {
                listedInventory.add(item);
            }
        }
        return listedInventory;
    }

    public int getSize() {
        return containedItems.size();
    }


}// Inventory Class End
