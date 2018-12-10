package model.characthers;

import lombok.Getter;
import lombok.Setter;

import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import javax.validation.constraints.NotNull;




@Getter
@Setter
public abstract class Hero extends Character {

    //Hero name
    //Hero class
    //Level
    //Experience
    //Attack
    //Defense
    //Hit point



    @NotNull
    private String name;
    @NotNull
    private Weapon weapon; // increases the attack
    @NotNull
    private Armor armor; // increase the defense
    @NotNull
    private Helm helm; // increases hit points
    int level;
    int experience;


    public Hero(@NotNull String name,@NotNull String weapon, @NotNull String armor, @NotNull String helm, int level, int experience) {
        this.name = name;
        this.weapon = new Weapon(weapon);
        this.armor = new Armor(armor);
        this.helm = new Helm(helm);
        this.level = level;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }
}
