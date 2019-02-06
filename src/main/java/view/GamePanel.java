package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {

    Shape shape;
    Game game;

    public GamePanel(Game game) {
        this.game = game;
        shape = new Rectangle2D.Double(game.getHeroX(), game.getHeroX(), 10, 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.RED);
        Graphics2D g2d = (Graphics2D)g;


        // Save the current transform of the graphics contexts.
        AffineTransform saveTransform = g2d.getTransform();
        // Create a identity affine transform, and apply to the Graphics2D context
        AffineTransform identity = new AffineTransform();
        g2d.setTransform(identity);

        // Paint Shape (with identity transform), centered at (0, 0) as defined.
        g2d.setColor(Color.GREEN);
        g2d.fill(shape);
        // Translate to the initial (x, y) position, scale, and paint
        //g2d.translate(x, y);
        //g2d.scale(1.2, 1.2);
        //g2d.fill(shape);

 /*       // Try more transforms
        for (int i = 0; i < 5; ++i) {
            g2d.translate(50.0, 5.0);  // translates by (50, 5)
            g2d.setColor(Color.BLUE);
            g2d.fill(shape);
            g2d.rotate(Math.toRadians(15.0)); // rotates about transformed origin
            g2d.setColor(Color.RED);
            g2d.fill(shape);
        }
        // Restore original transform before returning
        g2d.setTransform(saveTransform);*/

    }

    public void moveShape()
    {
        System.out.println("x  : "  + game.getHeroX() +  " y : "  + game.getHeroY());
        shape = new Rectangle2D.Double(game.getHeroX(), game.getHeroY(), 10, 10);
    }
}
