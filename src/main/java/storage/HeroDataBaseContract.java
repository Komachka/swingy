package storage;

public interface HeroDataBaseContract {
    public static final String TABLE_NAME = "Heroes";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HEROCLASS = "class";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_EXPERIENCE = "experience";
    public static final String COLUMN_ATTACK = "attack";
    public static final String COLUMN_DEFENSE = "defense";
    public static final String COLUMN_HP = "hitPoints";
    public static final String COLUMN_WEAPON = "weapon";
    public static final String COLUMN_HELM = "helm";
    public static final String COLUMN_ARMOR = "armor";



    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
                    COLUMN_HEROCLASS + " TEXT NOT NULL, " +
                    COLUMN_LEVEL + " TEXT NOT NULL, " +
                    COLUMN_EXPERIENCE + " TEXT NOT NULL, " +
                    COLUMN_ATTACK + " TEXT NOT NULL, " +
                    COLUMN_DEFENSE + " TEXT NOT NULL, " +
                    COLUMN_WEAPON + " TEXT NOT NULL, " +
                    COLUMN_HELM + " TEXT NOT NULL, " +
                    COLUMN_ARMOR + " TEXT NOT NULL, " +
                    COLUMN_HP + " TEXT NOT NULL);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final String SQL_SELECT_ALL =
            "SELECT * FROM " + TABLE_NAME;

    public static final String SQL_DELETE_BY_ID =
            "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = ?";


    public static final String INSERT_INTO =
            "INSERT INTO "+TABLE_NAME+" ("+
                    COLUMN_NAME +", "+          //1
                    COLUMN_HEROCLASS +", "+     //2
                    COLUMN_LEVEL +", "+         //3
                    COLUMN_EXPERIENCE +", "+    //4
                    COLUMN_ATTACK +", "+        //5
                    COLUMN_DEFENSE +", "+       //6
                    COLUMN_WEAPON +", "+        //7
                    COLUMN_HELM +", "+          //8
                    COLUMN_ARMOR +", "+         //9
                    COLUMN_HP +") " +           //10
                    "VALUES(?,?,?,?,?,?,?,?,?,?);";

    public static final String UPDATE_HERO_SQL =
            "UPDATE "+TABLE_NAME+" SET "+
                    COLUMN_LEVEL +" = ?, "+         //1
                    COLUMN_EXPERIENCE +" = ? , "+   //2
                    COLUMN_ATTACK +" = ?, "+        //3
                    COLUMN_DEFENSE +" = ?, "+       //4
                    COLUMN_WEAPON +" = ?, "+        //5
                    COLUMN_HELM +" = ?, "+          //6
                    COLUMN_ARMOR +" = ?, "+         //7
                    COLUMN_HP +" = ? " +            //8
                    "where "+COLUMN_ID+" = ?;";


}
