package view.console;

import storage.HeroStorage;
import view.WindowManager;

import java.util.List;
import java.util.Scanner;

public class SelectHeroConsole {
    private WindowManager windowManager;
    private HeroStorage storage;
    private Scanner scanner;
    private int selectedHeroIndex;



    public SelectHeroConsole(WindowManager windowManager) {
        this.windowManager = windowManager;
        storage = new HeroStorage();
        scanner = new Scanner(System.in);
        do {
            start();
        }
        while (!accept());
        windowManager.showSelectedMission(storage.getHero(selectedHeroIndex));

    }

    private boolean accept() {
        System.out.println("\t- Print 1 to accept hero");
        System.out.println("\t- Print 2 to deny hero");
        System.out.println("\t- Print "+StartGameConsole.EXIT+" to exit");
        Scanner scanner = new Scanner(System.in);
        String answer = null;
        while ((answer = scanner.nextLine()) != null) {
            System.out.println(answer);
            switch (answer) {
                case "1":
                    return true;
                case "2":
                    return false;
                case StartGameConsole.EXIT:
                    System.exit(0);
            }
        }
        return false;
    }

    private void start() {
        printHeroTable();
        System.out.println("\t - Print hero number to see more");
        selectedHeroIndex = readSelectedIndex();
        printDeteils(selectedHeroIndex);
    }

    private void printDeteils(int selectedIndex) {
        System.out.println("|----------------------------------------|");
        System.out.println(storage.getHero(selectedIndex).toString());
        System.out.println("|----------------------------------------|");
    }

    private int readSelectedIndex() {
        String inputValue = null;
        int index = 0;

        while (inputValue == null)
        {
            try {
                inputValue = scanner.nextLine();
                index = Integer.parseInt(inputValue);
                if (index <= 0 || index > storage.getAllHeroes().size())
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                System.out.println("----------WRONG INPUT----------");
                inputValue = null;
            }
        }
        return index-1;

    }

    private void printHeroTable() {
        System.out.println("Heroes:");
        List<String> heroesNames = storage.getAllHeroeNames();
        for (int i = 1; i <=heroesNames.size() ; i++) {
            System.out.println("\t" + i + "\t" + heroesNames.get(i - 1));
        }

    }
}
