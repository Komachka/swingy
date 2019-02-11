package model.characthers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

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

    public void attack(Hero hero)
    {
        int pointsOfDefence = hero.getHitPoints() + hero.getDefense();
        hero.decreaseHP(Math.abs(power - pointsOfDefence));
    }

    public void decreasePower(@NotNull int attack) {
        power-=attack;
    }
}
