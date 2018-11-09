public class GameBuilder {

    public Game buildGame(GameObjFactory factory, Main.GameModes mode)
    {

        Game game= new Game();

        MenuModel menu = factory.createMenu();
        MapView view = factory.createMapView();

        game.setMapView(view);
        game.setMenu(menu);
        game.setMode(mode);
        return new Game();
    }
}
