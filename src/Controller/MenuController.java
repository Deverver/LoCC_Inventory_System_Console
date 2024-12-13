package Controller;

import java.util.Scanner;

public class MenuController {
    private Scanner input = new Scanner(System.in);
    private final InventoryManager inventoryManager = new InventoryManager();
    private final ScenarioManager scenarioManager = new ScenarioManager();

    public boolean menu(boolean running) {


        //scenarioManager.commnads

        while (running) {
            System.out.println("""
                    __________________________________
                    ********** MENU SYSTEM **********
                    
                    These are your options:
                    [1] Show Inventory
                    [2] Do Scenario -Gain random item-
                    [3] Sell an Item
                    [4] Upgrade Bag space
                    [5] Exit program
                    __________________________________
                    """);

            switch (input.nextInt()) {
                case 1:
                    if (inventoryManager.inventoryEmpty()) {
                        System.out.println(inventoryManager.inventoryEmpty());
                    } else {
                        inventoryManager.showInventory();
                        System.out.println("""
                                __________________________________
                                ************ MENU [1] ************
                                
                                These are your options:
                                [1] Sort Inventory
                                [2] Search Inventory
                                __________________________________
                                """);
                        if (input.nextInt() == 1) {
                            System.out.println("""
                                    __________________________________
                                    ******** SUB MENU [1] [1] ********
                                         - - Inventory Sorting - -
                                    Select how Inventory is Sorted:
                                    [1] Sort by Alphabetical Order
                                    [2] Sort by Weight Value
                                    [3] Sort by Item Value                                
                                    """);

                            switch (input.nextInt()){
                                case 1:
                                    System.out.println("Sorted Inventory by Alphabetical Order...");
                                    inventoryManager.sortInventoryAlpha();
                                    break;
                                case 2:
                                    System.out.println("Sorted Inventory by Weight Value...");
                                    inventoryManager.sortInventoryWeight();
                                    break;
                                case 3:
                                    System.out.println("Sorted Inventory by Item Value...");
                                    inventoryManager.sortInventoryValue();
                                    break;
                            }
                            inventoryManager.showInventory();
                        } else if (input.nextInt() == 2) {
                            System.out.println("""
                                    __________________________________
                                    ******** SUB MENU [1] [2] ********
                                         - - Inventory Search - -
                                    
                                    Enter the name of the item you wish to search for
                                    """);
                            String userSearchInput = input.next();
                            System.out.println("Searching inventory for " + userSearchInput + "...");
                            inventoryManager.searchInventory(userSearchInput);
                        }
                    }
                    break;
                case 2:

                    break;

                case 3:
                    String itemToDelete = input.next();
                    inventoryManager.removeFromInventory(itemToDelete);

                    break;

                case 4:
                    try {
                        inventoryManager.upgradeInventory();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("""
                            __________________________________
                            ********** MENU SYSTEM **********
                              - - - - SHUTTING DOWN - - - - 
                            __________________________________
                            """);
                    running = false;
                    break;

                case 6:

                    break;
            }
        }
        return running;
    }


}
