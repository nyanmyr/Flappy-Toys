package database;

import game.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import toys.ToyCharacter;

public class DatabaseConnection {

    private static List<HashMap<String, String>> accounts = new ArrayList<>();

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/flappytoys?serverTimezone=UTC";
        String username = "root";
        String password = ""; // Replace with your XAMPP MySQL password

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static String getMostUsedCharacter() {
        String MostUsedCharacters;

        try (Connection connection = getConnection()) {
            String query = String.format("SELECT * FROM character_uses WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet set = prep.executeQuery();

            MostUsedCharacters = "";
            int highestUses = 0;
            
            while (set.next()) {
                if (set.getInt("uses") == highestUses) {
                    MostUsedCharacters += ", " + set.getString("character_name").substring(0, 1).toUpperCase() 
                            + set.getString("character_name").substring(1);
                } else if (set.getInt("uses") > highestUses) {
                    MostUsedCharacters = "";
                    highestUses = set.getInt("uses");
                    MostUsedCharacters += set.getString("character_name").substring(0, 1).toUpperCase() 
                            + set.getString("character_name").substring(1);
                }
            }

            connection.close();
            prep.close();
            set.close();
        } catch (Exception e) {
            MostUsedCharacters = "None";
            System.out.println("Database error: " + e.getMessage());
        }

        return MostUsedCharacters;
    }

    public static List<Object[]> getStatsData() {
        List<Object[]> stats = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM stats";

            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet set = prep.executeQuery();

            while (set.next()) {
                Object[] record = {"username", "highest_score", "total_score",
                    "highest_tokens_collected", "total_tokens", "total_charges_used"};

                record[0] = set.getString("username");
                record[1] = set.getInt("highest_score");
                record[2] = set.getInt("total_score");
                record[3] = set.getInt("highest_tokens_collected");
                record[4] = set.getInt("total_tokens");
                record[5] = set.getInt("total_charges_used");

                stats.add(record);
            }

            connection.close();
            prep.close();
            set.close();
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return stats;
    }

    // <editor-fold desc="record saving">
    public static void saveRecord(int score, int tokens, int charges, ToyCharacter toyCharacter) {
        // save score
        int retrievedHighScore = getHighestScore();
        System.out.println("score: " + score);
        System.out.println("retrievedHighScore: " + retrievedHighScore);
        if (retrievedHighScore >= 0 && score > retrievedHighScore) {
            saveHighestScore(score);
        }
        saveTotalScore(score);

        // save tokens collected
        int retrievedHighestTokensCollected = getHighestTokensCollected();
        if (retrievedHighestTokensCollected >= 0 && tokens > retrievedHighestTokensCollected) {
            saveHighestTokensCollected(tokens);
        }
        saveTotalTokensCollected(tokens);

        saveTotalChargesUsed(charges);

        saveCharacterUses(toyCharacter);

        // save charges used
        System.out.println("Record saved successfully.");
    }

    public static int getTotalChargesUsed() {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT total_charges_used FROM stats WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            ResultSet set = prep.executeQuery();

            int highestScore = -1;
            if (set.next()) {
                highestScore = set.getInt("total_charges_used");
            }

            connection.close();
            prep.close();
            set.close();

            System.out.println("Total charges used retrieved.");
            return highestScore;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    public static int getTotalTokens() {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT total_tokens FROM stats WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            ResultSet set = prep.executeQuery();

            int highestScore = -1;
            if (set.next()) {
                highestScore = set.getInt("total_tokens");
            }

            connection.close();
            prep.close();
            set.close();

            System.out.println("Total tokens retrieved.");
            return highestScore;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    public static int getTotalScore() {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT total_score FROM stats WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            ResultSet set = prep.executeQuery();

            int highestScore = -1;
            if (set.next()) {
                highestScore = set.getInt("total_score");
            }

            connection.close();
            prep.close();
            set.close();

            System.out.println("Total score retrieved.");
            return highestScore;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    public static int getHighestScore() {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT highest_score FROM stats WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            ResultSet set = prep.executeQuery();

            int highestScore = -1;
            if (set.next()) {
                highestScore = set.getInt("highest_score");
            }

            connection.close();
            prep.close();
            set.close();

            System.out.println("Highest score retrieved.");
            return highestScore;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    public static int getHighestTokensCollected() {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT highest_tokens_collected FROM stats WHERE username = '%s'",
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            ResultSet set = prep.executeQuery();

            int highestTokensCollected = -1;
            if (set.next()) {
                highestTokensCollected = set.getInt("highest_tokens_collected");
            }

            connection.close();
            prep.close();
            set.close();

            System.out.println("Highest tokens collected retrieved.");
            return highestTokensCollected;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    private static void saveHighestScore(int score) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE stats SET highest_score = %d WHERE username = '%s'",
                    score,
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Highest score saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void saveTotalScore(int score) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE stats SET total_score = (total_score + %d) WHERE username = '%s'",
                    score,
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Total score saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void saveHighestTokensCollected(int tokensCollected) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE stats SET highest_tokens_collected = %d WHERE username = '%s'",
                    tokensCollected,
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Hoghest score saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void saveTotalTokensCollected(int tokensCollected) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE stats SET total_tokens = (total_tokens + %d) WHERE username = '%s'",
                    tokensCollected,
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Total tokens collected saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void saveTotalChargesUsed(int charges) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE stats SET total_charges_used = (total_charges_used + %d) WHERE username = '%s'",
                    charges,
                    Account.getSavedUsername());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Total charges used saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void saveCharacterUses(ToyCharacter toyCharacter) {
        try (Connection connection = getConnection()) {
            String query = String.format("UPDATE character_uses SET uses = (uses + 1) WHERE username = '%s' AND character_name = '%s'",
                    Account.getSavedUsername(),
                    toyCharacter.toString().toLowerCase());

            PreparedStatement prep = connection.prepareStatement(query);

            prep.executeUpdate();

            connection.close();
            prep.close();
            System.out.println("Character use saved successfully.");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    // </editor-fold>

    // <editor-fold desc="account stuff">
    public static boolean update() throws SQLException {
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
            return true;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public static boolean addAccount(String username, String password, int pfp) throws SQLException {
        try (Connection connection = getConnection()) {
            if (!isUsernameAvailable(username)) {
                return false;
            }

            // queries
            String accountsQuery = "INSERT INTO accounts VALUES (?, ?, ?)";
            String statsQuery = "INSERT INTO stats VALUES  (?, 0, 0, 0, 0, 0)";

            String teddycopterUsesQuery = "INSERT INTO character_uses VALUES (?, \"teddycopter\", 0)";
            String rocketronUsesQuery = "INSERT INTO character_uses VALUES (?, \"rocketron\", 0)";
            String foldyUsesQuery = "INSERT INTO character_uses VALUES (?, \"foldy\", 0)";
            String dancerinaUsesQuery = "INSERT INTO character_uses VALUES (?, \"dancerina\", 0)";
            String sergeantUsesQuery = "INSERT INTO character_uses VALUES (?, \"sergeant\", 0)";
            String allicornUsesQuery = "INSERT INTO character_uses VALUES (?, \"allicorn\", 0)";

            // prepared statements
            PreparedStatement accountsPrep = connection.prepareStatement(accountsQuery);
            PreparedStatement statsPrep = connection.prepareStatement(statsQuery);

            PreparedStatement teddycopterUsesPrep = connection.prepareStatement(teddycopterUsesQuery);
            PreparedStatement rocketronUsesPrep = connection.prepareStatement(rocketronUsesQuery);
            PreparedStatement foldyUsesPrep = connection.prepareStatement(foldyUsesQuery);
            PreparedStatement dancerinaUsesPrep = connection.prepareStatement(dancerinaUsesQuery);
            PreparedStatement sergeantUsesPrep = connection.prepareStatement(sergeantUsesQuery);
            PreparedStatement allicornUsesPrep = connection.prepareStatement(allicornUsesQuery);

            // create account record
            accountsPrep.setString(1, username);
            accountsPrep.setString(2, password);
            accountsPrep.setInt(3, pfp);

            accountsPrep.executeUpdate();

            // create stats record
            statsPrep.setString(1, username);

            statsPrep.executeUpdate();

            // create character uses record
            teddycopterUsesPrep.setString(1, username);
            teddycopterUsesPrep.executeUpdate();

            rocketronUsesPrep.setString(1, username);
            rocketronUsesPrep.executeUpdate();

            foldyUsesPrep.setString(1, username);
            foldyUsesPrep.executeUpdate();

            dancerinaUsesPrep.setString(1, username);
            dancerinaUsesPrep.executeUpdate();

            sergeantUsesPrep.setString(1, username);
            sergeantUsesPrep.executeUpdate();

            allicornUsesPrep.setString(1, username);
            allicornUsesPrep.executeUpdate();
            connection.close();

            accountsPrep.close();
            statsPrep.close();

            teddycopterUsesPrep.close();
            rocketronUsesPrep.close();
            foldyUsesPrep.close();
            dancerinaUsesPrep.close();
            sergeantUsesPrep.close();
            allicornUsesPrep.close();
            update();
            return true;
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    private static boolean isUsernameAvailable(String username) {

        for (HashMap<String, String> account : accounts) {
            if (account.get("username").equals(username)) {
                System.out.println("Username is already taken.");
                return false;
            }
        }

        return true;
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
    // </editor-fold>
}
