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

    public PlayMissionConsole(Game game, WindowManager windowManager) {
        this.windowManager = windowManager;
        this.game = game;
        this.hero = game.getHero();
        scanner = new Scanner(System.in);
        game.registerObserver((MoveObserver) this);
        game.getHero().registerLevelUpObserver((LevelUpObserver) this);
        this.gamePlayController = new GamePlayController(game, this);
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
        System.out.println("----------Print U , R , D, L for move----------");
    }

    private void printMap() {
        for (int i = 0; i <game.getGameSquare() ; i++) {
            for (int j = 0; j <game.getGameSquare() ; j++) {
                if (j == hero.getX()/game.getScale() && i == hero.getY()/game.getScale())
                    System.out.print("X\t");
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }
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
        for (int i = 0; i <game.getGameSquare() ; i++) {
            for (int j = 0; j <game.getGameSquare() ; j++) {
                for (Villain v: villains) {
                    if (j == v.getX()/game.getScale() && i == v.getY()/game.getScale())
                        System.out.print("!\t");
                }
                for (Villain v: vLoosers) {
                    if (j == v.getX()/game.getScale() && i == v.getY()/game.getScale())
                        System.out.print("X\t");
                }
                if (j == hero.getX()/game.getScale() && i == hero.getY()/game.getScale())
                    System.out.print("O\t");
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }

    }

    @Override
    public void showGameOverWindow(String message) {
        System.out.println("show game over");

    }

    @Override
    public void showFightModeWindow() {
        System.out.println("Fight");
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
        System.out.println("LOOSE GAME");
    }

    @Override
    public void updateFightProgressMode() {
        System.out.println("FIGHT PROGRESS");
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
