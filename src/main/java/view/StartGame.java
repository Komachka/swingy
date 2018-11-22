package view;

import model.characthers.Hero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartGame extends JFrame implements WindowManager {


    public StartGame() {
        initComponents();

    }

    private void initComponents()
    {
        JButton selectHeroBut = new JButton();
        JButton createNewHeroBut = new JButton();
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

        setSize(400,400);
        setLayout(new GridLayout(3, 1));

        JLabel headerLabel = new JLabel("Choose game settings",JLabel.CENTER);
        headerLabel.setSize(350,100);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        setBackground(Color.darkGray);
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(createNewHeroBut)
                                .addComponent(selectHeroBut))));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addComponent(createNewHeroBut)
                .addComponent(selectHeroBut));

        setLayout(layout);
        pack();


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

    public void start() {
        setVisible(true);
    }
}
