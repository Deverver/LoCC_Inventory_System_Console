package Controller;

import Controller.InventoryManager;

import java.util.Scanner;

public class MenuController {
    private Scanner input = new Scanner(System.in);
    private boolean menu(boolean running){
    InventoryManager player = new InventoryManager();


    while (running) {

        System.out.println("To show inventory contents press 1");
        System.out.println("To go on an adventure press 2");
        System.out.println("To sell an item press 3");
        System.out.println("To upgrade bag space 4");
        System.out.println("To exit program press 5");

        switch (input.nextInt()) {
            case 1:
                if(player.inventoryEmpty()){
                    System.out.println(player.inventoryEmpty());
                } else {
                    player.showInventory();
                    System.out.println("To sort inventory press 1");
                    System.out.println("To search for an item press 2");
                    if (input.nextInt() == 1) {
                        System.out.println("how would you like it sorted:"+"\n"+"alphabetical press 1:"+"\n"+"weight press 2:"+"\n"+"value press 3:");
                        if (input.nextInt() == 1) {
                            player.sortInventoryAlpha();
                        } else if (input.nextInt() == 2) {
                            player.sortInventoryWeight();
                        } else if (input.nextInt() == 3) {
                            player.sortInventoryValue();
                        }
                        player.showInventory();
                    } else if (input.nextInt() == 2) {
                        System.out.println("Please enter the name of the item you want to search for");
                        String userSearchInput = input.next();
                        player.searchInventory(userSearchInput);
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
                    running = false;

                    break;

                case 6:

                    break;
            }
        }
       return running;
    }


}
