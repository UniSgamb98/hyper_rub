package orodent.hyper_rub.utils;

import javafx.util.Pair;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class DBManager {
    private static final String protocol = "jdbc:derby://localhost:1527/";
    private static final String dbName = "Customer_Relation_Manager_Database";
    private Connection connection;
    FileOutputStream f;

    public DBManager(){
        Properties props = new Properties();
        props.put("user", "APP");
        props.put("password", "user1");

        try {
            f = new FileOutputStream("log.txt");
            //System.setErr(new PrintStream(f));
        } catch (Exception ignored) {}

        try {
            connection = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);
        } catch (SQLException e) {
            System.out.println("Failed connection to DB");
            printSQLException(e);
        }
    }

    /**
     * Prende il <code>Result</code> da loginDialog e verifica le credenziali contro il db
     * @param loginResult il Result di loginDialog
     * @return true se username e password coincidono a una entry del DB
     */
    public boolean autenticate(Pair<String,String> loginResult){
        boolean ret = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT PASSWORD FROM USERS WHERE USERNAME = ?"
            );
            statement.setString(1, loginResult.getKey());
            ResultSet rs = statement.executeQuery();
            rs.next();
            ret = Objects.equals(rs.getString(1), loginResult.getValue());
            rs.close();
            statement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ret;
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e)
    {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }
}
