package DbController;

import Controller.ScenarioManager;
import Model.*;

import java.sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepo {
    public List<ScenarioManager> scenario_Manager_list = new ArrayList<>();
    public List<Item> item_list = new ArrayList<>();

    //region Scenario Commands
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

    public void createScenario(ScenarioManager scenarioManager) {
        // In order to transfer data to the DB we have to make our object readable to MySQL
        // We do this by choosing where our object data goes in our SQL Query
        String sql = "INSERT INTO scenarios (scenario_type, scenario_name, scenario_description) VALUES (?, ?, ?)";


        // In order to do anything in our DB we have to make a connection
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Selects where the data goes into our query
            preparedStatement.setString(1, scenarioManager.getScenario_type());
            preparedStatement.setString(2, scenarioManager.getScenario_name());
            preparedStatement.setString(3, scenarioManager.getScenario_description());

            // Should "do" execute the query, and report if something went wrong
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(scenarioManager.getScenario_type() + " Scenario " + scenarioManager.getScenario_name() + " has been created in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// createScenario End

    public void updateScenario(ScenarioManager scenarioManager, int DBIndex) {
        // In order to update in the DB, we have to provide and SET new data, and choose WHERE it goes
        String sql = "UPDATE scenarios SET scenario_type = ?, scenario_name = ?, scenario_description = ?, WHERE id = " + DBIndex;

        // We have to make a new connection to our DB
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, scenarioManager.getScenario_name());
            preparedStatement.setString(2, scenarioManager.getScenario_type());
            preparedStatement.setString(3, scenarioManager.getScenario_description());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Scenario " + scenarioManager.getScenario_name() + "Type: " + scenarioManager.getScenario_type() + " has been updated in DB");
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

    public List<ScenarioManager> readScenario(int DBIndex) {
        String sql = "SELECT * FROM scenarios WHERE id = ?";
        scenario_Manager_list.clear();// clears list so it can be reused

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Reading scenario...");

            int primaryKey = resultSet.getInt("id");
            String scenario_type = resultSet.getString("scenarioType");
            String scenario_name = resultSet.getString("scenarioName");
            String scenario_description = resultSet.getString("scenarioDescription");

            scenario_Manager_list.add(new ScenarioManager(primaryKey, scenario_type, scenario_name, scenario_description));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scenario_Manager_list;
    }// readScenario End

    public List<ScenarioManager> readALLScenarios() {
        String sql = "SELECT * FROM scenarios";
        scenario_Manager_list.clear();// clears list so it can be reused
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

                scenario_Manager_list.add(new ScenarioManager(primaryKey, scenario_type, scenario_name, scenario_description));
            }// WLoop End
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scenario_Manager_list;
    }// readScenario End

    public ScenarioManager readRandomScenarioFromDB() throws SQLException {
        String sql = "SELECT * FROM scenarios ORDER BY RAND() LIMIT 1";
        scenario_Manager_list.clear();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            ScenarioManager returnedScenarioManager;

            while (resultSet.next()) {
                int primaryKey = resultSet.getInt("id");
                String scenario_type = resultSet.getString("scenarioType");
                String scenario_name = resultSet.getString("scenarioName");
                String scenario_description = resultSet.getString("scenarioDescription");

                scenario_Manager_list.add(new ScenarioManager(primaryKey, scenario_type, scenario_name, scenario_description));

            }
            return (ScenarioManager) scenario_Manager_list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Item Commands
    public void createItem(Item item) {
        String sql = "INSERT INTO itemList (itemType, itemName, itemDescription, itemWeight, itemValue) VALUES (?, ?, ?, ?, ?)";

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


    public Item itemBuilder(int buildItemID, String buildItemName, String buildItemType, String buildItemDescription, double buildItemWeight, double buildItemValue, boolean buildItemStackable) {
        Item buildItem = null;
        int itemID = buildItemID;
        String itemName = buildItemName;
        String itemType = buildItemType;
        String itemDescription = buildItemDescription;
        double itemWeight = buildItemWeight;
        double itemValue = buildItemValue;
        boolean itemStackable = buildItemStackable;

        switch (itemType) {
            case "Weapon":
                buildItem = new Weapon(itemID, itemType, itemName, itemDescription, itemWeight, itemValue, 1);
                break;
            case "Helmet":
                buildItem = new Armor(itemID, itemType, itemName, itemDescription, itemWeight, itemValue);
                break;
            case "Shoulder":
                buildItem = new Armor(itemID, itemType, itemName, itemDescription, itemWeight, itemValue);
                break;
            case "Chest":
                buildItem = new Armor(itemID, itemType, itemName, itemDescription, itemWeight, itemValue);
                break;
            case "Legs":
                buildItem = new Armor(itemID, itemType, itemName, itemDescription, itemWeight, itemValue);
                break;
            case "Boots":
                buildItem = new Armor(itemID, itemType, itemName, itemDescription, itemWeight, itemValue);
                break;
            case "Consumable":
                buildItem = new Consumable(itemID, itemType, itemName, itemDescription, itemWeight, itemValue, 1, itemStackable);
                break;
            case "Resource":
                buildItem = new Ressource(itemID, itemType, itemName, itemDescription, itemWeight, itemValue, 1, itemStackable);
                break;
        }

        return buildItem;
    }


    public Item readRandomItemFromDB() {
        String sql = "SELECT * FROM itemList ORDER BY RAND() LIMIT 1";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            Item item1 = null;
            int itemID = resultSet.getInt("itemID");
            String itemName = resultSet.getString("itemName");
            String itemType = resultSet.getString("itemType");
            String itemDescription = resultSet.getString("itemDescription");
            double itemWeight = resultSet.getFloat("itemWeight");
            double itemValue = resultSet.getFloat("itemValue");
            boolean itemStackable = resultSet.getBoolean("itemStackable");

            item1 = itemBuilder(itemID, itemName, itemType, itemDescription, itemWeight, itemValue, itemStackable);

            return item1;

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }// readItem End

    public List<Item> readAllItemsFromDB() {
        item_list.clear();
        String sql = "SELECT * FROM itemList";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            int itemID = resultSet.getInt("itemID");
            String itemName = resultSet.getString("itemName");
            String itemType = resultSet.getString("itemType");
            String itemDescription = resultSet.getString("itemDescription");
            double itemWeight = resultSet.getFloat("itemWeight");
            double itemValue = resultSet.getFloat("itemValue");
            boolean itemStackable = resultSet.getBoolean("itemStackable");

            Item item2 = itemBuilder(itemID, itemName, itemType, itemDescription, itemWeight, itemValue, itemStackable);

            item_list.add(item2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item_list;
    }// readAllItems End

    public void updateItem(Item item, int DBIndex) {
        String sql = "UPDATE Itemlist SET itemName = ?, itemType = ?, itemDescription = ?, itemWeight = ?, itemValue = ?,itemStackable = ? WHERE itemID = " + DBIndex;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, item.getItem_name());
            preparedStatement.setString(2, item.getItem_type());
            preparedStatement.setString(3, item.getItem_description());
            preparedStatement.setDouble(4, item.getItem_weight());
            preparedStatement.setDouble(5, item.getItem_value());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Item " + item.getItem_name() + " has been updated in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// updateItem End

    public void deleteItem(Item item, int DBIndex) {
        String sql = "DELETE FROM Itemlist WHERE itemID = " + DBIndex;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, item.getItem_id());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Item from DB index: " + DBIndex + "has been deleted from DB");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting item with DB index: " + DBIndex);
            e.printStackTrace();
        }
    }// deleteItem End
    //endregion

    //region Inventory Commands
    public void createSavedInventory(Inventory savedinventory) {
        String sql = "INSERT INTO Savedinventory (Fkitemid, Amount,) VALUES (?, ?,)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, inventory.getFkitemid());
            preparedStatement.setInt(2, getAmount);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Your inventory has been opdated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Item> readSavedInventory() {
        ArrayList<Item> inventoryFromDB = new ArrayList<>();
        // skal lige finde ud af hvordan man får den til at read data fra et andet tabel

        String sql = "SELECT * FROM Savedinventory,Itemlist WHERE ID = itemID ";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // get data from or DB

        // Convert Data to a new Sql Query to call ItemList

        // get data from itemlist DB

        // build items from the data

        // add items to the presumed empty inventory on system startup


        return List.of();
    }

    public void updateSavedInventory(Inventory inventory) {
        String sql = "UPDATE Savedinventory SET Fkitemid=?, Amount=?, WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            /*
            preparedStatement.setInt(1, savedinventoreis.getFkitemid());
            preparedStatement.setString(2, savedinventoreis.getAmount());
            */

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("The inventory has been opdated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSavedInventory(int id) {
        String sql = "DELETE FROM Savedinventory WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Item has been deleted from inventory");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion


}// DatabaseRepo End
