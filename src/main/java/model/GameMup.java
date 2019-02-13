package model;

import model.characthers.Hero;
import model.characthers.Villain;
import view.swing.PlayMissionSwing;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class GameMup {
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = -1;
    public static final int LEFT = -2;
    private int WIDTH;
    private int HEIGHT;
    private int scale;
    private ArrayList<Villain> villains;

    @Min(1)
    @NotNull
    private int squareSizeMap;


    public GameMup(Hero hero, ArrayList<Villain> villains) {
        this.squareSizeMap = (hero.getLevel() - 1)*5 + 10 - (hero.getLevel()%2);
        this.villains = villains;


    }


    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getSquareSizeMap() {
        return squareSizeMap;
    }

    void putVillainsOnAMap(Hero hero) {

        for (int i = 0; i <= hero.getLevel()*10; i++)
        {
            int x = randomWithRange(0, squareSizeMap - 1);
            int y = randomWithRange(0, squareSizeMap - 1);
            x *=scale;
            y *=scale;
            villains.add(new Villain(x, y, generateVillainPower(hero)));
        }


    }

    private int generateVillainPower(Hero hero) {
        int attack = randomWithRange(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defense = randomWithRange(hero.getDefense() - 20, hero.getDefense() + 2 + hero.getLevel());
        int hitPoints = randomWithRange(hero.getHitPoints() - 50, hero.getHitPoints() + 20 + hero.getLevel());

        attack = attack < 0 ? -attack : attack;
        defense = defense < 0 ? -defense : defense;
        hitPoints = hitPoints < 0 ? -hitPoints : hitPoints;
        return attack + defense + hitPoints;
    }


    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void calcScale(String gameMode)
    {
        if (gameMode.equals("gui"))
            scale = PlayMissionSwing.CANVAS_WIDTH/squareSizeMap;
        else
            scale = 1;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getScale() {
        return scale;
    }

    public int getCenterX()
    {
        return (squareSizeMap/2)*scale;
    }

    public int getCenterY()
    {
        return(squareSizeMap/2)*scale;
    }
}
