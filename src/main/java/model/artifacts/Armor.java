package model.artifacts;

 //increase the defense

import model.InOutEnumConsole;

public enum Armor implements InOutEnumConsole {

    BROTHERHOOD_OF_STEEL(100),
    SCORPION_SUIT(90),
    ARS_SUIT(80),
    NANOSUIT(70),
    TANOOKI_SUIT(1000);

    private int increaseTheDefense;

    Armor(int increaseTheDefense) {
        this.increaseTheDefense = increaseTheDefense;
    }

    public int getIncreaseTheDefense() {
        return increaseTheDefense;
    }

    public static void print() {
        for (Armor c : Armor.values()) {
            System.out.println(c.name() + " Increase the defence " + c.increaseTheDefense);
        }
    }

    @Override
    public void printAll() {
        print();
    }

    @Override
    public boolean contains(String str) {
        for (Armor armor : Armor.values())
        {
            if (armor.name().equals(str.toUpperCase()))
                return true;
        }
        return false;
    }


}
