public class Game {

    private MenuModel menu;
    private MapView mapView;
    Main.GameModes mode;







    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public void setMode(Main.GameModes mode) {
        this.mode = mode;
    }
}
