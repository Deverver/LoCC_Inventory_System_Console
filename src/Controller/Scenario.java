package Controller;

public class Scenario {
private String scenario_type;
private String scenario_name;
private String scenario_description;

    public Scenario() {} //Empty constructor
    public Scenario(String scenario_type, String scenario_name, String scenario_description) {
        this.scenario_type = scenario_type;
        this.scenario_name = scenario_name;
        this.scenario_description = scenario_description;
    }// Full Constructor

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


    @Override
    public void showScenarioInfo() {

    }

    @Override
    public void playScenario() {


    }
}// End
