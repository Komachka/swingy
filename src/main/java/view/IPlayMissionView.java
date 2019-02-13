package view;

public interface IPlayMissionView {
    void createView();
    int getHeroDirection();
    void updateView();
    void showEnemies();
    void showGameOverWindow(String message);
    void showFightModeWindow();
    void gameOver();
}
