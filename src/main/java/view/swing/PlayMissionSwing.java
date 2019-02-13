package view.swing;

import controller.CharactersController;
import controller.GamePlayController;
import model.Game;
import model.GameMup;
import model.characthers.Hero;
import view.IPlayMissionView;
import view.LevelUpObserver;
import view.MoveObserver;
import view.WindowManager;
import view.swing.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayMissionSwing extends JPanel implements ActionListener, IPlayMissionView,  MoveObserver, LevelUpObserver {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 650;
    public static final int BUTTON_PANEL_WIDTH = 600;
    public static final int BUTTON_PANEL_HEIGHT = 50;
    private int heroDirection;


    private Game game;
    private Hero hero;
    private CharactersController charactersController = new CharactersController();
    private GamePlayController gamePlayController;
    private GamePanel gamePanel;
    private WindowManager windowManager;

    JButton buttonUp = new JButton("North");
    JButton buttonRight = new JButton("East");
    JButton buttonDown = new JButton("South");
    JButton buttonLeft = new JButton("West");



    public PlayMissionSwing(Game game, WindowManager windowManager) {
        this.windowManager = windowManager;
        this.game = game;
        this.hero = game.getHero();
        this.gamePanel = new GamePanel(game);
        game.registerObserver((MoveObserver) this);
        game.getHero().registerLevelUpObserver((LevelUpObserver) this);
        this.gamePlayController = new GamePlayController(game, this, "gui");

    }

    public void createView() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel boxPane = new JPanel();
        boxPane.setLayout(new BoxLayout(boxPane, BoxLayout.X_AXIS));
        boxPane.setPreferredSize(new Dimension(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT));
        buttonUp.setActionCommand("UP");
        buttonUp.addActionListener(this);
        buttonDown.setActionCommand("DOWN");
        buttonDown.addActionListener(this);
        buttonRight.setActionCommand("RIGHT");
        buttonRight.addActionListener(this);
        buttonLeft.setActionCommand("LEFT");
        buttonLeft.addActionListener(this);
        boxPane.add(buttonUp);
        boxPane.add(buttonDown);
        boxPane.add(buttonLeft);
        boxPane.add(buttonRight);
        add(boxPane, BorderLayout.PAGE_START);
        setGamePanel();

    }

    public void setGamePanel()
    {
        gamePanel.setPreferredSize(new Dimension(game.getMapW(), game.getMapH()));
        gamePanel.setMaximumSize(new Dimension(game.getMapW(), game.getMapH()));
        add(gamePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int direction = 1;
        switch (e.getActionCommand())
        {
            case "UP":
                direction = GameMup.UP;
                break;
            case "DOWN":
                direction = GameMup.DOWN;
                break;
            case "RIGHT":
                direction = GameMup.RIGHT;
                break;
            case "LEFT":
                direction = GameMup.LEFT;
                break;
        }
        heroDirection = direction;
        gamePlayController.playGameRound(direction);

    }

    public void updateView()
    {
        gamePanel.repaint();
    }

    public void showEnemies()
    {
        gamePanel.setEnemyColour();
    }




    // if win or loose
    public void showGameOverWindow(String message) {
        System.out.println("Game over window");
        JOptionPane.showMessageDialog(null,message);

    }

    public void showFightModeWindow()
    {
        System.out.println("Fight window");
        int answer = JOptionPane.showConfirmDialog(this, "FIGHT???\n\n\nCLICK YES TO FIGHT\n CLICK NO TO RUN");
        if (answer == JOptionPane.YES_OPTION) {
            gamePlayController.fight();
        }
         else if (answer == JOptionPane.NO_OPTION) {
            gamePlayController.run();
        }
    }



    public void gameOver()
    {
        updateView();
        showEnemies();
        charactersController.updateHero(hero);
        windowManager.restartGame();
    }


    @Override
    public void updatePosition() {
        System.out.println("--------------------------Update position mode---------------------------------");
        updateView();
    }

    @Override
    public void updateFightMode() {
        System.out.println("--------------------------Update fight mode---------------------------------");
        showEnemies();
        updateView();
        showFightModeWindow();
    }

    @Override
    public void updateWinEnemyMode() {
        System.out.println("--------------------------Update win mode---------------------------------");
        game.addHeroExperience();
        JOptionPane.showMessageDialog(null,"You are win with this enemy");
        updateView();
        showCurentEnemy();

    }

    private void showCurentEnemy() {
        gamePanel.setEnemyLooserColour();
    }


    @Override
    public void updateWinGameMode() {
        showGameOverWindow("You are the winner!");
        showEnemies();
        gameOver();
    }

    @Override
    public void updateLooseMode() {
        System.out.println("--------------------------Update loose mode---------------------------------");
        showGameOverWindow("YOU ARE LOOSER");
        gameOver();
    }

    @Override
    public void updateFightProgressMode() {
        JOptionPane.showMessageDialog(null,"Villain power " + game.currentVillain.getPower() + "\n " + "Hero XP " + hero.getHitPoints());
    }

    public int getHeroDirection() {
        return heroDirection;
    }

    @Override
    public void updateVillainsOnMup()
    {
        //this.gamePanel = new GamePanel(game);
        //setGamePanel();
        updateView();
        System.out.println("-------------------------------------Update enemies on mup------------------------------");
    }

    @Override
    public void showLevelUpWindow() {
        JOptionPane.showMessageDialog(null,"Hero is level up to " + game.getHero().getLevel() + "\n " + "Hero XP is" + hero.getHitPoints());
    }
}


