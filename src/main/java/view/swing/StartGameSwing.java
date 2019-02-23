package view.swing;

import controller.GamePlayController;
import lanch.MainClass;
import model.Game;
import model.characthers.Hero;
import view.WindowManager;

import javax.swing.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;


public class StartGameSwing extends JFrame implements WindowManager {

    public StartGameSwing() {
        super("Swingy");
    }

    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        JButton selectHeroBut = new JButton();
        JButton createNewHeroBut = new JButton();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        JLabel gameName = new JLabel("SWINGY",JLabel.CENTER);
        gameName.setFont(new Font("Serif", Font.BOLD, 32));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(gameName, constraints);
        JLabel headerLabel = new JLabel("Choose game settings :",JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 28));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headerLabel, constraints);
        selectHeroBut.setText("Select Hero");
        selectHeroBut.setFont(new Font("Serif", Font.CENTER_BASELINE, 26));
        selectHeroBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showSelectedHero();
            }
        });
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(selectHeroBut, constraints);
        createNewHeroBut.setText("Create New Hero");
        createNewHeroBut.setFont(new Font("Serif", Font.CENTER_BASELINE, 26));
        createNewHeroBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewHero();
            }
        });
        constraints.gridx = 1;
        constraints.gridx = 4;
        add(createNewHeroBut, constraints);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        setBackground(Color.gray);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void showSelectedHero() {
        SelectHeroSwing selectHero = new SelectHeroSwing(this);
        setContentPane(selectHero);
        pack();
    }

    public void showNewHero() {
        NewHeroSwing newHero = new NewHeroSwing(this);
        setContentPane(newHero);
        pack();
    }

    @Override
    public void showSelectedMission(Hero hero) {
        Game model = new Game(hero);
        PlayMissionSwing view = new PlayMissionSwing(model, this);
        setContentPane(view);
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

    public void stop()
    {
        getContentPane().removeAll();
        repaint();
    }
    public void setup()
    {
        setPreferredSize(new Dimension(800, 900));
        setMaximumSize(new Dimension(800, 900));
        setMinimumSize(new Dimension(800, 900));
        initComponents();
    }

}
