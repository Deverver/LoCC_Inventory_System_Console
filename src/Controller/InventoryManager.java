package Controller;


import ModelController.Item;
import java.util.ArrayList;
import java.util.Comparator;

public class InventoryManager {
    private double goldAmount;
    private double weightLimit;
    private double currentWeight;
    private double remainingWeightCapacity;

    private int upgradeValue = 32;
    private int inventoryMaxCapacity = 192;
    private int currentMaxCapacity = 32;

    public ArrayList<Item> inventory = new ArrayList<>(currentMaxCapacity);


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
    //endregion


    //region Inventory Command Methods
    public double getRemainingWeight() {
        setRemainingWeightCapacity(weightLimit - currentWeight);
        return remainingWeightCapacity;
    }

    public void showInventory() {

        try {
            for (Item item : inventory) {
                System.out.println(inventory.indexOf(item));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void removeFromInventory(int itemID){

        if (inventory.contains (itemID)) {
            inventory.remove(itemID);
        }else {
            System.out.println("Item not found in inventory");
        }

    }

    public void upgradeInventory(){
        if (currentMaxCapacity <= inventoryMaxCapacity ) {
            currentMaxCapacity = (currentMaxCapacity + upgradeValue);
        }else {
            System.out.println("This inventory is at capacity: " + currentMaxCapacity + " out of " + inventoryMaxCapacity);
        }
    }


    public void sortInventoryAlpha(){
        inventory.sort(Comparator.comparing(item -> item.getItem_name()));
    }

    public void sortInventoryWeight(){
        inventory.sort(Comparator.comparingDouble(Item::getItem_weight));
    }
    public void sortInventoryValue(){
        inventory.sort(Comparator.comparingDouble(Item::getItem_value));
    }

    public static void searchInventory() {

    }

    public ArrayList searchForItem(String searchedName){
        ArrayList<String> searchList = new ArrayList();

       try{
           for(Item item : inventory){
               if (item.getItem_name().equalsIgnoreCase(searchedName)){
                   searchList.add(String.valueOf(item));
               }
           }
       }
        catch(Exception e){
            e.printStackTrace();
        }
            return searchList;
    }
    public boolean inventoryFull(){
        if(inventory.size() == currentMaxCapacity){
            return true;
        }
        return false;
    }

    //endregion
    


}// End
