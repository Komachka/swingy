public interface MenuModelInterface {


    void initialise();
    void on();

    void registerObserver(CreateHeroObserver createHeroObserver);

    void removeObserver(CreateHeroObserver createHeroObserver);
}
