package model.artifacts;

public enum Weapon {

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
}
