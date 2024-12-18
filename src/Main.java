import Controller.MenuController;
import Controller.ScenarioManager;
import DbController.DatabaseConnection;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();

        // in theory,



        try {
            DatabaseConnection.createFiles();
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage());
        } catch (Exception e3) {
            e3.printStackTrace();
        }

        boolean run = true;
        menuController.menu(run);


    }
}