package view;

import model.Game;
import model.characthers.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Shape playerShape;
    private Color enemyColour =  Color.GREEN;
    private Color looserColour = Color.BLUE;
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

        paintVillains(g2d);

        playerShape = new Rectangle2D.Double(game.getHeroX(), game.getHeroY(), game.getScale(), game.getScale());
        g2d.setColor(Color.GREEN);
        g2d.fill(playerShape);



    }

    public void setEnemyColour()
    {
        enemyColour = Color.MAGENTA;
    }

    private void paintVillains(Graphics2D g2d) {
        ArrayList<Villain> villains = game.getEnemies();

        for (Villain v : villains)
        {
            //System.out.println("v.getY() " + v.getY() + "v.getX() " + v.getX());
            g2d.setColor(enemyColour);
            g2d.fillOval(v.getX(), v.getY(), game.getScale(), game.getScale());
        }

        ArrayList<Villain> loosers = game.getVillainsLoosers();
        System.out.println("looser count " + loosers.size());
        for (Villain v : loosers)
        {

            g2d.setColor(looserColour);
            g2d.fillOval(v.getX(), v.getY(), game.getScale(), game.getScale());
        }
    }




    public void setEnemyLooserColour() {
        looserColour = Color.RED;
    }
}
