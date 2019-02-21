package controller;

import lanch.MainClass;
import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import model.characthers.Hero;
import model.characthers.HeroClass;
import storage.DBManager;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class CharactersController {

    private DBManager manager;

    public CharactersController() {

        manager = new DBManager();

    }

    public boolean saveHero(Hero hero) {
        return manager.insertHero(hero);

    }

    public boolean updateHero(Hero hero) {
        hero.setDefXP();

        return manager.updateHero(hero);
    }

    public boolean deleteHero(Hero hero) {
       return manager.deleteHero(hero);
    }

    public Hero createHero(String name, HeroClass hClass, Weapon weapon, Armor armor, Helm helm) {
        Hero hero = new Hero.HeroBuilder(hClass,name)
                .setAttack(hClass.getAttack())
                .setDefense(hClass.getDefense())
                .addWeapon(weapon)
                .addArmor(armor)
                .addHelm(helm)
                .build();
        Set<ConstraintViolation<Hero>> violations = null;
        Validator validator = MainClass.factory.getValidator();
        if (violations != null)
        {
            for (ConstraintViolation<Hero> violation : violations)
                System.out.println(violation.getMessage());
        }
        violations = validator.validate(hero);
        if (violations.size() == 0)
        {
            return hero;
        }
        else
        {
            for (ConstraintViolation<Hero> violation : violations)
                System.err.println(violation.getMessage());
            return null;
        }

    }
}
