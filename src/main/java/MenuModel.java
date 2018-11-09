import java.util.ArrayList;

public  class MenuModel implements MenuModelInterface {

    String menuModelname;
    ArrayList <CreateHeroObserver> createHerroObservers = new ArrayList();

    public MenuModel() {
        System.out.println("MenuModel created");
    }

    public void initialise() {
        menuModelname = "Test";
    }

    public void on() {
        System.out.println(menuModelname);
    }


    void chooseHeroes()
    {
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (int i = 0; i <createHerroObservers.size() ; i++) {
            createHerroObservers.get(i).update();
        }
    }

    public void registerObserver(CreateHeroObserver createHeroObserver) {

    }

    public void removeObserver(CreateHeroObserver createHeroObserver) {

    }


    public void addObserver(CreateHeroObserver observer)
    {
        createHerroObservers.add(observer);
    }
}
