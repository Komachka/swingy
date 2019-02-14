import view.WindowManager;
import view.console.StartGameConsole;
import view.swing.StartGameSwing;

public class GameObjFactory {

    public static WindowManager newInstance(String gameMode)
    {
        if (gameMode.equals("gui"))
            return new StartGameSwing();
        else if (gameMode.equals("console"))
            return new StartGameConsole();
        else
            return null;
    }

}
