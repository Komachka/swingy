package storage;

import java.sql.*;

//

public class DBManager {


    public DBManager() {
    }

    public  void creteNewDataBase() {

        try(Connection connection = DBConnection.getDBConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new db has been created");
        }
        catch (SQLException e)
        {
            System.err.println();
        }
    }

    public  void createWeaponTable() {

        // SQL statement for creating a new table
        String CREATE_WEAPON_TABLE = "CREATE TABLE IF NOT EXISTS weapon (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	attack real NOT NULL\n"
                + ");";

        try (Connection connection = DBConnection.getDBConnection();
             Statement stmt = connection.createStatement()) {
            // create a new table
            stmt.execute(CREATE_WEAPON_TABLE);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public  void insert(String name, int attack) {
        String sql = "INSERT INTO weapon (name, attack) VALUES(?,?)";

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        dbManager.creteNewDataBase();
        dbManager.createWeaponTable();

        //SWORD(50),
        //BOW(40),
        //PUGIO(30),
        //FLAX(20);
        dbManager.insert("SWORD", 500);
        dbManager.insert("BOW", 400);
        dbManager.insert("PUGIO", 300);
        dbManager.insert("FLAX", 200);
    }

}
