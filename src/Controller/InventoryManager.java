package Controller;

import Model.Inventory;
import Model.Item;

import java.util.Comparator;

public class InventoryManager {
    private double goldAmount;
    private double weightLimit = 50;
    private double currentWeight;
    private double remainingWeightCapacity;

    private int absoluteMaxCapacity = 192;
    private int upgradeValue = 32;
    private int currentMaxCapacity = 32;

    private Inventory inventory;

    // Constructor
    public InventoryManager( ) {
        this.inventory = new Inventory();
    }

    //region Getters & Setters
    public double getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        this.goldAmount = goldAmount;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getRemainingWeightCapacity() {
        return remainingWeightCapacity;
    }

    public void setRemainingWeightCapacity(double remainingWeightCapacity) {
        this.remainingWeightCapacity = remainingWeightCapacity;
    }

    public int getAbsoluteMaxCapacity() {
        return absoluteMaxCapacity;
    }

    public void setAbsoluteMaxCapacity(int absoluteMaxCapacity) {
        this.absoluteMaxCapacity = absoluteMaxCapacity;
    }

    public int getUpgradeValue() {
        return upgradeValue;
    }

    public void setUpgradeValue(int upgradeValue) {
        this.upgradeValue = upgradeValue;
    }

    public int getCurrentMaxCapacity() {
        return currentMaxCapacity;
    }

    public void setCurrentMaxCapacity(int currentMaxCapacity) {
        this.currentMaxCapacity = currentMaxCapacity;
    }
    //endregion

    public boolean addToInventory(Item item){
        return inventory.addItem(item);
    }

    public boolean removeFromInventory(String itemName){
        return inventory.removeItem(itemName);
    }

    public void showInventory(){
        System.out.println("""
                __________________________________
                ********* SYSTEM MESSAGE *********
                """);
        System.out.println("Inventory currently contains: ");
        int itemSlot = 1;
        for (Item item : inventory.getContainedItems()){
            System.out.println("Inventory space " + itemSlot + " contains " + item);
            itemSlot++;
        }// loop
        System.out.println("__________________________________");
    }

    public int getCurrentInventorySize() {
        return inventory.getSize();
    }

    public void sortInventoryAlpha(){
        inventory.getContainedItems().sort(Comparator.comparing(item -> item.getItem_name()));
    }

    public void sortInventoryWeight(){
        inventory.getContainedItems().sort(Comparator.comparingDouble(Item::getItem_weight));
    }
    public void sortInventoryValue(){
        inventory.getContainedItems().sort(Comparator.comparingDouble(Item::getItem_value));
    }

    public void searchInventory(String searchedName) {
        inventory.findContainedItemByName(searchedName);
    }

    public boolean inventoryFull(){
        if(inventory.getSize() == inventory.getContainedInventoryMaxCapacity()){
            System.out.println("""
                __________________________________
                ********* SYSTEM MESSAGE *********
                """);
            System.out.println("Inventory is full: " + inventory.getSize() + "/" + inventory.getContainedInventoryMaxCapacity());
            System.out.println("__________________________________");
            return true;
        }
        return false;
    }

    public boolean inventoryEmpty(){
        if(inventory.getSize() == 0){
            System.out.println("""
                __________________________________
                ********* SYSTEM MESSAGE *********
                """);
            System.out.println("Inventory is empty: " + inventory.getSize() + "/" + inventory.getContainedInventoryMaxCapacity());
            System.out.println("__________________________________");
            return true;
        }
        return false;
    }


    public int upgradeInventory(){
        if (inventory.getContainedInventoryMaxCapacity() <= absoluteMaxCapacity - upgradeValue) {
            inventory.setInventoryMaxCapacity(inventory.getContainedInventoryMaxCapacity() + upgradeValue);
            System.out.println("""
                __________________________________
                ********* SYSTEM MESSAGE *********
                """);
            System.out.println("Inventory max capacity is now " + inventory.getContainedInventoryMaxCapacity());
            System.out.println("__________________________________");
            return 1;
        }
        System.out.println("""
                __________________________________
                ********* SYSTEM MESSAGE *********
                """);
        System.out.println("Inventory could not be upgraded by: " + upgradeValue + " slots");
        System.out.println("Maximum input for this operation is currently: " + (absoluteMaxCapacity - inventory.getContainedInventoryMaxCapacity()));
        System.out.println("__________________________________");
        return 0;
    }

    public void refreshInventory(){
        setRemainingWeightCapacity(getWeightLimit() - getCurrentWeight());
    }



}// InventoryManager End
