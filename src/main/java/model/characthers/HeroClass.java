package model.characthers;

import model.InOutEnumConsole;

public enum HeroClass implements InOutEnumConsole {

    BLACK_MAGE(40, 40),
    ELF(30, 40),
    KNIGHT(80, 30),
    ORC(100, 10);

    private final int attack;
    private final int defense;

    HeroClass(int attack, int defense)
    {
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }


    public boolean contains(String name)
    {
        for (HeroClass heroClass : HeroClass.values())
        {
            if (heroClass.name().equals(name.toUpperCase()))
                return true;
        }
        return false;
    }




    public static void print() {
        for (HeroClass c : HeroClass.values()) {
            System.out.println(c.name() + " Attack " + c.attack + " Defence " + c.defense);
        }
    }


    @Override
    public void printAll() {
        print();
    }
}
