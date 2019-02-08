package model.characthers;

import javax.validation.constraints.NotNull;

public  class Villain extends Character {
    @NotNull
    private int power;

    public Villain(int x, int y, int power)
    {
        super(x, y);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
