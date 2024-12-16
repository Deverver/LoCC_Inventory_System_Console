package Controller;

import DbController.DatabaseRepo;
import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private Scanner input = new Scanner(System.in);
    private final InventoryManager inventoryManager = new InventoryManager();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final DatabaseRepo database = new DatabaseRepo();

    public boolean menu(boolean running) {

        //scenarioManager.commands

        while (running) {
            System.out.println("""
                    __________________________________
                    ********** MENU SYSTEM **********
                    
                    These are your options:
                    [1] Show Inventory
                    [2] Scenario Options -Gain random item-
                    [3] Sell an Item
                    [4] Upgrade Bag space
                    [5] Exit program
                    """);

            switch (input.nextInt()) {
                case 1:
                    if (inventoryManager.inventoryEmpty()) {
                        System.out.println(inventoryManager.inventoryEmpty());
                    } else {
                        int itemSlot = 1;
                        for (Item item : inventoryManager.showInventory()) {
                            if (item.getItem_type().equals("Consumable")) {
                                System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name() + " " + ((Consumable) item).getItemAmount());
                            } else if (item.getItem_type().equals("Resource")) {
                                System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name() + " " + ((Ressource) item).getItemAmount());
                            } else {
                                System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name());
                            }
                            itemSlot++;
                        }// loop

                        System.out.println("""
                                __________________________________
                                ************ MENU [1] ************
                                
                                These are your options:
                                [1] Sort Inventory
                                [2] Search Inventory
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

                            switch (input.nextInt()) {
                                case 1:
                                    System.out.println("Sorted Inventory by Alphabetical Order...");
                                    inventoryManager.sortInventoryAlpha();
                                    itemSlot = 1;
                                    for (Item item : inventoryManager.showInventory()) {
                                        System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name());
                                        itemSlot++;
                                    }
                                    break;
                                case 2:
                                    System.out.println("Sorted Inventory by Weight Value...");
                                    inventoryManager.sortInventoryWeight();
                                    itemSlot = 1;
                                    for (Item item : inventoryManager.showInventory()) {
                                        System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name());
                                        itemSlot++;
                                    }
                                    break;
                                case 3:
                                    System.out.println("Sorted Inventory by Item Value...");
                                    inventoryManager.sortInventoryValue();
                                    itemSlot = 1;
                                    for (Item item : inventoryManager.showInventory()) {
                                        System.out.println("Inventory space " + itemSlot + " contains " + item.getItem_type() + " " + item.getItem_name());
                                        itemSlot++;
                                    }
                                    break;
                            }// Sub Switch case
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
                // Case 1 End
                case 2:
                    System.out.println("""
                            __________________________________
                            ************ MENU [2] ************
                            
                            These are your options:
                            [1] Play Random Scenario
                            [2] Show All Scenarios
                            """);

                    if (input.nextInt() == 1) {
                        System.out.println("Playing Random Scenario...");
                        Item item = null;
                        try {
                            item = scenarioManager.playScenario();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(item.getItem_name() + " " + item.getItem_type() + " " + item.getItem_description());
                        System.out.println("""
                                __________________________________
                                ************ MENU [2] [1] ************
                                
                                These are your options:
                                [1] keep item
                                [2] discard item
                                """);
                        if (input.nextInt() == 1) {
                            inventoryManager.addToInventory(item);
                        } else {
                            break;
                        }

                        break;
                    } else if (input.nextInt() == 2) {
                        System.out.println("Displaying All Possible Scenarios.../n");
                        List<ScenarioManager> scenarios = database.readALLScenarios();
                        for (ScenarioManager scenario : scenarios) {
                            System.out.println(scenario.getScenario_name());
                            System.out.println(scenario.getScenario_type());
                            System.out.println(scenario.getScenario_description());
                        }


                        break;
                    }
                    break;
                // Case 2 Emd
                case 3:
                    System.out.println("""
                            __________________________________
                            ************ MENU [3] ************
                            
                            These are your options:
                            [1] Sell Item
                            [2] Sell All non equipped Items
                            """);
                    switch (input.nextInt()) {
                        case 1:
                            System.out.println("""
                                    __________________________________
                                    ******** SUB MENU [3] [1] ********
                                          - - Item Selling - -
                                    
                                    Enter the name of the item you wish to Sell
                                    """);
                            String itemToDelete = input.next();
                            Item searchedItem = inventoryManager.searchInventory(itemToDelete);
                            inventoryManager.setGoldAmount(inventoryManager.getGoldAmount() + searchedItem.getItem_value());
                            database.deleteSavedInventory(inventoryManager.searchInventory(itemToDelete).getItem_id());
                            inventoryManager.removeFromInventory(itemToDelete);
                            break;
                        case 2:
                            System.out.println("""
                                    __________________________________
                                    ******** SUB MENU [3] [2] ********
                                      - Sell All non equipped Items -
                                    
                                    Are you sure you wish to do this?
                                               (YES / NO)
                                    """);
                            if (input.next().toLowerCase().trim().equals("yes")) {
                                for (Item item : inventoryManager.showInventory()) {

                                    if (item instanceof Consumable || item instanceof Ressource) {
                                        inventoryManager.setGoldAmount(inventoryManager.getGoldAmount() + item.getItem_value());
                                        inventoryManager.removeFromInventory(item.getItem_name());
                                    } else if (item instanceof Armor || item instanceof Weapon) {

                                        if (!((Armor) item).isEquipped()) {
                                            inventoryManager.setGoldAmount(inventoryManager.getGoldAmount() + item.getItem_value());
                                            inventoryManager.removeFromInventory(item.getItem_name());
                                        } else if (!((Weapon) item).isEquipped()) {
                                            inventoryManager.setGoldAmount(inventoryManager.getGoldAmount() + item.getItem_value());
                                            inventoryManager.removeFromInventory(item.getItem_name());
                                        }
                                    }
                                }
                            }
                            break;

                    }
                case 4:
                    System.out.println("""
                            __________________________________
                            ************ MENU [4] ************
                            
                            These are your options:
                            [1] Upgrade Inventory Capacity
                            """);
                    switch (input.nextInt()) {
                        case 1:
                            System.out.println("Upgrading Inventory Capacity...");
                            try {
                                inventoryManager.upgradeInventory();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                case 5:
                    System.out.println("""
                            __________________________________
                            ********** MENU SYSTEM **********
                              - - - - SHUTTING DOWN - - - -
                            __________________________________
                            """);

                    running = false;
                    break;

            }
        }// main Switch case

        return running;
    }

}


// MenuController End
