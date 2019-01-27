package view;

import controller.CharactesController;

import javax.swing.*;
import java.awt.*;
import java.util.logging.ErrorManager;

public class NewHero extends JPanel {
    private CharactesController charactesController = new CharactesController();
    private WindowManager windowManager;

    JTextField jTextField2;
    JLabel jLabel;
    JLabel jLabe2;
    JLabel errorMessage;
    JComboBox comboBox1 ;
    JButton button1;


    public NewHero(WindowManager windowManager) {
        this.windowManager = windowManager;
        initComponents();
    }

    private void initComponents() {
        jTextField2 = new JTextField();
        jLabel = new JLabel();
        jLabe2 = new JLabel();
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        comboBox1 = new JComboBox();
        button1 = new JButton();

        jLabel.setText("Name");
        jLabe2.setText("Type");
        comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Knight", "Elf"}));
        button1.setText("Create");


        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

       /* groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                        .addGap(29, 29, 29 )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)

                            ))))
        );*/

    }
}
