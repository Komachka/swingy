package lanch;

import view.WindowManager;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public  class MainClass {
    public static final String GAME_MODE_CONSOLE = "console";
    public static final String GAME_MODE_GUI = "gui";

    public static Configuration<?> config = Validation.byDefaultProvider().configure();
    public static ValidatorFactory factory = config.buildValidatorFactory();

    public static void main(String[] args)  {

        String gameMode = "";
        if (args.length == 1 && args[0].equals("console")) {
            gameMode = GAME_MODE_CONSOLE;
        } else if (args.length == 1 && args[0].equals("gui")) {
            gameMode = GAME_MODE_GUI;
        }
        WindowManager gameManager = GameObjFactory.newInstance(gameMode);
        if(gameManager!=null)
        {
            gameManager.setup();
            gameManager.start();
        }
        else
        {
            System.out.println("Usage ./swingy gui | ./swingy console");
        }
        factory.close();
    }

}
