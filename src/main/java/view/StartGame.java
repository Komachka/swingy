package view;

import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static view.ListDialog2.showDialog;

public class StartGame extends JFrame implements WindowManager {

    private JButton selectHeroBut;
    private JButton createNewHeroBut;
    private JLabel headerLabel;


    public StartGame() {
        super("Swingy");
        setSize(450,400);
        initComponents();

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
        //GroupLayout layout = new GroupLayout(getContentPane());
        //layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);

        /*layout.setHorizontalGroup(layout.createSequentialGroup()
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

        */

        add(headerLabel);
        panel.add(createNewHeroBut);
        panel.add(selectHeroBut);
        add(panel);
        pack();

    }

    public void showSelectedHero() {
        SelectHero selectHero = new SelectHero(this);
        ArrayList<String> heroes = new ArrayList<String>();
        String [] test = new String[]{"1", "2", "3", "4", "5"};
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");
        heroes.add("Bob");
        heroes.add("Bill");


        //showDialog(null, null, "Lable", "Title", heroes.toArray(new String[heroes.size()]), heroes.get(0), null);

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
