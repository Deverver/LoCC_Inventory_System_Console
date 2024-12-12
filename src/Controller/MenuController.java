package Controller;

import Controller.InventoryManager;

import java.util.Scanner;

public class MenuController {
    public static Scanner input = new Scanner(System.in);

    public static boolean menu(boolean run){
        while (run) {
            System.out.println("To show inventory contents press 1");
            System.out.println("To go on an adventure press 2");
            System.out.println("To sell an item press 3");
            System.out.println("To upgrade bag space 4");
            System.out.println("To exit program press 5");

            switch (input.nextInt()) {
                case 1:
                    if(inventory.isEmpty()){
                        System.out.println("Inventory is empty");
                    } else {
                        InventoryManager.showInventory();
                        System.out.println("To sort inventory press 1");
                        System.out.println("To search for an item press 2");
                        if (input.nextInt() == 1) {
                            System.out.println("how would you like it sorted:"+"\n"+"alphabetical press 1:"+"\n"+"weight press 2:"+"\n"+"value press 3:");
                            if (input.nextInt() == 1) {
                                InventoryManager.sortInventoryAlpha();
                            } else if (input.nextInt() == 2) {
                                InventoryManager.sortInventoryWeight();
                            } else if (input.nextInt() == 3) {
                                InventoryManager.sortInventoryValue();
                            }
                            InventoryManager.showInventory();
                        } else if (input.nextInt() == 2) {
                            InventoryManager.searchInventory();
                        }
                    }
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Thank you come again:");
                    run = false;

                    break;

                case 6:

                    break;
            }
        }
       return run;
    }


}
