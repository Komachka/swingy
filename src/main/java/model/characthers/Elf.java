package model.characthers;

import javax.validation.constraints.NotNull;

public class Elf extends Hero {

    public Elf(String name, String type, int level, int experience, String weapon, String armor) {
        super(name, weapon, armor, type, level, experience);


    }


}

