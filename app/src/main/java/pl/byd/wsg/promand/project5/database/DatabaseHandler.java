package pl.byd.wsg.promand.project5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import pl.byd.wsg.promand.project5.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 3/14/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MobileExpenseManagerDB";

    // Table names
    private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_PROJECT = "projects";
    private static final String TABLE_SUBCATEGORY = "subcategory";
    private static final String TABLE_EXPENSE = "expense";

    // Common column names
    private static final String KEY_ID = "id";

    // CATEGORY Table - column names
    private static final String KEY_CATEGORY = "category_name";

    // SUBCATEGORY Table - column names
    private static final String KEY_SUBCATEGORY = "subcategory_name";

    // PROJECT Table - column names
    private static final String KEY_PROJECT = "project_name";

    // EXPENSE Table - column names
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_SUBCATEGORY_ID = "subcategory_id"; //SUBCATEGORY Table column
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DATE = "date";
    private static final String KEY_PHOTO = "photo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY + " TEXT" + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);

        // Create tables again
        onCreate(db);
    }

    /*
     * CRUD Operations for Category Table
     */

    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category.getCategoryName());

        db.insert(TABLE_CATEGORY, null, values);
        db.close();

    }

    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[] {KEY_ID, KEY_CATEGORY}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Category category = new Category(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Category category = new Category();
                category.setID(Integer.parseInt(cursor.getString(0)));
                category.setCategoryName(cursor.getString(1));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        return categoryList;
    }

    public int getCategoriesCount() {
        String countQuery = "SELECT * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, category.getCategoryName());

        return db.update(TABLE_CATEGORY, values, KEY_CATEGORY + " =?", new String[] {String.valueOf(category.getID())});

    }

    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?", new String[] {String.valueOf(category.getID())});

        db.close();
    }

}
