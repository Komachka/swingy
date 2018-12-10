package model.characthers;

import javax.validation.constraints.NotNull;

public class Knight extends Hero {


    public Knight(String name, String type, int level, int experience, String weapon, String armor) {
        super(name, weapon, armor, type, level, experience);


    }
}
