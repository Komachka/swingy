package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GameMup {
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    public int WIDTH;
    public int HEIGHT;


    @Min(1)
    @NotNull
    private int squareSizeMap;


    public GameMup(int heroLevel) {
        this.squareSizeMap = (heroLevel - 1)*5 + 10 - (heroLevel%2);
        WIDTH = this.squareSizeMap;
        HEIGHT = this.squareSizeMap;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
