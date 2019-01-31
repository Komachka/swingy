package view;

import controller.CharactesController;
import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import model.characthers.HeroClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.ErrorManager;

public class NewHero extends JPanel implements ActionListener {
    private static final String ACTION_SAVE = "Save";
    private static final String ACTION_CANCEL = "Cancel";
    private CharactesController charactesController = new CharactesController();
    private WindowManager windowManager;
    //private static NewHero newHero;

    public NewHero(WindowManager windowManager) {
        System.out.println("New hero constructor");
        this.windowManager = windowManager;
        //newHero = this;
        setSize(450,400);
        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        System.out.println("Init components");

        setLayout(new GridBagLayout());



        JLabel labelUsername = new JLabel("Enter name:  ");
        JLabel labelRase = new JLabel("Choose the race:  ");
        JLabel labelWeapon = new JLabel("Choose the weapon:  ");
        JLabel labelArmor = new JLabel("Choose the armor:  ");
        JLabel labelHelm = new JLabel("Choose the helm:  ");
        JTextField textUsername = new JTextField(20);



        JButton cancelButton = new JButton("Cancel");
        JButton buttonSave = new JButton("Submit");


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelUsername, constraints);

        constraints.gridx = 1;
        add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelRase, constraints);


        JComboBox<String> heroClasses = new JComboBox(HeroClass.values());
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(heroClasses, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelWeapon, constraints);


        JComboBox<String> weapons = new JComboBox(Weapon.values());
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(weapons, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        add(labelArmor, constraints);



        JComboBox<String> armors = new JComboBox(Armor.values());
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(armors, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        add(labelHelm, constraints);


        JComboBox<String> helms = new JComboBox(Helm.values());
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(helms, constraints);


        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        cancelButton.setActionCommand(ACTION_CANCEL);
        cancelButton.addActionListener(this);
        add(cancelButton, constraints);



        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        buttonSave.setActionCommand(ACTION_SAVE);
        buttonSave.addActionListener(this);
        add(buttonSave, constraints);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Create new hero"));

        // add the panel to this frame

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e.getActionCommand() " + e.getActionCommand());
        if (ACTION_SAVE.equals(e.getActionCommand()))
        {
            /*
            System.out.println("Selected value " + availableHeroes.getSelectedValue().toString());
            System.out.println("Selected index " + availableHeroes.getSelectedIndex());

            int index = availableHeroes.getSelectedIndex();
            StringBuilder heroInfo = new StringBuilder("Hero Name\n");
            heroInfo.append(storage.getHero(index).getName()).append("\n");

            //TODO method getArmor
            heroInfo.append("Herro armor ").append(storage.getHero(index).getArmor().toString()).append("\n");
            area.setText(heroInfo.toString());
            */
            //start mission with spesefic hero

            //createHero();
            //saveHerroToDatabase();
            //windowManager.showSelectedMission(storage.getHero(availableHeroes.getSelectedIndex()));


        }

        if (ACTION_CANCEL.equals(e.getActionCommand()))
        {
            windowManager.restartGame();
        }

    }
}
