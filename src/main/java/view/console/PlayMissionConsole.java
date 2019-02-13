package view.console;

import controller.CharactersController;
import controller.GamePlayController;
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
        this.gamePlayController = new GamePlayController(game, this, "consol");
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
                    case "D":
                        heroDirection = GameMup.DOWN;
                        break;
                    case "R":
                        heroDirection = GameMup.RIGHT;
                        break;
                    case "L":
                        heroDirection = GameMup.LEFT;
                        break;
                    case "U":
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
                System.out.println("----------WRONG INPUT----------");
                inputValue = null;
            }
            System.out.println("heroDirection " + heroDirection);
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
        System.out.println("----------Print U , R , D, L for move----------");
    }

    @Override
    public int getHeroDirection() {
        return heroDirection;
    }

    //TODO remuve bacause it does not need
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
        System.out.println("Fight mode window");
    }

    @Override
    public void gameOver() {
        System.out.println("Game over");
        System.out.println("-------------------------------------------------");
        System.out.println();
        windowManager.restartGame();
    }

    @Override
    public void updatePosition() {
        printMap();

    }

    @Override
    public void updateFightMode() {
        System.out.println("Print F to fight");
        System.out.println("Print R to run");
        String line;
        while ((line = scanner.nextLine()) !=StartGameConsole.EXIT)
        {
            if (line.equals("F"))
            {
                gamePlayController.fight();
                break;
            }
            if (line.equals("R"))
            {
                gamePlayController.run();
            }
        }
        if (line.equals(StartGameConsole.EXIT))
            gameOver();
        showFightModeWindow();
    }

    @Override
    public void updateWinEnemyMode() {
        System.out.println("WIN ENEMY");
    }

    @Override
    public void updateWinGameMode() {
        System.out.println("WIN GAME");
        showEnemies();
        gameOver();
    }

    @Override
    public void updateLooseMode() {
        System.out.println("YOU ARE LOOSE");
        showEnemies();
        gameOver();

    }

    @Override
    public void updateFightProgressMode() {
        System.out.println("---------->Villain power " + game.currentVillain.getPower() + "\n " + "<----------Hero XP " + hero.getHitPoints());
    }

    @Override
    public void updateVillainsOnMup() {
        System.out.println("updateVillainsOnMup()");
    }

    @Override
    public void showLevelUpWindow() {
        System.out.println("Level up to " + hero.getLevel());
    }
}
