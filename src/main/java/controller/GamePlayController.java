package controller;

import model.Game;
import model.GameMup;
import view.PlayMission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePlayController {
    //model
    private Game game;
    //view
    private PlayMission view;


    public GamePlayController(Game game, PlayMission view) {
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
        game.playRound(direction);
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
