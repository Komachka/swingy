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

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void move(int direction, GameMup map)
    {
        System.out.println("Move" + direction);
        switch (direction) {
            case UP:  moveUp(map);
                break;
            case RIGHT: moveRight(map);
                break;
            case DOWN: moveDown();
                break;
            case LEFT: moveLeft();
                break;
        }
    }

    private void moveDown() {
        System.out.println("Move down");
        if (Y - 1 >= 0)
        {
            this.Y -=1;
            System.out.println("Y = " + Y);
        }
    }

    private void moveUp(GameMup map) {
        if (Y + 1 <= map.HEIGHT)
        {
            this.Y -=1;
        }
    }

    private void moveRight(GameMup map) {
        if (X + 1 <= map.WIDTH)
        {
            this.X -=1;
        }
    }
    private void moveLeft() {
        if (X -1 >= 0)
        {
            this.X -=1;
        }
    }

}


