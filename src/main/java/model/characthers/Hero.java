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
    private Weapon weapon; // increases the attack
    @NotNull
    private Armor armor; // increase the defense
    @NotNull
    private Helm helm; // increases hit points
    int level;
    int experience;
}
