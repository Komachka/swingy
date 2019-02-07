package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {

    private Shape playerShape;
    private Shape enemyShape;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        System.out.println(game.getMapW()+ " " +  game.getMapH());
        g2d.drawRect(0, 0, game.getMapW(), game.getMapH());
        // Save the current transform of the graphics contexts.
        AffineTransform saveTransform = g2d.getTransform();
        // Create a identity affine transform, and apply to the Graphics2D context
        AffineTransform identity = new AffineTransform();
        g2d.setTransform(identity);

        g2d.setColor(Color.RED);
        g2d.fillOval(game.getEnemyX(), game.getEnemyY(), game.getScale(), game.getScale());

        playerShape = new Rectangle2D.Double(game.getHeroX(), game.getHeroY(), game.getScale(), game.getScale());
        g2d.setColor(Color.GREEN);
        g2d.fill(playerShape);



    }


}
