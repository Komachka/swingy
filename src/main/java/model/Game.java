package model;

import model.characthers.Hero;
import model.characthers.Villain;
import view.MoveObserver;

import java.util.ArrayList;

public class Game {

    private ArrayList<MoveObserver> moveObserver = new ArrayList();
    private Hero hero;
    private Villain villain;
    private GameMup gameMup;

    public Game(Hero hero) {
        this.hero = hero;
        villain = new Villain();
        generateMap();

    }

    private boolean generateMap()
    {
        gameMup = new GameMup(hero.getLevel());
        return true;
    }


    public boolean playGameRound(int direction)
    {
        hero.move(direction, gameMup);
        System.out.println("x  : "  + getHeroX() +  " y : "  + getHeroY());
        if (isCloseToEnemy())
        {
            return true;
        }
        return false;
    }

    private boolean isCloseToEnemy() {
        if (hero.getX() == villain.getX() || hero.getY() == villain.getY())
        {
            return true;
        }
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

    public int getEnemyX()
    {
        return villain.getX();
    }

    public int getEnemyY()
    {
        return villain.getY();
    }
}
