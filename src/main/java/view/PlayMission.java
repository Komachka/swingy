package view;

import model.characthers.Hero;

import javax.swing.*;

public class PlayMission  extends JPanel{
    private WindowManager windowManager;
    Hero hero;
    public PlayMission(WindowManager windowManager, Hero hero) {
        this.windowManager = windowManager;
        this.hero = hero;
    }
}
