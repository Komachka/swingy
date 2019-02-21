package view.console;

import controller.CharactersController;
import controller.GamePlayController;
import lanch.MainClass;
import model.Game;
import model.GameMup;
import model.characthers.Hero;
import model.characthers.Villain;
import view.IPlayMissionView;
import view.LevelUpObserver;
import view.MoveObserver;
import view.WindowManager;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayMissionConsole implements IPlayMissionView, MoveObserver, LevelUpObserver {
    private Game game;
    private Hero hero;
    private int heroDirection;
    private WindowManager windowManager;
    private CharactersController charactersController = new CharactersController();
    private GamePlayController gamePlayController;
    private Scanner scanner;

    private static final String HERO = "\uD83D\uDE07\t";
    private static final String ENEMY = "\uD83D\uDE08\t";
    private static final String ENEMY_LOOSER = "\uD83D\uDE1E\t";

    public PlayMissionConsole(Game game, WindowManager windowManager) {
        this.windowManager = windowManager;
        this.game = game;
        this.hero = game.getHero();
        scanner = new Scanner(System.in);
        game.registerObserver((MoveObserver) this);
        game.getHero().registerLevelUpObserver((LevelUpObserver) this);
        this.gamePlayController = new GamePlayController(game, this, MainClass.GAME_MODE_CONSOLE);
        readInputData();
    }

    private void readInputData() {
        String inputValue = null;

        while (inputValue == null)
        {
            try {
                inputValue = scanner.nextLine();
                inputValue = inputValue.toUpperCase();
                switch (inputValue)
                {
                    case "S":
                        heroDirection = GameMup.DOWN;
                        break;
                    case "E":
                        heroDirection = GameMup.RIGHT;
                        break;
                    case "W":
                        heroDirection = GameMup.LEFT;
                        break;
                    case "N":
                        heroDirection = GameMup.UP;
                        break;
                    default:
                        heroDirection = 0;
                        break;
                }
                if (heroDirection == 0)
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                System.out.println("\t\tWRONG INPUT");
                inputValue = null;
            }
        }
        gamePlayController.playGameRound(heroDirection);

    }

    @Override
    public void createView() {
        printMap();
    }

    private void printMap() {
        for (int i = 0; i <game.getGameSquare() ; i++) {
            for (int j = 0; j <game.getGameSquare() ; j++) {
                if (j == hero.getX()/game.getScale() && i == hero.getY()/game.getScale())
                    System.out.print(HERO);
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }
        System.out.println("----------Print N , E , S, W for move----------");
    }

    @Override
    public int getHeroDirection() {
        return heroDirection;
    }

    @Override
    public void updateView() {
        printMap();
        readInputData();

    }

    @Override
    public void showEnemies() {
        ArrayList<Villain> villains = game.getEnemies();
        ArrayList<Villain> vLoosers = game.getVillainsLoosers();
        String symbolToPrint;
        int gameSquare = game.getGameSquare();
        System.out.println();
        for (int i = 0; i <gameSquare ; i++) {
            for (int j = 0; j <gameSquare ; j++) {
                symbolToPrint = ".\t";
                for (Villain v: villains) {
                    if (j == v.getX()/game.getScale() && i == v.getY()/game.getScale())
                    {
                        symbolToPrint = ENEMY;
                    }

                }
                for (Villain v: vLoosers) {
                    if (j == v.getX()/game.getScale() && i == v.getY()/game.getScale())
                    {
                        symbolToPrint = ENEMY_LOOSER;
                    }

                }
                if (j == hero.getX()/game.getScale() && i == hero.getY()/game.getScale())
                    symbolToPrint = HERO;
                System.out.print(symbolToPrint);
            }
            System.out.println();
        }
        System.out.println();

    }

    @Override
    public void showGameOverWindow(String message) {
        System.out.println("show game over");

    }

    @Override
    public void showFightModeWindow() {
        System.out.println("Fight mode window empty method");
    }

    @Override
    public void gameOver() {
        System.out.println("\t\t----------");
        System.out.println("\t\tGAME OVER");
        System.out.println("\t\t----------");
        showEnemies();
        charactersController.updateHero(hero);
        windowManager.restartGame();
    }

    @Override
    public void updatePosition() {
        printMap();

    }

    @Override
    public void updateFightMode() {
        System.out.println("\tYOU NEED TO FIGHT OR RUN!");
        System.out.println("\t\tPrint F to fight");
        System.out.println("\t\tPrint R to run");
        String line;
        while (!(line = scanner.nextLine()).equals(StartGameConsole.EXIT))
        {
            if (line.toUpperCase().equals("F"))
            {
                gamePlayController.fight();
                break;
            }
            if (line.toUpperCase().equals("R"))
            {
                gamePlayController.run();
                break;
            }
            System.out.println("\t\tWRONG INPUT");
        }
        if (line.equals(StartGameConsole.EXIT))
            gameOver();

    }

    @Override
    public void updateWinEnemyMode() {
        System.out.println("\t\tYOU WIN THIS ENEMY!");
    }

    @Override
    public void updateWinGameMode() {
        System.out.println("\t\tYOU WIN THE GAME");
        gameOver();
    }

    @Override
    public void updateLooseMode() {
        System.out.println("\t\tYOU ARE LOOSE");
        gameOver();

    }

    @Override
    public void updateFightProgressMode() {
        System.out.println("---------->Villain power " + game.currentVillain.getPower() + "\n " + "<----------Hero XP " + hero.getHitPoints());
    }

    @Override
    public void updateVillainsOnMup() {
        System.out.println("updateVillainsOnMup() empty method now");
    }

    @Override
    public void showLevelUpWindow() {
        String text = "Level up to " + hero.getLevel();
        for (int i = 0; i <text.length() + 2 ; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("|" + text + "|");
        for (int i = 0; i <text.length() + 2 ; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
