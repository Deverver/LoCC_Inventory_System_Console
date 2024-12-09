package ModelController;

import Controller.InventoryManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
In essence an inventory needs to nothing more than just a list of Item objects, it could be rewards from defeating a monster or boss,
it could be a shops inventory or a rewards screen from a quest - in essence it is the same.
*/
public class Inventory {
    private int arrayInitialMaxRange;

    private ArrayList<Item> containedItems = new ArrayList<>(arrayInitialMaxRange);


    // Constructor
    public Inventory(int arrayInitialMaxRange, ArrayList<Item> containedItems) {
        this.arrayInitialMaxRange = arrayInitialMaxRange;
        this.containedItems = containedItems;
    }



}// End
