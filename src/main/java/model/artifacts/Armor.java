package model.artifacts;

 //increase the defense

public enum Armor {

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
}
