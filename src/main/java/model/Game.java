package model;

import model.characthers.Hero;
import model.characthers.Villain;
import view.MoveObserver;

import java.util.ArrayList;

/**
 *
 * When a map is generated, villains of varying power will be spread randomly
 * over the map. When a hero moves to a position occupied by a villain,
 * the hero has 2 options: •
 * Fight, which engages him in a battle with the villain
 * • Run, which gives him a 50% chance of returning to the previous position.
 * If the odds aren’t on his side, he must fight the villain.
 *
 */




public class Game {

    private ArrayList<MoveObserver> moveObserver = new ArrayList();
    private Hero hero;
    private ArrayList<Villain> villains = new ArrayList<>();
    private GameMup gameMup;
    public boolean isGameOver = false;
    public boolean isFightMode = false;
    public Villain currentVillain;

    public Game(Hero hero) {
        this.hero = hero;
        gameMup = new GameMup(hero, villains);
        hero.setX(gameMup.getCenterX());
        hero.setY(gameMup.getCenterY());
    }



    public boolean playRound(int direction)
    {
        hero.move(direction, gameMup);
        if (isBodredOver())
            return true;
        if (isCloseToEnemies())
            return true;
        return false;
    }

    public boolean isBodredOver()
    {
        if (hero.getX() <= 0 || hero.getX() >= gameMup.getWIDTH()
                || hero.getY() <= 0 || hero.getY() >= gameMup.getHEIGHT())
        {
            isGameOver = true;
            return true;
        }
        else
            return false;
    }


    private void setFightMode(Villain villain)
    {
        isFightMode = true;
        currentVillain = villain;
    }




    private boolean isCloseToEnemies() {

        int radius = getScale()-getScale()/2;
        for (Villain villain : gameMup.getVillains())
        {
            int x1 = villain.getX() - radius;
            int x2 = villain.getX() + radius;
            int y1 = villain.getY() - radius;
            int y2 = villain.getY() + radius;


            if (hero.getX() > x1 && hero.getX() < x2 && hero.getY()> y1 && hero.getY() < y2) {
                setFightMode(villain);
                return true;
            }
        }
        isFightMode = false;
        return false;
    }


    //Run, which gives him a 50% chance of returning to the previous position.
    // If the odds aren’t on his side, he must fight the villain.
    public boolean run()
    {
        return false;
    }



    //You will need to simulate the battle between the hero and monster
    // and present the user the outcome of the battle.
    // We leave it at you to find a nice simulation algorythm
    // that decides based on the hero and monster stats,
    // who will win. You can include a small "luck",
    // component in the algo in order to make the game more entertaining.

    public Character fight()
    {
        System.out.println("currentVillain.getPower()" + currentVillain.getPower());
        System.out.println(hero.toString());

        return null;
    }

    //
    public void registerObserver(MoveObserver observer)
    {
        moveObserver.add(observer);
    }

    public void removeObserver(MoveObserver observer)
    {
    }

    public Hero getHero() {
        return hero;
    }

    public int getMapH()
    {
        return gameMup.getHEIGHT();
    }

    public int getMapW()
    {
        return gameMup.getWIDTH();
    }

    public int getHeroX()
    {
        return hero.getX();
    }

    public int getHeroY()
    {
        return hero.getY();
    }


    public ArrayList<Villain> getEnemies()
    {
        return gameMup.getVillains();
    }


    public int getScale()
    {
        return gameMup.getScale();
    }

    public void increaseExpWinButl() {
        hero.setExperience(currentVillain.getPower());
    }

    public void increaseExpRichBorder() {
        hero.setExperience(50);
    }
}
