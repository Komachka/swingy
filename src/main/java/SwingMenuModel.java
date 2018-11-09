import javax.swing.*;
import java.awt.*;

public class SwingMenuModel extends MenuModel {

    public SwingMenuModel() {
        new SwingGuiMenu();
    }

    class SwingGuiMenu extends JFrame
    {
        JButton butChooseHero = new JButton("ChooseHero");
        JButton butExit = new JButton("Exit");

        public SwingGuiMenu(){
            super("Hello Swing");
            this.setBounds(100, 100, 500, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = getContentPane();
            container.setLayout(new GridLayout(3, 2, 2, 2));
            container.add(butChooseHero);
            container.add(butExit);
            setVisible(true);
        }

    }

}
