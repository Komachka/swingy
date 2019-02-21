package view.console;

import controller.CharactersController;
import model.InOutEnumConsole;
import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import model.characthers.Hero;
import model.characthers.HeroClass;
import view.WindowManager;

import java.util.Scanner;

public class NewHeroConsole {

    private WindowManager windowManager;
    private Scanner scanner;
    private CharactersController charactersController = new CharactersController();

    NewHeroConsole(WindowManager windowManager) {
        this.windowManager = windowManager;
        this.scanner = new Scanner(System.in);
        createHero();
    }


    private void createHero() {
        System.out.println("----------Start creating a new hero----------");
        System.out.println("\t- Print "+StartGameConsole.EXIT+" to exit");
        System.out.println("\t- Print hero name");
        String name;
        while ((name = scanner.nextLine()).equals(""))
            System.out.println("----------WRONG INPUT----------");;
        if (name.equals(StartGameConsole.EXIT))
                System.exit(0);


        System.out.println("\t- Choose hero class, print the full name");
        HeroClass heroClass = HeroClass.valueOf(readEnumProperties(HeroClass.values()[0]));

        Hero.HeroBuilder builder = new Hero.HeroBuilder(heroClass, name);

        System.out.println("\t- Choose the weapon, print the full name");
        Weapon weapon = Weapon.valueOf(readEnumProperties(Weapon.values()[0]));
        System.out.println("\t- Choose the armor, print the full name");
        Armor armor = Armor.valueOf(readEnumProperties(Armor.values()[0]));
        System.out.println("\t\t- Choose the helm, print the full name");
        Helm helm = Helm.valueOf(readEnumProperties(Helm.values()[0]));
        builder.setAttack(heroClass.getAttack())
                .setDefense(heroClass.getDefense())
                .addWeapon(weapon)
                .addArmor(armor)
                .addHelm(helm);

        if (!charactersController.saveHero(builder.build()))
            System.out.println("The hero with this name is already exists");
        else {
            System.out.println("Hero is successfully created");
            windowManager.showSelectedHero();
        }

    }



    private   String readEnumProperties(InOutEnumConsole enymType)
    {
        String inputValue = null;
        enymType.printAll();

        while (inputValue == null)
        {
            try {
                inputValue = scanner.nextLine();
                if (inputValue == StartGameConsole.EXIT)
                    System.exit(0);
                if (!enymType.contains(inputValue))
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {

                if (inputValue.equals(StartGameConsole.EXIT))
                    System.exit(0);
                inputValue = null;
                System.out.println("----------WRONG INPUT----------");
            }
        }
        return inputValue.toUpperCase();
    }

}
