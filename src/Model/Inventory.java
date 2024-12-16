package Model;

import Controller.SavedInventory;

import java.util.ArrayList;
import java.util.List;

/*
In essence an inventory needs to nothing more than just a list of Item objects, it could be rewards from defeating a monster or boss,
it could be a shops inventory or a rewards screen from a quest - in essence it is the same.
*/

public class Inventory {
    private int containedInventoryMaxCapacity = 32;
    private List<Item> containedItems;

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
        System.out.println("whee");
        if(containedItems.contains(item) && item.getItem_type().equals("Consumable")){
            ((Consumable)item).setItemAmount(+1);
            item.setItem_weight(item.getItem_weight() + item.getItem_weight());
        } else if (containedItems.contains(item) && item.getItem_type().equals("Resource")){
            ((Ressource)item).setItemAmount(+1);
            item.setItem_weight(item.getItem_weight() + item.getItem_weight());
        }

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

        // We create a copy of the Inventory to show to users, this keeps the Inventory inside the InventoryManager in control, this should prevent external changes.
        ArrayList<Item> listedInventory = new ArrayList<>(containedItems.size());
        for (Item item : containedItems) {
            listedInventory.add(item);
        }
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
        } else {
            return -1;
        }
    }
    public ArrayList<SavedInventory> createSavedInventory(){
        ArrayList<SavedInventory> savedInventory = new ArrayList<>();
        int itemCount = 1;
        for (Item item : containedItems) {
            if(item.getItem_type().equals("Consumable")){
                itemCount = ((Consumable)item).getItemAmount();
            } else if (item.getItem_type().equals("Resource")) {
                itemCount = ((Ressource)item).getItemAmount();
            }
            SavedInventory itemToBeSaved = new SavedInventory(item.getItem_id(), itemCount);
            savedInventory.add(itemToBeSaved);
        }
        return savedInventory;
    }

}// Inventory Class End
