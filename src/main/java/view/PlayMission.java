package view;

import controller.GamePlayController;
import model.Game;
import model.GameMup;
import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

// it is like a view!



public class PlayMission extends JPanel implements ActionListener, MoveObserver{
    //private Hero hero;
    private Game game;
    private Hero hero;
    private GamePlayController controller;
    GamePanel gamePnel;


    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 480;


    JLabel label1 = new JLabel("Hello");
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

        setLayout(new BorderLayout());
        JPanel a = new JPanel(new GridLayout(1,4));
        buttonUp.setActionCommand("UP");
        buttonUp.addActionListener(this);

        buttonDown.setActionCommand("DOWN");
        buttonDown.addActionListener(this);
        buttonRight.setActionCommand("RIGHT");
        buttonRight.addActionListener(this);
        buttonLeft.setActionCommand("LEFT");
        buttonLeft.addActionListener(this);
        a.add(buttonUp);
        a.add(buttonDown);
        a.add(buttonLeft);
        a.add(buttonRight);
        add(a, BorderLayout.PAGE_START);
        gamePnel.setPreferredSize(new Dimension(game.getMapW()*10, game.getMapH() * 10));
        add(gamePnel, BorderLayout.CENTER);



    }



    public void startGame(Hero hero) {


        // 1 create game object
        // 2 show map
        //open window to select the direction
    }

    public void changeMapPosition()
    {
        gamePnel.moveShape();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click");
        if (e.getActionCommand().equals("UP") || e.getActionCommand().equals("DOWN") ||
                e.getActionCommand().equals("LEFT") || e.getActionCommand().equals("RIGHT"))
        {
            System.out.println("Change position");
            if (game.playGameRound(GameMup.DOWN))
                changeMapPosition();

        }

    }


}
