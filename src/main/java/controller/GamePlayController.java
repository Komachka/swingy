package controller;

import model.Game;
import view.IPlayMissionView;
import view.swing.PlayMissionSwing;

public class GamePlayController {
    private Game game;
    private IPlayMissionView view;
    private String mode;


    public GamePlayController(Game game, IPlayMissionView view, String gameMode) {
        this.game = game;
        this.view = view;
        this.mode = gameMode;
        game.setupGameMup(gameMode);
        this.mode = gameMode;
        view.createView();
    }

    public void playGameRound(int direction)
    {
        game.playRound(direction);
        view.updateView();
    }


    public void fight() {
        game.fight();
    }

    public void run() {
        if (!game.run())
            game.fight();
        else
            playGameRound(-view.getHeroDirection());
    }



}
