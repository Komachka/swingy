package util;

import model.characthers.Elf;
import model.characthers.Hero;
import model.characthers.Knight;

public class CharacterFactory {

    public static Hero newHero(String name, String type,  int level, int experience, String weapon, String armor)
    {
        switch (type.toLowerCase().trim())
        {
            case "knight" :
                return new Knight(name, type, level, experience, weapon, armor);
            case "elf":
                return new Elf(name, type, level, experience, weapon, armor);
            default:
                throw new NullPointerException();
        }
    }

}
