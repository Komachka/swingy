package view;

import controller.CharactersController;
import controller.GamePlayController;
import model.Game;
import model.GameMup;
import model.characthers.Character;
import model.characthers.Hero;
import model.characthers.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayMission extends JPanel implements ActionListener, MoveObserver{
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 650;
    public static final int BUTTON_PANEL_WIDTH = 600;
    public static final int BUTTON_PANEL_HEIGHT = 50;


    private Game game;
    private Hero hero;
    private GamePlayController controller;
    private CharactersController charactersController = new CharactersController();
    private GamePanel gamePanel;
    private WindowManager windowManager;

    JButton buttonUp = new JButton("North");
    JButton buttonRight = new JButton("East");
    JButton buttonDown = new JButton("South");
    JButton buttonLeft = new JButton("West");




    double x = 50.0, y = 50.0;  // (x, y) position of this Shape


    public PlayMission(Hero hero, WindowManager windowManager) {
        this.windowManager = windowManager;
        this.hero = hero;
        System.out.println("play mission " + hero.toString());
        game = new Game(this.hero);
        this.controller = new GamePlayController();
        this.gamePanel = new GamePanel(game);
        game.registerObserver((MoveObserver) this);
        createView();

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
        gamePanel.setPreferredSize(new Dimension(game.getMapW(), game.getMapH()));
        gamePanel.setMaximumSize(new Dimension(game.getMapW(), game.getMapH()));
        add(gamePanel, BorderLayout.CENTER);
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
            gamePanel.setEnemyColour();
            gamePanel.repaint();
            showGameOverWindow("Game over. You are WINNER!");
            return;
        }
        if (game.isFightMode) {
            gamePanel.setEnemyColour();
            gamePanel.repaint();
            showFightModeWindow(direction);
            return;
        }
        gamePanel.repaint();
    }

    private void showGameOverWindow(String message) {
        System.out.println("Game over window");
        JOptionPane.showMessageDialog(null,message);
        gameOver();

    }

///TODO переписать этот метод!!
    private void showFightModeWindow(int direction)
    {
        //if click on fight you fight
        //if you loose to run you fight


        System.out.println("Fight window");
        int answer = JOptionPane.showConfirmDialog(this, "FIGHT???\n\n\nCLICK YES TO FIGHT\n CLICK NO TO RUN");
        if (answer == JOptionPane.YES_OPTION) {
            if (!game.fight())
            {
                System.out.println("Hero is loose");
                showGameOverWindow("YOU ARE LOOSER");
                gameOver();
            }
            else
            {
                game.addExperience();
                JOptionPane.showMessageDialog(null,"You are win with this enemy");
                //add observer to know if experence increase


            }
        } else if (answer == JOptionPane.NO_OPTION) {
             if (game.run())
             {
                 game.playRound(-direction);
             }
             else
             {
                 if (!game.fight())
                 {
                     System.out.println("Hero is loose");
                     showGameOverWindow("YOU ARE LOOSER");
                     gameOver();
                 }
                 else
                 {
                     game.addExperience();
                     JOptionPane.showMessageDialog(null,"You are win with this enemy");
                     //add observer to know if experence increase


                 }
             }

        }
    }


    private void gameOver()
    {
        System.out.println("Game over");
        game.addExperience();
        charactersController.updateHero(hero);
        windowManager.restartGame();
    }

}


