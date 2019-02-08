package view;

import controller.CharactersController;
import controller.GamePlayController;
import model.Game;
import model.GameMup;
import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// it is like a view!



public class PlayMission extends JPanel implements ActionListener, MoveObserver{
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 650;
    public static final int BUTTON_PANEL_WIDTH = 600;
    public static final int BUTTON_PANEL_HEIGHT = 50;


    private Game game;
    private Hero hero;
    private GamePlayController controller;
    private CharactersController charactersController = new CharactersController();
    private GamePanel gamePnel;
    private WindowManager windowManager;

    JButton buttonUp = new JButton("North");
    JButton buttonRight = new JButton("East");
    JButton buttonDown = new JButton("South");
    JButton buttonLeft = new JButton("West");




    double x = 50.0, y = 50.0;  // (x, y) position of this Shape


    public PlayMission(Hero hero, WindowManager windowManager) {
        this.windowManager = windowManager;
        this.hero = hero;
        game = new Game(this.hero);
        this.controller = new GamePlayController();
        this.gamePnel = new GamePanel(game);
        game.registerObserver((MoveObserver) this);
        createView();
        //startGame(hero);

    }

    private void createView() {
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
        gamePnel.setPreferredSize(new Dimension(game.getMapW(), game.getMapH()));
        gamePnel.setMaximumSize(new Dimension(game.getMapW(), game.getMapH()));
        add(gamePnel, BorderLayout.CENTER);
    }



    public void startGame(Hero hero) {


        // 1 create game object
        // 2 show map
        //open window to select the direction
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
        game.playRound(direction);

        if (game.isGameOver)
        {
            gamePnel.setEnemyColour();
            gamePnel.repaint();
            showGameOverWindow();
            return;
        }
        if (game.isFightMode) {
            gamePnel.setEnemyColour();
            gamePnel.repaint();
            showFightModeWindow();
            return;
        }
        gamePnel.repaint();
    }


    //Leveling up is based on the following formula level*1000+(level − 1)2*450.
    //So the necessary experience to level up will follow this pattern:
    // • Level 1 - 1000 XP • Level 2 - 2450 XP • Level 3 - 4800 XP • Level 4 - 8050 XP • Level 5 - 12200 XP

    //If a hero wins a battle, he gains:
    //Experience points, based on the villain power.
    //Of course, he will level up if he reaches the next level experience.
    //• An artifact, which he can keep or leave.
    // Of course, winning a battle doesn’t guarantee that an artefact will be droped
    // and the quality of the artefact also varies depending on the villain’s strenght.

    private void showGameOverWindow() {
        System.out.println("Game over window");
        //int answer = JOptionPane.showConfirmDialog(this, "Game is over. You are winner");
        JOptionPane.showMessageDialog(null,"Game over. You are WIN!");
        game.increaseExpRichBorder();
        charactersController.updateHero(hero);
        windowManager.restartGame();


    }




    private void showFightModeWindow()
    {
        System.out.println("Fight window");
        int answer = JOptionPane.showConfirmDialog(this, "FIGHT???\n\n\nCLICK YES TO FIGHT \n CLICK NO TO RUN");
        if (answer == JOptionPane.YES_OPTION) {
            game.fight();
        } else if (answer == JOptionPane.NO_OPTION) {
            // User clicked NO.
        }
    }

}


