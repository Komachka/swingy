package view;

import model.characthers.Hero;

public interface WindowManager {

    public void showSelectedHero();
    public void showNewHero();
    public void showSelectedMission(Hero hero);
    public void restartGame();
}
