package pl.byd.wsg.promand.project5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Le on 3/16/14.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String LOGTAG = "MEM";

    private static final String DATABASE_NAME = "mem.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EXPENSE = "expense";
    public static final String COLUMN_ID = "expenseId";
    public static final String COLUMN_PROJECT = "project";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_PHOTO = "photo";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EXPENSE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PROJECT + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_AMOUNT + " TEXT, " +
                    COLUMN_COMMENT+ " TEXT, " +
                    COLUMN_PHOTO + " TEXT " +
                    ")";
//AMOUNT may be NUMERIC

    public DatabaseOpenHelper(Context context) {
        /*Each time I want to work with the database in my code,
        I'll create an instance of this open helper class, and I'll call this constructor method.
        I'll pass in the context, but the values for the database name
        and the database version will come from the constants that are defined in this class.*/
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_EXPENSE);
        onCreate(db);
    }
}
