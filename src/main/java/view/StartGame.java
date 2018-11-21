package view;

import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame extends JFrame implements WindowManager {

    private void initComponents()
    {
        JButton jButton = new JButton();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jButton.setText("Select Hero");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jButtonActionPerformed(event);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(122, Short.MAX_VALUE)
                                .addComponent(jButton, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addGap(88,88,88))

        );
        layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(142, Short.MAX_VALUE)
                        .addComponent(jButton)
                        .addGap(135,135,135))
        );
        pack();

    }

    private void jButtonActionPerformed(ActionEvent event) {
            showSelectedHero();
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

}
