package view;

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
    GamePanel gamePnel;

    JButton buttonUp = new JButton("UP");
    JButton buttonRight = new JButton("RIGHT");
    JButton buttonDown = new JButton("DOWN");
    JButton buttonLeft = new JButton("LEFT");


    // Define an arrow shape using a polygon centered at (0, 0)
    int[] polygonXs = { -20, 0, +20, 0};
    int[] polygonYs = { 20, 10, 20, -20};
    //Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);


    double x = 50.0, y = 50.0;  // (x, y) position of this Shape


    public PlayMission(Hero hero) {
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

        Dimension d = gamePnel.getSize();
        System.out.println("d.height " + d.height + "d.weight " + d.width);



    }



    public void startGame(Hero hero) {


        // 1 create game object
        // 2 show map
        //open window to select the direction
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click");
        boolean fight = false;
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
        if (game.playGameRoundAndCloseToenemy(direction))
            fight = true;
        gamePnel.repaint();
        if (fight)
            showFightWindow();
        }

    private void showFightWindow() {
        System.out.println("Fight window");
    }

}


