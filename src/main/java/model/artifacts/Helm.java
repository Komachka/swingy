package model.artifacts;

// increases hit points

//figured out how to create enum from database dynamucly and store info about type oh helm armow and weapont in database
//https://www.niceideas.ch/roller2/badtrash/entry/java_create_enum_instances_dynamically
//https://bojanv55.wordpress.com/2015/05/25/java-dynamic-enums/

public enum  Helm {
VEIL_OF_STEEL(100),
    LEORICS_CROWN(90),
    UNDEAD_CROWN(80),
    STAR_HELM(70),
    COIF(50);


    private int increaseHitPoints;

    Helm(int increaseHitPoints) {
        this.increaseHitPoints = increaseHitPoints;
    }
}
