package model.artifacts;

// increases hit points

import model.InOutEnumConsole;

public enum  Helm implements InOutEnumConsole {
VEIL_OF_STEEL(100),
    LEORICS_CROWN(90),
    UNDEAD_CROWN(80),
    STAR_HELM(70),
    COIF(50);


    private int increaseHitPoints;

    Helm(int increaseHitPoints) {
        this.increaseHitPoints = increaseHitPoints;
    }

    public int getIncreaseHitPoints() {
        return increaseHitPoints;
    }

    public static void print() {
        for (Helm c : Helm.values()) {
            System.out.println(c.name() + " Increase the XP " + c.increaseHitPoints);
        }
    }

    @Override
    public void printAll() {
        print();
    }

    @Override
    public boolean contains(String name)
    {
        for (Helm h : Helm.values())
        {
            if (h.name().equals(name.toUpperCase()))
                return true;
        }
        return false;
    }


}
