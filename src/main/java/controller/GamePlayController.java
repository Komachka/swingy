package controller;

import model.Game;
import view.IPlayMissionView;
import view.swing.PlayMissionSwing;

public class GamePlayController {
    //model
    private Game game;
    //view
    private IPlayMissionView view;


    public GamePlayController(Game game, IPlayMissionView view) {
        this.game = game;
        this.view = view;
        view.createView();

        //view.createControls();

        //view.disableStopMenuItem();
        //view.enableStartMenuItem();
        //model.initialize();
    }

    public void playGameRound(int direction)
    {
        System.out.println("Play game round in controller");
        game.playRound(direction);
        System.out.println("Play game round in controller after");
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
