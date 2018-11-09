public class SwingFactory extends GameObjFactory {
    public MenuModel createMenu() {
        return new SwingMenuModel();
    }

    public MapView createMapView() {
        return new SwingView();
    }
}
