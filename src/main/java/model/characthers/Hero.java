package model.characthers;

import lombok.Getter;
import lombok.Setter;

import model.artifacts.Armor;
import model.artifacts.Weapon;
import javax.validation.constraints.NotNull;




@Getter
@Setter
public abstract class Hero extends Character {

    @NotNull
    private Weapon weapon;
    @NotNull
    private Armor armor;

    int level;
    int experience;
}
