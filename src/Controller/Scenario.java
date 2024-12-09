package Controller;

public class Scenario implements ScenarioAction {
private int scenario_id;
private int scenario_type;
private String scenario_name;
private String scenario_description;

    public Scenario() {} //Empty constructor
    public Scenario(int scenario_id, int scenario_type, String scenario_name, String scenario_description) {
        this.scenario_id = scenario_id;
        this.scenario_type = scenario_type;
        this.scenario_name = scenario_name;
        this.scenario_description = scenario_description;
    }// Full Constructor

    //region Getters & Setters
    public int getScenario_id() {
        return scenario_id;
    }

    public void setScenario_id(int scenario_id) {
        this.scenario_id = scenario_id;
    }

    public int getScenario_type() {
        return scenario_type;
    }

    public void setScenario_type(int scenario_type) {
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


    @Override
    public void showScenarioInfo() {

    }

    @Override
    public void playScenario() {

    }
}// End
