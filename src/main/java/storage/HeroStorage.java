package storage;

import model.characthers.Hero;
import java.util.List;
import java.util.stream.Collectors;

public class HeroStorage {
    private List<Hero> heroes;
    public HeroStorage() {

        DBManager dbManager = new DBManager();
        this.heroes = dbManager.getAllHeroes();
    }


    public List<Hero> getAllHeroes() {
        return heroes;
    }

    public List<String> getAllHeroeNames() {
        if (heroes.size() != 0)
            return heroes.stream().map(Hero::getName).collect(Collectors.toList());
        else
            return null;
    }

    public Hero getHero(int rollNo)
    {
        return heroes.get(rollNo);
    }
}
