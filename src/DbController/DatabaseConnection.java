package DbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:ExamensDB.db";

    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(URL);

        }
        catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }// Connection End

}
