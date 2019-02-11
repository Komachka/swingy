package controller;

import model.characthers.Hero;
import storage.DBManager;

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
}
