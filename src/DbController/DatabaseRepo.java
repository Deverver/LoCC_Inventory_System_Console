package DbController;

import Controller.Scenario;
import ModelController.Armor;
import ModelController.Item;

import java.sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepo {

    public List<Scenario> scenario_list = new ArrayList<>();
    public List<Item> item_list = new ArrayList<>();


    //region Scenario commands
    public static void uploadToScenarios() {
        String filePath = "scenario_data";
        String sql = "INSERT INTO scenarios (scenarioType, scenarioName, scenarioDescription) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader reader1 = new BufferedReader(new FileReader(filePath));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String line;
            while ((line = reader1.readLine()) != null) {
                // Since we are taking data from a file and not an Object, we split the String so we can use each part
                String[] values = line.split(",");
                String TYPE = values[0];
                String NAME = values[1];
                String DESCRIPTION = values[2];

                preparedStatement.setString(1, TYPE);
                preparedStatement.setString(2, NAME);
                preparedStatement.setString(3, DESCRIPTION);
                // Learned why we have to use a Batch, next comment
                preparedStatement.addBatch();
            }
            reader1.close();
            // Learned that you have to do a Batch instead of an Update, this is because our rowsInserted is an array -
            // this also makes it easier to get info out like how many Entries we have put into the DB
            int[] rowsInserted = preparedStatement.executeBatch();

            System.out.println(rowsInserted.length + " rows inserted successfully.");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
        //CRUD operations for Scenarios

    public void createScenario(Scenario scenario) {
        // In order to transfer data to the DB we have to make our object readable to MySQL
        // We do this by choosing where our object data goes in our SQL Query
        String sql = "INSERT INTO scenarios (scenario_type, scenario_name, scenario_description) VALUES (?, ?, ?)";


        // In order to do anything in our DB we have to make a connection
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Selects where the data goes into our query
            preparedStatement.setString(1, scenario.getScenario_type());
            preparedStatement.setString(2, scenario.getScenario_name());
            preparedStatement.setString(3, scenario.getScenario_description());

            // Should "do" execute the query, and report if something went wrong
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(scenario.getScenario_type() + " Scenario " + scenario.getScenario_name() + " has been created in DB");
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }// createScenario End

    public void updateScenario(Scenario scenario, int DBIndex) {
        // In order to update in the DB, we have to provide and SET new data, and choose WHERE it goes
        String sql = "UPDATE scenarios SET scenario_type = ?, scenario_name = ?, scenario_description = ?, WHERE id = "+ DBIndex;

        // We have to make a new connection to our DB
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, scenario.getScenario_name());
            preparedStatement.setString(2, scenario.getScenario_type());
            preparedStatement.setString(3, scenario.getScenario_description());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Scenario " + scenario.getScenario_name() + "Type: " + scenario.getScenario_type() + " has been updated in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// updateScenario End

    // Same as updating we have to choose which game to delete via ID
    public void deleteScenario(int DBIndex) {
        String sql = "DELETE FROM scenarios WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, DBIndex);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Scenario on DBIndex: " + DBIndex + " has been deleted from the DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// deleteScenario End

   public List<Scenario> readScenario(int DBIndex) {
        String sql = "SELECT * FROM scenarios WHERE id = ?";
        scenario_list.clear();// clears list so it can be reused


       try (Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

           System.out.println("Reading scenario...");

           int primaryKey = resultSet.getInt("id");
           String scenario_type = resultSet.getString("scenarioType");
           String scenario_name = resultSet.getString("scenarioName");
           String scenario_description = resultSet.getString("scenarioDescription");

           scenario_list.add(new Scenario(primaryKey, scenario_type, scenario_name, scenario_description));

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return scenario_list;
   }// readScenario End

   public List<Scenario> readALLScenarios() {
        String sql = "SELECT * FROM scenarios";
        scenario_list.clear();// clears list so it can be reused
      /*
      The reason we use a Statement is in order to transfer the read data from the DB into a result-set
      that we can read from separately.
      this way we can create object from the stored data from the ResultSet.
      */
       try (Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

           // Loops through the resultset table, and creates the Scenario objects
           while (resultSet.next()) {
               int primaryKey = resultSet.getInt("id");
               String scenario_type = resultSet.getString("scenarioType");
               String scenario_name = resultSet.getString("scenarioName");
               String scenario_description = resultSet.getString("scenarioDescription");

               scenario_list.add(new Scenario(primaryKey, scenario_type, scenario_name, scenario_description));
           }// WLoop End
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return scenario_list;
   }// readScenario End
    //endregion


    //region Item commands
    public static void uploadToItems() {
        String filePath = "item_data";
        String sql = "INSERT INTO items (itemType, itemName, itemDescription, itemWeight, itemValue) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String line;
            while ((line = reader2.readLine()) != null) {
                String[] values = line.split(",");
                String TYPE = values[0];
                String NAME = values[1];
                String DESCRIPTION = values[2];
                double WEIGHT = Double.parseDouble(values[3]);
                double VALUE = Double.parseDouble(values[4]);

                preparedStatement.setString(1, TYPE);
                preparedStatement.setString(2, NAME);
                preparedStatement.setString(3, DESCRIPTION);
                preparedStatement.setDouble(3,WEIGHT);
                preparedStatement.setDouble(4,VALUE);

                preparedStatement.addBatch();
            }
            reader2.close();

            int[] rowsInserted = preparedStatement.executeBatch();
            System.out.println(rowsInserted.length + " rows inserted successfully.");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// uploadToItems End


    //CRUD operations for Items
    public void createItem(Item item){
        String sql = "INSERT INTO items (itemType, itemName, itemDescription, itemWeight, itemValue) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, item.getItem_type());
            preparedStatement.setString(2, item.getItem_name());
            preparedStatement.setString(3, item.getItem_description());
            preparedStatement.setDouble(4, item.getItem_weight());
            preparedStatement.setDouble(5, item.getItem_value());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Item: " + item.getItem_name() + " type: " + item.getItem_type() + " has been created in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// createItem End

    public void readItem(){

    }// readItem End

    public void readAllItems(){

    }// readAllItems End

    public void updateItem(){

    }// updateItem End

    public void deleteItem(){

    }// deleteItem End
    //endregion




}// DatabaseRepo End
