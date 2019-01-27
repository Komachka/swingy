package model.characthers;

public enum HeroClass {

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
}
