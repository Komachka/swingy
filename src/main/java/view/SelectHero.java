package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.characthers.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectHero extends JPanel implements ActionListener {
    private static SelectHero dialog;
    private static final String ACTION_SET = "Set";
    private WindowManager windowManager;
    private JList availableHeroes;
    //private List<Hero> heroes;
    private List<String> heroes;
    String initialValue;
    private static String value = "";



    public SelectHero(WindowManager windowManager)  {
        this.windowManager = windowManager;
        setSize(450,400);
        setLayout(new BorderLayout());

        heroes = new ArrayList<String>();
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



        initComponents();
        dialog = this;
        dialog.setVisible(true);

    }



    private void initComponents() {
        //cancel but
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        //set but
        final JButton setButton = new JButton("Set");
        setButton.setActionCommand(ACTION_SET);
        setButton.addActionListener(this);
        add(setButton);





        availableHeroes = new JList(heroes.toArray())
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
        }

        ;
        availableHeroes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        availableHeroes.setLayoutOrientation(JList.VERTICAL);
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
        JScrollPane listScroller = new JScrollPane(availableHeroes);
        listScroller.setPreferredSize(new Dimension(250, 250));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);

        //Create a container so that we can add a title around
        //the scroll pane.  Can't add a title directly to the
        //scroll pane because its background would be white.
        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Available heroes");
        label.setLabelFor(availableHeroes);
        listPane.add(label);

        //listPane.add(Box.createRigidArea(new Dimension(0,5)));

        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));




        JPanel statusPane = new JPanel();
        statusPane.setLayout(new BoxLayout(statusPane, BoxLayout.PAGE_AXIS));
        JLabel label2 = new JLabel("Hero status");
        JTextArea area = new JTextArea(30, 30);
        area.setText("Hello world");
        label2.setLabelFor(area);
        statusPane.add(Box.createRigidArea(new Dimension(50,5)));
        statusPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        statusPane.add(label2);
        statusPane.add(area);


        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);




        //Put everything together, using the content pane's BorderLayout.
        //Container contentPane = this;
        add(listPane, BorderLayout.WEST);
        add(statusPane, BorderLayout.EAST);
        add(buttonPane, BorderLayout.SOUTH);
        setValue(initialValue);

    }

    private void setValue(String newValue) {
        value = newValue;
        availableHeroes.setSelectedValue(value, true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (ACTION_SET.equals(e.getActionCommand()))
        {
            System.out.println(availableHeroes.getSelectedValue().toString());
            //windowManager.showSelectedMission(heroes.get(availableHeroes.getSelectedIndex()));
        }


    }
}
