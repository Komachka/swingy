package model.characthers;

import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;

import javax.validation.constraints.NotNull;




/*

Hero mage = new Hero.Builder(HeroClass.MAGE, "Riobard").withHairColor(HairColor.BLACK).withWeapon(Weapon.DAGGER).build();
 • Weapon - increases the attack • Armor - increases defense • Helm - increases hit points After choosing a hero the actual game begins. The hero needs to navigate a square map with the size calculated by the formula (level-1)*5+10-(level%2). For example a hero of level 7 will be placed on a 39X39 map.

 Leveling up is based on the following formula level*1000+(level−1)2*450. So the necessary experience to level up will follow this pattern: • Level 1 - 1000 XP • Level 2 - 2450 XP • Level 3 - 4800 XP • Level 4 - 8050 XP • Level 5 - 12200 XP
 */

public class Hero {

    //Hero name
    //Hero class
    //Level
    //Experience
    //Attack
    //Defense
    //Hit point
//TODO add loombok anotations

    @NotNull String name;
    @NotNull
    private HeroClass heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    @NotNull
    private Weapon weapon; // increases the attack
    @NotNull
    private Armor armor; // increase the defense
    @NotNull
    private Helm helm; // increases hit points


    private Hero(@NotNull HeroBuilder builder) {

        this.heroClass = builder.heroClass;
        this.name = builder.name;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.helm = builder.helm;

    }


    public static class HeroBuilder {

        @NotNull
        private HeroClass heroClass;
        @NotNull
        private String name;
        @NotNull
        private Weapon weapon; // increases the attack
        @NotNull
        private Armor armor; // increase the defense
        @NotNull
        private Helm helm; // increases hit points


        public HeroBuilder(HeroClass heroClass, String name)
        {
            this.heroClass = heroClass;
            this.name = name;
        }

        public HeroBuilder addWeapon(Weapon weapon)
        {
            this.weapon = weapon;
            return this;
        }

        public HeroBuilder addArmor(Armor armor) {
            this.armor = armor;
            return this;
        }

        public HeroBuilder addHelm(Helm helm) {
            this.helm = helm;
            return this;
        }

        public Hero build()
        {
            return new Hero(this);
        }

    }



    public @NotNull Weapon getWeapon() {
        return this.weapon;
    }

    public @NotNull Armor getArmor() {
        return this.armor;
    }

    public @NotNull Helm getHelm() {
        return this.helm;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }


    public HeroClass getHeroClass() {
        return heroClass;
    }

    public String getName() {
        return name;
    }
}
