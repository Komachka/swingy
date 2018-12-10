package util;

import model.characthers.Elf;
import model.characthers.Hero;
import model.characthers.Knight;

public class CharacterFactory {

    public static Hero newHero(String name, String type,  int level, int experience, String attack, String defence)
    {
        switch (type.toLowerCase().trim())
        {
            case "knight" :
                return new Knight();
            case "elf":
                return new Elf();
            default:
                throw new NullPointerException();
        }
    }

}
