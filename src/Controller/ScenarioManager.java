package Controller;

import DbController.DatabaseRepo;
import Model.Item;

import java.io.File;
import java.io.IOException;

public class ScenarioManager {
    private int scenario_id;
    private String scenario_type;
    private String scenario_name;
    private String scenario_description;

    public ScenarioManager() {
    } //Empty constructor

    public ScenarioManager(String scenario_type, String scenario_name, String scenario_description) {
        this.scenario_type = scenario_type;
        this.scenario_name = scenario_name;
        this.scenario_description = scenario_description;
    }// Constructor without the ID, we use this to create the Scenarios locally

    public ScenarioManager(int scenario_id, String scenario_type, String scenario_name, String scenario_description) {
        this.scenario_id = scenario_id;
        this.scenario_type = scenario_type;
        this.scenario_name = scenario_name;
        this.scenario_description = scenario_description;
    }// Full constructor with id, we use this if we chose to have scenarios in the DB, in order to know their primary key

    //region Getters & Setters
    public String getScenario_type() {
        return scenario_type;
    }

    public void setScenario_type(String scenario_type) {
        this.scenario_type = scenario_type;
    }

    public String getScenario_name() {
        return scenario_name;
    }

    public void setScenario_name(String scenario_name) {
        this.scenario_name = scenario_name;
    }

    public String getScenario_description() {
        return scenario_description;
    }

    public void setScenario_description(String scenario_description) {
        this.scenario_description = scenario_description;
    }
    //endregion

    public static void createScenarioFiles() throws IOException {
        File file3 = new File("scenario_data");
        File file4 = new File("item_data");
        if (!file3.exists() && !file4.exists()) {
            if (file3.createNewFile() && file4.createNewFile()) {
                System.out.println("File Created");
            } else {
                System.out.println("File Not Created");
            }
        } else {
            System.out.println("Files Already Exists");
        }
    }
    /*
    public ScenarioManager createScenario() {
        ScenarioManager randomScenarioManager;
        randomScenarioManager = DatabaseRepo.readRandomScenarioFromDB();

        return randomScenarioManager;
    }

    public void showScenarioInfo() {
        DatabaseRepo.readScenario();

    }

    public Item playScenario() {
        // Scenario is supposed to create a random item, that will be displayed in its description
        Item randomItem;
        randomItem = DatabaseRepo.readRandomItemFromDB();

        createScenario();
        //update scenario with Item
        showScenarioInfo();
        return randomItem;
    }
    */

}// End
