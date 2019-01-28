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


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_HEROCLASS + " TEXT NOT NULL, " +
                    COLUMN_LEVEL + " TEXT NOT NULL, " +
                    COLUMN_EXPERIENCE + " TEXT NOT NULL, " +
                    COLUMN_ATTACK + " TEXT NOT NULL, " +
                    COLUMN_DEFENSE + " TEXT NOT NULL, " +
                    COLUMN_HP + " TEXT NOT NULL);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final String SQL_SELECT_ALL =
            "SELECT * FROM " + TABLE_NAME;


    public static final String INSERT_INTO =
            "INSERT INTO "+TABLE_NAME+" ("+
                    COLUMN_NAME +", "+
                    COLUMN_HEROCLASS +", "+
                    COLUMN_LEVEL +", "+
                    COLUMN_EXPERIENCE +", "+
                    COLUMN_ATTACK +", "+
                    COLUMN_DEFENSE +", "+
                    COLUMN_HP +") " +
                    "VALUES(?,?,?,?,?,?,?);";


}
