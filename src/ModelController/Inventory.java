package ModelController;

import java.lang.reflect.Array;

/*
In essence an inventory needs to nothing more than just a list of Item objects, it could be loot from a monster or boss,
it could be a shops inventory or a rewards screen from a quest - in essence it is the same.
*/
public class Inventory implements InventoryAction {
    private Array containedItems;


    @Override
    public void showInventory() {

    }

    @Override
    public void addToInventory() {

    }

    @Override
    public void removeFromInventory() {

    }

    @Override
    public void sortInventory() {

    }
}// End
