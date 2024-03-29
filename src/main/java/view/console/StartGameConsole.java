package view.console;

import model.Game;
import model.characthers.Hero;
import view.WindowManager;

import java.util.Scanner;

public class StartGameConsole implements WindowManager {
    public static final String EXIT = "q";

    public StartGameConsole() {
    }

    @Override
    public void showSelectedHero() {
        System.out.println("------------------Selected hero------------------");
        new SelectHeroConsole(this);
    }

    @Override
    public void showNewHero() {
        System.out.println("------------------Create a new hero------------------");
        new NewHeroConsole(this);

    }

    @Override
    public void showSelectedMission(Hero hero) {
        System.out.println("------------------Mission Start------------------");
        Game game = new Game(hero);
        new PlayMissionConsole(game, (WindowManager)this);
    }

    @Override
    public void restartGame() {
        stop();
        setup();
        start();
    }

    @Override
    public void stop() {
        //System.out.println("stop");
    }

    @Override
    public void setup() {
        //System.out.println("set up");
    }

    @Override
    public void start() {
        System.out.println("\033[34m----------WELCOME IN SWINGY GAME----------\033[0m");
        System.out.println("\t- Print 1 to create a hero");
        System.out.println("\t- Print 2 to select already created hero\t");
        System.out.println("\t- Print "+EXIT+" to exit\t");
        Scanner scanner = new Scanner(System.in);
        String answer = null;
        while ((answer = scanner.nextLine()) != null)
        {
            System.out.println(answer);
            switch (answer)
            {
                case "1" :
                    showNewHero();
                    break;
                case "2" :
                    showSelectedHero();
                    break;
                case EXIT :
                    System.exit(0);



            }

        }

    }
}
