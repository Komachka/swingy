package model.characthers;

import javax.validation.constraints.NotNull;

public  class Villain extends Character {
    @NotNull
    private int power;

    public Villain(int x, int y, int power)
    {
        super();
        setX(x);
        setY(y);
        this.power = power;
    }

    public Villain() {
        super();
    }

    public int getPower() {
        return power;
    }
}
