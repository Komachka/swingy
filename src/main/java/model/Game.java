package model;

import model.characthers.Hero;
import model.characthers.Villain;
import view.MoveObserver;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Min(1)
    private ArrayList<MoveObserver> moveObserveres = new ArrayList();
    @NotNull
    private Hero hero;
    private ArrayList<Villain> villains = new ArrayList<>();
    private ArrayList<Villain> villainsLoosers = new ArrayList<>();
    private GameMup gameMup;
    public boolean isGameOver = false;
    public boolean isFightMode = false;
    public Villain currentVillain;

    public Game(Hero hero) {
        this.hero = hero;
    }


    public void setupGameMup(String gameMode) {
        this.gameMup = new GameMup(hero, villains);
        gameMup.calcScale(gameMode);
        gameMup.putVillainsOnAMap(hero);
        gameMup.setHEIGHT(getGameSquare() * getScale());
        gameMup.setWIDTH(getGameSquare() * getScale());
        hero.setX(gameMup.getCenterX());
        hero.setY(gameMup.getCenterY());

    }

    public boolean playRound(int direction)
    {
        hero.move(direction, gameMup);
        notifyMoveObservers();
        if (isCloseToEnemies())
            return true;
        if (isBodredOver())
            return true;


        return false;
    }

    public boolean isBodredOver()
    {
        if (hero.getX() <= 0 || hero.getX() >= gameMup.getWIDTH()
                || hero.getY() <= 0 || hero.getY() >= gameMup.getHEIGHT())
        {
            isGameOver = true;
            addHeroExperience();
            notifyMoveObserversWinGame();
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

        int radius = getScale();
        for (Villain villain : villains)
        {
            int x1 = villain.getX() - radius;
            int x2 = villain.getX() + radius;
            int y1 = villain.getY() - radius;
            int y2 = villain.getY() + radius;
            if (hero.getX() >= x1 && hero.getX() <= x2 && hero.getY()>= y1 && hero.getY() <= y2) {
                setFightMode(villain);
                notifyMoveObserversFightMode();
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
        if (rand % 2 == 0)
            return true;
        else
            return false;

    }




    public boolean fight()
    {

        if (isLack()) {
            heroWin();
            return true;
        }
        while (hero.getHitPoints() > 0 && currentVillain.getPower() > 0)
        {
            hero.attack(currentVillain);
            hero.addExperience(50);
            currentVillain.attack(hero);
            notifyMoveObserversFightProgressMode();

        }
        if (hero.getHitPoints() <= 0) {
            notifyMoveObserversLooseMode();
            return false;
        }
        if (currentVillain.getPower() <= 0) {
            heroWin();

            return true;

        }return false;
    }

    private void heroWin() {
        villainsLoosers.add(currentVillain);
        villains.remove(currentVillain);
        currentVillain = null;
        notifyMoveObserversRemuveVillain();
        notifyMoveObserversWinEnemyMode();
    }


    private boolean isLack() {
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        if (rand <= 5)
            return true;
        else
            return false;
    }

    //
    public void registerObserver(MoveObserver observer)
    {
        moveObserveres.add(observer);
    }

    //TODO need to put somewhere
    public void removeObserver(MoveObserver observer)
    {
        int i = moveObserveres.indexOf(observer);
        if (i >= 0) {
            moveObserveres.remove(i);
        }
    }

    //notify move
    public void notifyMoveObservers() {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updatePosition();
        }
    }

    //notify win enemy
    private void notifyMoveObserversWinEnemyMode() {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateWinEnemyMode();
        }
    }

    private void notifyMoveObserversWinGame()
    {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateWinGameMode();
        }
    }

    //notify to open fight window
    private void notifyMoveObserversFightMode() {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateFightMode();
        }
    }

    //notify to loose the game
    private void notifyMoveObserversLooseMode() {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateLooseMode();
        }
    }

    //notify in progress fight to show the fight result
    private void notifyMoveObserversFightProgressMode() {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateFightProgressMode();
        }
    }

    //notify if vilian is remuved from mau
    private void notifyMoveObserversRemuveVillain()
    {
        for(int i = 0; i < moveObserveres.size(); i++) {
            MoveObserver observer = (MoveObserver) moveObserveres.get(i);
            observer.updateVillainsOnMup();
        }
    }


    public int getGameSquare()
    {
        return gameMup.getSquareSizeMap();
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
        return villains;
    }


    public int getScale()
    {
        return gameMup.getScale();
    }

    public void addHeroExperience() {
        int experience = 0;
        if (isGameOver && currentVillain == null)
            experience = 100;
        else if (isGameOver && currentVillain != null)
        {
            experience = currentVillain.getPower();
        }
        hero.addExperience(experience);


    }


    public ArrayList<Villain> getVillainsLoosers() {
        return villainsLoosers;
    }


}
