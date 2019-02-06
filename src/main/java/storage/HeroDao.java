package storage;

import model.characthers.Hero;

import java.util.List;

public interface HeroDao {
    Hero getHeroById(long id);
    List<Hero> getAllHeroes();
    void insertHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHero(Hero hero);
}
