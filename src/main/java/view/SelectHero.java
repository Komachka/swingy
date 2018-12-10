package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.characthers.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectHero extends JPanel implements ActionListener {
    private static final String ACTION_SET = "Set";
    private WindowManager windowManager;


    private static SelectHero dialog;
    private JList availableHeroes;
    //private List<Hero> heroes;
    private List<String> heroes;





    public SelectHero(WindowManager windowManager)  {
        this.windowManager = windowManager;
        setSize(450,400);

        heroes = new ArrayList<String>();
        heroes.add("Bob");
        heroes.add("Bill");



        initComponents();


    }

    private void initComponents() {
        final JButton setButton = new JButton("Set");
        setButton.setActionCommand(ACTION_SET);
        setButton.addActionListener(this);
        add(setButton);
        //getRootPane().setDefaultButton(setButton);

        availableHeroes = new JList(heroes.toArray());
        availableHeroes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        availableHeroes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        availableHeroes.setVisibleRowCount(-1);

        availableHeroes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if (e.getClickCount() == 2)
                {
                    setButton.doClick(); // emulate button click
                }
            }
        });
        add(availableHeroes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ACTION_SET.equals(e.getActionCommand()))
        {
            System.out.println(availableHeroes.getSelectedValue().toString());
        }

    }
}
