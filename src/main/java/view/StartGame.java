package view;

import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class StartGame extends JFrame implements WindowManager {

    private JButton selectHeroBut;
    private JButton createNewHeroBut;
    private JLabel headerLabel;
    private StartGame instance;


    public StartGame() {
        super("Swinngy");
        setup();


    }

    private void initComponents()
    {
        selectHeroBut = new JButton();
        createNewHeroBut = new JButton();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        selectHeroBut.setText("Select Hero");
        selectHeroBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showSelectedHero();
            }
        });
        createNewHeroBut.setText("Create New Hero");
        createNewHeroBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewHero();
            }
        });


        setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("Choose game settings",JLabel.CENTER);
        headerLabel.setSize(350,100);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        setBackground(Color.darkGray);
        JPanel panel = new JPanel( );
        add(headerLabel);
        panel.add(createNewHeroBut);
        panel.add(selectHeroBut);
        add(panel);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    public void showSelectedHero() {
        SelectHero selectHero = new SelectHero(this);
        setContentPane(selectHero); // we can do it because selectHero extands jframe
        pack();


    }

    public void showNewHero() {
        NewHero newHero = new NewHero(this);
        setContentPane(newHero);
        pack();
    }

    @Override
    public void showSelectedMission(Hero hero) {
        PlayMission mission = new PlayMission(this, hero);
        setContentPane(mission);
        pack();
    }

    @Override
    public void restartGame() {

        stop();
        setup();
        start();
    }


    public void start() {
        setVisible(true);
    }

    private void stop()
    {
        getContentPane().removeAll();
        repaint();
    }
    private void setup()
    {
        setSize(450,400);
        initComponents();
    }

}
