package storage;

import model.characthers.Hero;
import sun.security.pkcs.ParsingException;
import util.CharacterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HerroStorage {
    private List<Hero> heroes;
    private final static String FILE_NAME = "heroes.txt";

    public HerroStorage() {
        this.heroes = parseTextFile();
    }

    private List<Hero> parseTextFile() {
        heroes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            CharacterFactory factory  = new CharacterFactory();
            while ((line = reader.readLine()) != null)
            {
                String[] params = line.split(",");
                for (String par: params) {
                    System.out.println(par);

                }
                Hero hero = factory.newHero(params[0], params[1], Integer.parseInt(params[2].trim()), Integer.parseInt(params[3].trim()), params[4], params[5]);
                heroes.add(hero);
            }
        }
        catch (IOException ex)
        {

        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return heroes;
    }

    public List<Hero> getAllHeroes() {
        return heroes;
    }

    public List<String> getAllHeroeNames() {
        List<String> names = heroes.stream().map(Hero::getName).collect(Collectors.toList());
        return names;
    }

    public Hero getHero(int rollNo)
    {
        return heroes.get(rollNo);
    }

}
