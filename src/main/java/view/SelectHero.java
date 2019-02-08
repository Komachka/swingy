package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import storage.HerroStorage;

public class SelectHero extends JPanel implements ActionListener {
    //private static SelectHero selectHero;
    private static final String ACTION_SET = "Set";
    private static final String ACTION_CANCEL = "Cancel";
    private WindowManager windowManager;
    private JList availableHeroes;
    private HerroStorage storage;
    private String initialValue;
    private static String value = "";
    JTextArea area;
    private JPanel listPane;



    public SelectHero(WindowManager windowManager)  {
        System.out.println("Select hero constructor");
        this.windowManager = windowManager;
        storage = new HerroStorage();
        setSize(450,400);
        setLayout(new BorderLayout());
        initComponents();

        setVisible(true);

    }



    private void initComponents() {
        //cancel but
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand(ACTION_CANCEL);

        //set but
        final JButton setButton = new JButton("Set");
        setButton.setActionCommand(ACTION_SET);
        setButton.addActionListener(this);
        add(setButton);
        listPane = new JPanel();

        JLabel label = new JLabel("Available heroes");
        label.setLabelFor(availableHeroes);
        listPane.add(label);
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        initJList();


        JPanel statusPane = new JPanel();
        statusPane.setLayout(new BoxLayout(statusPane, BoxLayout.PAGE_AXIS));
        JLabel label2 = new JLabel();

        area = new JTextArea(30, 30);
        area.setText("Hero info...");
        label2.setLabelFor(area);

        statusPane.add(Box.createRigidArea(new Dimension(50,5)));
        statusPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        statusPane.add(label2);
        statusPane.add(area);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);

        add(listPane, BorderLayout.WEST);
        add(statusPane, BorderLayout.EAST);
        add(buttonPane, BorderLayout.SOUTH);
        setValue(initialValue);

    }

    private void initJList() {
        if(storage.getAllHeroes() == null || storage.getAllHeroes().size() <= 0)
        {

            JLabel labelNoHero = new JLabel("There are no hero to select yet. Go to Create hero menu");
            listPane.add(labelNoHero);
            return;

        }
        else
        {

        }
        availableHeroes = new JList(storage.getAllHeroeNames().toArray())
        {
            @Override
            public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {

                int row;
                if (orientation == SwingConstants.VERTICAL && direction < 0 && (row = getFirstVisibleIndex()) != -1)
                {
                    Rectangle rectangle = getCellBounds(row, row);
                    if ((rectangle.y == visibleRect.y) && (row != 0))
                    {
                        Point loc = rectangle.getLocation();
                        loc.y --;
                        int prevIndex = locationToIndex(loc);
                        Rectangle prevR = getCellBounds(prevIndex, prevIndex);
                        if (prevR == null || prevR.y >=rectangle.y)
                        {
                            return 0;
                        }
                        return  prevR.height;
                    }
                }
                return super.getScrollableUnitIncrement(visibleRect, orientation, direction);
            }
        };
        availableHeroes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        availableHeroes.setLayoutOrientation(JList.VERTICAL);
        availableHeroes.setVisibleRowCount(-1);

        availableHeroes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if (e.getClickCount() == 2)
                {
                    int index = availableHeroes.getSelectedIndex();
                    area.setText(storage.getHero(index).toString());

                    //it in on if we want to emulate click on set button
                    //setButton.doClick(); // emulate button click
                }
            }
        });
        JScrollPane listScroller = new JScrollPane(availableHeroes);
        listScroller.setPreferredSize(new Dimension(250, 250));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        listPane.add(listScroller);
    }

    private void setValue(String newValue) {
        if (newValue != null && newValue.isEmpty())
        {
            value = newValue;
            availableHeroes.setSelectedValue(value, true);

        } }


    // this method in started when mouseClicked and  e.getClickCount() == 2
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e.getActionCommand() " + e.getActionCommand());
        if (ACTION_SET.equals(e.getActionCommand()))
        {
            if (availableHeroes.getSelectedIndex() != -1)
            {
                windowManager.showSelectedMission(storage.getHero(availableHeroes.getSelectedIndex()));
            }
        }

        if (ACTION_CANCEL.equals(e.getActionCommand()))
        {
            windowManager.restartGame();
        }



    }
}
