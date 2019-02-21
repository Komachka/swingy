package storage;

import model.characthers.Hero;

import java.util.List;

public interface HeroDao {
    List<Hero> getAllHeroes();
    boolean insertHero(Hero hero);
    boolean updateHero(Hero hero);
    boolean deleteHero(Hero hero);
}
