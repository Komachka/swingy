import model.characthers.HeroClass;
import view.StartGame;

import javax.print.Doc;
import java.util.Hashtable;

public class Main {


    enum GameModes {
        GUI,
        CONSOLE
    }


    public static void main(String[] args) {


/*
        Game game = null;
        GameBuilder builder = new GameBuilder();
        GameObjFactory factory = null;

        String arg = "console";
        //String arg = "gui";
        GameModes mode = GameModes.CONSOLE;
        if (mode == GameModes.GUI){
            factory = new SwingFactory();
        }
        else if (mode == GameModes.CONSOLE)
        {
            factory = new ConsoleFactory();
        }
        game =  builder.buildGame(factory, mode);*/


        HeroClass heroClass = HeroClass.ELF;
        System.out.println("Elf attack " + heroClass.getAttack());

        for (HeroClass p : HeroClass.values())
        {
            System.out.println("defence for " + p + " "+ p.getDefense());
        }

        StartGame app = new StartGame();
        //app.start();



    }






}
