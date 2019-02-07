package model.characthers;

import model.GameMup;

import static model.GameMup.*;

public abstract class Character {
    private int X;
    private int Y;


    public Character() {
        X = 0;
        Y = 0;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void move(int direction, GameMup map)
    {
        switch (direction) {
            case UP:  moveUp(map);
                break;
            case RIGHT: moveRight(map);
                break;
            case DOWN: moveDown(map);
                break;
            case LEFT: moveLeft(map);
                break;
        }
    }

    private void moveDown(GameMup map) {
        if (Y + map.getScale() <= map.getHEIGHT() - map.getScale())
        {
            this.Y +=map.getScale();
        }
    }

    private void moveUp(GameMup map) {
        if (Y - map.getScale() >= 0)
        {
            this.Y -=map.getScale();
        }
    }

    private void moveRight(GameMup map) {
        if (X + map.getScale() <= map.getWIDTH() - map.getScale())
        {
            this.X +=map.getScale();
        }
    }
    private void moveLeft(GameMup map) {
        if (X - map.getScale() >= 0)
        {
            this.X -=map.getScale();
        }
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
}


