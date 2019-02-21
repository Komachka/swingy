package storage;

public interface HeroDataBaseContract {
   String TABLE_NAME = "Heroes";
   String COLUMN_ID = "_ID";
   String COLUMN_NAME = "name";
   String COLUMN_HEROCLASS = "class";
   String COLUMN_LEVEL = "level";
   String COLUMN_EXPERIENCE = "experience";
   String COLUMN_ATTACK = "attack";
   String COLUMN_DEFENSE = "defense";
   String COLUMN_HP = "hitPoints";
   String COLUMN_WEAPON = "weapon";
   String COLUMN_HELM = "helm";
   String COLUMN_ARMOR = "armor";



   String SQL_CREATE_TABLE =
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

   String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


   String SQL_SELECT_ALL =
            "SELECT * FROM " + TABLE_NAME;

   String SQL_DELETE_BY_ID =
            "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = ?";


   String INSERT_INTO =
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

   String UPDATE_HERO_SQL =
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
