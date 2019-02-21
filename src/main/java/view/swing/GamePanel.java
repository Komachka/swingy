package view.swing;

import model.Game;
import model.characthers.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Shape playerShape;
    private Color enemyColour =  Color.BLUE;
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
        g2d.drawRect(0, 0, game.getMapW(), game.getMapH());
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

            g2d.setColor(enemyColour);
            g2d.fillOval(v.getX(), v.getY(), game.getScale(), game.getScale());
        }
        ArrayList<Villain> loosers = game.getVillainsLoosers();
        for (Villain v : loosers)
        {

            g2d.setColor(looserColour);
            g2d.fillOval(v.getX(), v.getY(), game.getScale(), game.getScale());
        }
    }

    public void setEnemyLooserColour() {
        looserColour = Color.RED;
    }

    public void hideEnemyes() {
        enemyColour = Color.BLUE;
    }
}
