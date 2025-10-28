package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseConnection {

    private static List<HashMap<String, String>> accounts = new ArrayList<>();
//    public static List<HashMap<String, String>> stats = new ArrayList<>();
//    public static List<HashMap<String, String>> characterUses = new ArrayList<>();

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/flappytoys?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = ""; // Replace with your XAMPP MySQL password

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void update() throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM accounts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            accounts.clear(); // Clear previous data

            while (resultSet.next()) {
                HashMap<String, String> userData = new HashMap<>();

                userData.put("username", resultSet.getString("username").trim());
//                System.out.println("Username: " + resultSet.getString("username"));

                userData.put("password", resultSet.getString("password"));
//                System.out.println("Password: " + resultSet.getString("password"));

                userData.put("pfp", String.valueOf(resultSet.getInt("pfp")));
//                System.out.println("Password: " + String.valueOf(resultSet.getInt("pfp")));

                accounts.add(userData);
            }
            System.out.println("Database fetched accounts successfully!");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static boolean authenticate(String username, String password) {

        for (HashMap<String, String> account : accounts) {
            if (account.get("username").equals(username) && account.get("password").equals(password)) {
                System.out.println("Authentication successful.");
                return true;
            }
        }

        System.out.println("Authentication failure.");
        return false;
    }

    public static int getPfp(String username) {
        for (HashMap<String, String> account : accounts) {
            if (account.get("username").equals(username)) {
                System.out.println("Pfp retrieved successfully.");
                return Integer.parseInt(account.get("pfp"));
            }
        }
        System.out.println("Pfp retrieved unsuccessfully.");
        return 0;
    }
}
