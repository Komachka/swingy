package view;

import model.characthers.Hero;

public interface WindowManager {

    void showSelectedHero();
    void showNewHero();
    void showSelectedMission(Hero hero);
    void restartGame();
    void stop();
    void setup();
    void start();

}
