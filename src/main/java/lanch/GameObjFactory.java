package lanch;

import view.WindowManager;
import view.console.StartGameConsole;
import view.swing.StartGameSwing;

public class GameObjFactory {

    public static WindowManager newInstance(String gameMode)
    {
        if (gameMode.equals(MainClass.GAME_MODE_GUI))
            return new StartGameSwing();
        else if (gameMode.equals(MainClass.GAME_MODE_CONSOLE))
            return new StartGameConsole();
        else
            return null;
    }

}
