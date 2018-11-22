import view.StartGame;

public class Main {


    enum GameModes {
        GUI,
        CONSOLE
    }


    public static void main(String[] args) {


/*
        Game game = null;
        GameBuilder builder = new GameBuilder();
        GameObjFactory factory = null;

        String arg = "console";
        //String arg = "gui";
        GameModes mode = GameModes.CONSOLE;
        if (mode == GameModes.GUI){
            factory = new SwingFactory();
        }
        else if (mode == GameModes.CONSOLE)
        {
            factory = new ConsoleFactory();
        }
        game =  builder.buildGame(factory, mode);*/

        StartGame app = new StartGame();
        app.start();
    }
}
