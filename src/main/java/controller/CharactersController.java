package controller;

import model.characthers.Hero;
import storage.DBManager;

public class CharactersController {

    private DBManager manager;

    public CharactersController() {

        manager = new DBManager();

    }

    public void saveHeroToDatabase(Hero hero) {
        manager.insertHero(hero);

    }
}
