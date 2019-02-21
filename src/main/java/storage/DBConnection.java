package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:heroes.db";

    public static Connection getDBConnection()
    {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC"); // check for what is it
            connection = DriverManager.getConnection(URL);

        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println("SQLException Connection to database has not been established.");

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.err.println("ClassNotFoundException Connection to database has not been established.");
        }
        return connection;

    }
}
