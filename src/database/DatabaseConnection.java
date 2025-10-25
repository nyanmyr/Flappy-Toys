
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "";
        String username = "root";
        String password = ""; // Replace with your XAMPP MySQL password
        
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
