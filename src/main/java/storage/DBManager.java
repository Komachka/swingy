package storage;

import java.sql.*;


public class DBManager {

    private static Connection connection = null;
    private static final String URL = "jdbc:sqlite:heroes.db";

    public DBManager() {
    }

    public Connection getDBConnection()
    {
        try {
            System.out.println("getConnection");
            System.out.println("getConnection is null");
            connection = DriverManager.getConnection(URL);
            System.out.println("Connection to database has been established.");

           /* if (connection == null)
            {
                System.out.println("getConnection is null");
                connection = DriverManager.getConnection(URL);
                System.out.println("Connection to database has been established.");
            }*/

        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public  void creteNewDataBase() throws SQLException {


        try(Connection conn = this.getDBConnection()) {
            if (conn != null)
            {
                try {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new db has been created");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println();
            }
        }


    }

    public  void createWeaponTable() {

        // SQL statement for creating a new table
        String CREATE_WEAPON_TABLE = "CREATE TABLE IF NOT EXISTS weapon (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	attack real NOT NULL\n"
                + ");";

        try (   Connection conn = this.getDBConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
                stmt.execute(CREATE_WEAPON_TABLE);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public  void insert(String name, int attack) {
        String sql = "INSERT INTO weapon (name, attack) VALUES(?,?)";

        try (Connection conn = this.getDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, attack);
            pstmt.executeUpdate();
            System.out.println("Data has been inserted");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        try {
            dbManager.creteNewDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbManager.createWeaponTable();

        //SWORD(50),
        //BOW(40),
        //PUGIO(30),
        //FLAX(20);
        dbManager.insert("SWORD", 50);
        dbManager.insert("BOW", 40);
        dbManager.insert("PUGIO", 30);
        dbManager.insert("FLAX", 20);
    }

}
