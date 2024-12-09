package DbController;

import ModelController.Armor;
import ModelController.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseRepo {
    public static Item createArmor() throws SQLException {


        String sql = "";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = new PreparedStatement(sql);
        ResultSet itemName = preparedStatement.getResultSet();

        Item armor = new Armor();
        return armor;
    }
}
