package model.characthers;

import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;

import javax.validation.constraints.NotNull;



public abstract class Hero extends Character {

    //Hero name
    //Hero class
    //Level
    //Experience
    //Attack
    //Defense
    //Hit point
//TODO add loombok anotations

    @NotNull
    private Weapon weapon; // increases the attack
    @NotNull
    private Armor armor; // increase the defense
    @NotNull
    private Helm helm; // increases hit points
    int level;
    int experience;


    public Hero(@NotNull String name,@NotNull String weapon, @NotNull String armor, @NotNull String helm, int level, int experience) {
        super(name);
        this.weapon = new Weapon(weapon);
        this.armor = new Armor(armor);
        this.helm = new Helm(helm);
        this.level = level;
        this.experience = experience;
    }


    public @NotNull Weapon getWeapon() {
        return this.weapon;
    }

    public @NotNull Armor getArmor() {
        return this.armor;
    }

    public @NotNull Helm getHelm() {
        return this.helm;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }


}
