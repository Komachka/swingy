public class ConsoleFactory extends GameObjFactory {
    public MenuModel createMenu() {
        return new ConsoleMenuModel();
    }

    public MapView createMapView() {
        return new ConsoleView();
    }
}
