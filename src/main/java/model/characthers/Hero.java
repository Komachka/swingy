package model.characthers;

import model.artifacts.Armor;
import model.artifacts.Helm;
import model.artifacts.Weapon;
import view.LevelUpObserver;

import javax.validation.constraints.*;

public class Hero extends Character {

    @NotNull
    private int id;
    @NotNull(message = "The hero name must not be null")
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotNull(message = "The hero class must not be null")
    private HeroClass heroClass;
    @NotNull
    @Max(value = 30, message = "The hero level must be less ot equal to 30. This hero can not be created in this game")
    private int level;
    @NotNull
    private int experience;
    @NotNull
    private int attack;
    @NotNull
    private int defense;
    @NotNull
    private int hitPoints;

    @NotNull(message = "The hero weapon must not be null")
    private Weapon weapon; // increases the attack
    @NotNull(message = "The hero armor must not be null")
    private Armor armor; // increase the defense
    @NotNull(message = "The hero helm must not be null")
    private Helm helm; // increases hit points

    private LevelUpObserver levelUpObserver;

    private Hero(@NotNull HeroBuilder builder) {

        super();
        this.id = builder.id;
        this.heroClass = builder.heroClass;
        this.name = builder.name;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.helm = builder.helm;
        this.level = builder.level;
        this.experience = builder.experience;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.hitPoints = builder.hitPoints;

    }

    public void decreaseHP(int HP) {
        this.hitPoints -= HP;
    }


    public void attack(Villain villain)
    {
        villain.decreasePower(attack);
    }

    public void setDefXP() {
        hitPoints = level * 1000 + (level - 1)* (level - 1) * 450;
    }

    public static class HeroBuilder {

        @NotNull
        private int id;
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


        @NotNull
        private int level;
        @NotNull
        private int experience;
        @NotNull
        private int attack;
        @NotNull
        private int defense;
        @NotNull
        private int hitPoints;


        public HeroBuilder(@NotNull HeroClass heroClass,@NotNull String name, int id)
        {
            this.heroClass = heroClass;
            this.name = name;
            this.id = id;
        }


        public HeroBuilder(@NotNull HeroClass heroClass, @NotNull String name) {
            this.heroClass = heroClass;
            this.name = name;
        }

        // weapon increases the attack
        public HeroBuilder addWeapon(Weapon weapon)
        {
            this.weapon = weapon;
            this.attack += weapon.getIncreasesTheAttack();
            return this;
        }

        // armor increase the defense
        public HeroBuilder addArmor(Armor armor) {
            this.armor = armor;
            this.defense += armor.getIncreaseTheDefense();
            return this;
        }

        //helm increases hit points
        public HeroBuilder addHelm(Helm helm) {
            this.helm = helm;
            this.hitPoints += helm.getIncreaseHitPoints();
            return this;
        }

        public HeroBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public HeroBuilder setExperience(int experience) {
            this.experience = experience;
            return this;
        }

        public HeroBuilder setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
            return this;
        }
        public HeroBuilder setAttack(int attack) {
            this.attack = attack;
            return this;
        }
        public HeroBuilder setDefense(int defense) {
            this.defense = defense;
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

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getId() {
        return id;
    }


    public void addExperience(int xp) {
        int nextLevel = level * 1000 + (level - 1)* (level - 1) * 450;
        if (experience + xp >= nextLevel)
            increaseLevel();
        experience += xp;


    }

    private void increaseLevel() {
        level++;
        attack += level * 3;
        defense += level * 2;
        setDefXP();
        notifyLevelUp();
    }

    @Override
    public String toString() {
        return "Hero info\n" +
                "Name\t\t" + name + "\n" +
                "Class\t\t" + heroClass + "\n" +
                "Level\t\t" + level + "\n" +
                "Experience\t\t" + experience + "\n" +
                "Attack\t\t" + attack + "\n" +
                "Defense\t\t" + defense + "\n" +
                "HitPoints\t\t" + hitPoints + "\n" +
                "Weapon\t\t" + weapon + "\n" +
                "Armor\t\t" + armor + "\n" +
                "Helm\t\t" + helm + "\n";
    }


    private void notifyLevelUp() {
        levelUpObserver.showLevelUpWindow();
    }

    public void registerLevelUpObserver(LevelUpObserver observer)
    {
        levelUpObserver = observer;
    }
}
