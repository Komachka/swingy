package model;

import view.PlayMission;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GameMup {
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    private int WIDTH;
    private int HEIGHT;
    private int scale;

    @Min(1)
    @NotNull
    private int squareSizeMap;


    public GameMup(int heroLevel) {
        this.squareSizeMap = (heroLevel - 1)*5 + 10 - (heroLevel%2);
        calcScale();
        WIDTH = this.squareSizeMap * scale;
        HEIGHT = this.squareSizeMap * scale;
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
        return WIDTH/2;
    }

    public int getCenterY()
    {
        return HEIGHT/2;
    }
}
