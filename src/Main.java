import Controller.InventoryManager;
import Controller.MenuController;
import Controller.Scenario;
import DbController.DatabaseConnection;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            DatabaseConnection.createFiles();
            Scenario.createScenarioFiles();
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage());
        } catch (Exception e3) {
            e3.printStackTrace();
        }

        boolean run = true;
        MenuController.menu(run);


    }
}