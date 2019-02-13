package model.artifacts;

import model.InOutEnumConsole;

public enum Weapon implements InOutEnumConsole {

    SWORD(50),
    BOW(40),
    PUGIO(30),
    FLAX(20);


    private int increasesTheAttack;

     Weapon(int increasesTheAttack) {
        this.increasesTheAttack = increasesTheAttack;
     }

    public int getIncreasesTheAttack() {
        return increasesTheAttack;
    }

    public static void print() {
        for (Weapon c : Weapon.values()) {
            System.out.println(c.name() + " Increase the Attack " + c.increasesTheAttack);
        }
    }

    @Override
    public void printAll() {
        print();
    }

    @Override
    public boolean contains(String str) {
        for (Weapon weapon : Weapon.values())
        {
            if (weapon.name().equals(str.toUpperCase()))
                return true;
        }
        return false;
    }


}
