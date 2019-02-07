package model;

import model.characthers.Villain;
import view.PlayMission;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class GameMup {
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    private int WIDTH;
    private int HEIGHT;
    private int scale;
    private ArrayList<Villain> villains;

    @Min(1)
    @NotNull
    private int squareSizeMap;


    public GameMup(int heroLevel, ArrayList<Villain> villains) {
        this.squareSizeMap = (heroLevel - 1)*5 + 10 - (heroLevel%2);
        this.villains = villains;
        calcScale();
        WIDTH = this.squareSizeMap * scale;
        HEIGHT = this.squareSizeMap * scale;
        putVillainsOnAMap(heroLevel);
    }

    public ArrayList<Villain> getVillains() {
        return villains;
    }

    private void putVillainsOnAMap(int heroLevel) {

        System.out.println("Put villains on map");
        for (int i = 0; i <= heroLevel*2; i++)
        {
            int x = randomWithRange(0, squareSizeMap - 1);
            int y = randomWithRange(0, squareSizeMap - 1);
            int power = randomWithRange(0, heroLevel);
            System.out.println("x y power " + x  + " " + y + " " + power);
            System.out.println("Scale " + scale);
            x *=scale;
            y *=scale;
            System.out.println("x y power " + x  + " " + y + " " + power);
            villains.add(new Villain(x, y, power));
        }

        System.out.println("villains.size() " + villains.size());

    }


    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void calcScale()
    {
        scale = PlayMission.CANVAS_WIDTH/squareSizeMap;
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
