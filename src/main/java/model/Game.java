package model;

import model.characthers.Hero;
import model.characthers.Villain;
import model.characthers.Character;
import view.MoveObserver;

import java.util.ArrayList;
import java.util.Random;


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
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        System.out.println("rand " + rand);
        if (rand % 2 == 0)
            return true;
        else
            return false;

    }




    public boolean fight()
    {

        if (isLack())
            return true;

        while (hero.getHitPoints() > 0 && currentVillain.getPower() > 0)
        {
            hero.attack(currentVillain);
            currentVillain.attack(hero);
            System.out.println("Hero XP " + hero.getHitPoints());
            System.out.println("Enemy power " + currentVillain.getPower());
        }
        if (hero.getHitPoints() <= 0) {

            return false;
        }
        if (currentVillain.getPower() <= 0) {
            villains.remove(currentVillain);
            currentVillain = null;
            return true;

        }return false;
    }

    private boolean isLack() {
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        System.out.println("rand " + rand);
        if (rand <= 5)
            return true;
        else
            return false;
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

    public void addExperience() {
        int experience = 0;
        if (isGameOver && currentVillain == null)
            experience = 100;
        else if (isGameOver && currentVillain != null)
        {
            experience = currentVillain.getPower();
        }

        hero.addExperience(experience);


    }
}
