package storage;

import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import model.characthers.Hero;
import model.characthers.HeroClass;

import java.sql.*;
import java.util.ArrayList;

//

public class DBManager {


    public DBManager() {
        creteNewDataBase();
        createHeroesTable();
    }

    private   void creteNewDataBase() {

        try(Connection connection = DBConnection.getDBConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new db has been created");
        }
        catch (SQLException e)
        {
            System.err.println("creteNewDataBase");
            System.err.println(e.getMessage());
        }
        catch (NullPointerException ex)
        {
            System.err.println("creteNewDataBase");
            System.err.println(ex.getMessage());
        }

    }

    private  void createHeroesTable() {

        try (Connection connection = DBConnection.getDBConnection();
             Statement stmt = connection.createStatement()) {
            // create a new table
            System.out.println(HeroDataBaseContract.SQL_CREATE_TABLE);
            stmt.execute(HeroDataBaseContract.SQL_CREATE_TABLE);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public  void insert(String name, int attack) {
        /*String sql = "INSERT INTO weapon (name, attack) VALUES(?,?)";*/

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(HeroDataBaseContract.INSERT_INTO)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, attack);
            pstmt.executeUpdate();
            System.out.println("Data has been inserted");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public  void insertHero(Hero hero) {
        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(HeroDataBaseContract.INSERT_INTO)) {
            pstmt.setString(1, hero.getName());
            pstmt.setString(2, hero.getHeroClass().name());
            pstmt.setString(3, String.valueOf(hero.getLevel()));
            pstmt.setString(4, String.valueOf(hero.getExperience()));
            pstmt.setString(5, String.valueOf(hero.getAttack()));
            pstmt.setString(6, String.valueOf(hero.getDefense()));
            pstmt.setString(7, String.valueOf(hero.getWeapon().name()));
            pstmt.setString(8, String.valueOf(hero.getHelm().name()));
            pstmt.setString(9, String.valueOf(hero.getArmor().name()));
            pstmt.setString(10, String.valueOf(hero.getHitPoints()));
            pstmt.executeUpdate();
            System.out.println("Data has been inserted");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public ArrayList<Hero> selectAll()
    {
        ArrayList<Hero> heroes = new ArrayList<>();
        try (Connection connection = DBConnection.getDBConnection();
             Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(HeroDataBaseContract.SQL_SELECT_ALL)) {

            while (rs.next())
            {
                heroes.add(createHero(rs));
            }
            System.out.println("Data has been selected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return heroes;
    }

    private Hero createHero(ResultSet rs) {
        try {
            HeroClass heroClass = HeroClass.valueOf(rs.getString(HeroDataBaseContract.COLUMN_HEROCLASS));
            String name = rs.getString(HeroDataBaseContract.COLUMN_NAME);
            String level = rs.getString(HeroDataBaseContract.COLUMN_LEVEL);
            String experience = rs.getString(HeroDataBaseContract.COLUMN_EXPERIENCE);
            String attack = rs.getString(HeroDataBaseContract.COLUMN_ATTACK);
            String defense = rs.getString(HeroDataBaseContract.COLUMN_DEFENSE);
            String hitPoints  = rs.getString(HeroDataBaseContract.COLUMN_HP);
            Weapon weapon = Weapon.valueOf(rs.getString(HeroDataBaseContract.COLUMN_WEAPON));
            Helm helm = Helm.valueOf(rs.getString(HeroDataBaseContract.COLUMN_HELM));
            Armor armor = Armor.valueOf(rs.getString(HeroDataBaseContract.COLUMN_ARMOR));

            Hero hero = new Hero.HeroBuilder(heroClass, name)
                    .addWeapon(weapon)
                    .addArmor(armor)
                    .addHelm(helm)
                    .addLevel(Integer.parseInt(level))
                    .addExperience(Integer.parseInt(experience))
                    .addAttack(Integer.parseInt(attack))
                    .addDefense(Integer.parseInt(defense))
                    .addHitPoints(Integer.parseInt(hitPoints))
                    .build();
            return hero;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

   /* private Hero createHero() {
        Hero hero = new Hero.HeroBuilder()
    }*/


}
