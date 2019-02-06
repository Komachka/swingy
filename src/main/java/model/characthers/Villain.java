package model.characthers;

import javax.validation.constraints.NotNull;

public  class Villain extends Character {
    @NotNull
    private int power;

    public Villain() {
        super();
        generateRandomPower();
    }


    //TODO implement method
    private void generateRandomPower() {
        power = 5;
    }


    public int getPower() {
        return power;
    }
}
