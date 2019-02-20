package storage;

import lanch.MainClass;
import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import model.characthers.Hero;
import model.characthers.HeroClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;


public class DBManager implements HeroDao {


    public DBManager() {
        creteNewDataBase();
        createHeroesTable();
    }

    private   void creteNewDataBase() {

        try(Connection connection = DBConnection.getDBConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            //System.out.println("The driver name is " + meta.getDriverName());
            //System.out.println("A new db has been created");
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
            //System.out.println(HeroDataBaseContract.SQL_CREATE_TABLE);
            stmt.execute(HeroDataBaseContract.SQL_CREATE_TABLE);
            //System.out.println("Table has been created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public Hero getHeroById(long id) {
        return null;
    }

    @Override
    public  boolean insertHero(Hero hero) {
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
            //System.out.println("Data has been inserted");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateHero(Hero hero) {
        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(HeroDataBaseContract.UPDATE_HERO_SQL)) {
            pstmt.setString(1, String.valueOf(hero.getLevel()));
            pstmt.setString(2, String.valueOf(hero.getExperience()));
            pstmt.setString(3, String.valueOf(hero.getAttack()));
            pstmt.setString(4, String.valueOf(hero.getDefense()));
            pstmt.setString(5, String.valueOf(hero.getWeapon().name()));
            pstmt.setString(6, String.valueOf(hero.getHelm().name()));
            pstmt.setString(7, String.valueOf(hero.getArmor().name()));
            pstmt.setString(8, String.valueOf(hero.getHitPoints()));
            pstmt.setInt(9, hero.getId());
            pstmt.executeUpdate();
            //System.out.println("Data has been inserted");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }


    }

    @Override
    public boolean deleteHero(Hero hero) {
        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(HeroDataBaseContract.SQL_DELETE_BY_ID)) {
            pstmt.setInt(1, hero.getId());
            pstmt.executeUpdate();
            //System.out.println("Data has been deleted");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public ArrayList<Hero> getAllHeroes()
    {
        ArrayList<Hero> heroes = new ArrayList<>();
        try (Connection connection = DBConnection.getDBConnection();
             Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(HeroDataBaseContract.SQL_SELECT_ALL)) {

            while (rs.next())
            {
                Hero hero = createHero(rs);
                if (hero != null)
                    heroes.add(hero);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return heroes;
    }

    private Hero createHero(ResultSet rs) {
        try {
            HeroClass heroClass = HeroClass.valueOf(rs.getString(HeroDataBaseContract.COLUMN_HEROCLASS));
            int id = rs.getInt(HeroDataBaseContract.COLUMN_ID);
            String name = rs.getString(HeroDataBaseContract.COLUMN_NAME);
            String level = rs.getString(HeroDataBaseContract.COLUMN_LEVEL);
            String experience = rs.getString(HeroDataBaseContract.COLUMN_EXPERIENCE);
            String attack = rs.getString(HeroDataBaseContract.COLUMN_ATTACK);
            String defense = rs.getString(HeroDataBaseContract.COLUMN_DEFENSE);
            String hitPoints  = rs.getString(HeroDataBaseContract.COLUMN_HP);
            Weapon weapon = Weapon.valueOf(rs.getString(HeroDataBaseContract.COLUMN_WEAPON));
            Helm helm = Helm.valueOf(rs.getString(HeroDataBaseContract.COLUMN_HELM));
            Armor armor = Armor.valueOf(rs.getString(HeroDataBaseContract.COLUMN_ARMOR));

            Hero hero = new Hero.HeroBuilder(heroClass, name, id)
                    .addWeapon(weapon)
                    .addArmor(armor)
                    .addHelm(helm)
                    .setLevel(Integer.parseInt(level))
                    .setExperience(Integer.parseInt(experience))
                    .setAttack(Integer.parseInt(attack))
                    .setDefense(Integer.parseInt(defense))
                    .setHitPoints(Integer.parseInt(hitPoints))
                    .build();
            Set<ConstraintViolation<Hero>> violations = null;
            Validator validator = MainClass.factory.getValidator();
            if (violations != null)
            {
                for (ConstraintViolation<Hero> violation : violations)
                    System.out.println(violation.getMessage());
            }
            violations = validator.validate(hero);
            if (violations.size() == 0)
            {
                return hero;
            }
            else
            {
                for (ConstraintViolation<Hero> violation : violations)
                    System.out.println(violation.getMessage());
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }




}
