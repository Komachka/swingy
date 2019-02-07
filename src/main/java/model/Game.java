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

    public Game(Hero hero) {
        this.hero = hero;
        gameMup = new GameMup(hero.getLevel(), villains);
        hero.setX(gameMup.getCenterX());
        hero.setY(gameMup.getCenterY());
    }



    public boolean isPlayRoundGameOver(int direction)
    {
        hero.move(direction, gameMup);
        if (isBodredOver())
            return true;
        if (isCloseToEnemy())
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

    //check is enemy is inside the rectungle
    private boolean isCloseToEnemy() {

        for (Villain villain : gameMup.getVillains())
        {

            if (hero.getX() == villain.getX() || hero.getY() == villain.getY())
            {
                if (villain.getPower() < hero.getLevel())
                {
                    isFightMode = true;
                    return true;
                }

            }
            else if (hero.getX() == villain.getX() && hero.getY() == villain.getY())
            {
                isFightMode = true;
                return true;
            }

        }
        isFightMode = false;
        return false;
    }


    //TODO implement fight method
    public Character fight()
    {
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
}
