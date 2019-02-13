package controller;

import model.Game;
import view.IPlayMissionView;
import view.swing.PlayMissionSwing;

public class GamePlayController {
    //model
    private Game game;
    //view
    private IPlayMissionView view;
    private String mode;


    public GamePlayController(Game game, IPlayMissionView view, String gameMode) {
        this.game = game;
        this.view = view;
        this.mode = gameMode;
        game.setupGameMup(gameMode);
        view.createView();


        //view.createControls();

        //view.disableStopMenuItem();
        //view.enableStartMenuItem();
        //model.initialize();
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
