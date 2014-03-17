package pl.byd.wsg.promand.project5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.byd.wsg.promand.project5.model.ExpenseEntry;

/**
 * Created by Le on 3/17/14.
 */
public class DataSource {

    /*the idea of DataSource will be that any activity in the application can open
    or close the database connection anytime it wants to
     */
    SQLiteOpenHelper dbHelper; //
    SQLiteDatabase database;

    private static final String[] allColumns={
            DatabaseOpenHelper.COLUMN_ID,
            DatabaseOpenHelper.COLUMN_PROJECT,
            DatabaseOpenHelper.COLUMN_CATEGORY,
            DatabaseOpenHelper.COLUMN_AMOUNT,
            DatabaseOpenHelper.COLUMN_COMMENT,
            DatabaseOpenHelper.COLUMN_PHOTO
    };

    public DataSource(Context context){
        //create an instance of DatabaseOpenHelper class
        dbHelper =new DatabaseOpenHelper(context);
    }
    public void open(){
        //create instance of writable database so that we can modify our database through this instance
        //open database connection
        database= dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public ExpenseEntry create(ExpenseEntry expenseEntry){
        //create instance of ContentValues which act like a map to store our key-value pair
        //I'm not putting a value in for the ID. That's because for this table, the key is an auto-incrementing integer value. It will be generated automatically
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseOpenHelper.COLUMN_PROJECT,expenseEntry.getProject());
        contentValues.put(DatabaseOpenHelper.COLUMN_CATEGORY,expenseEntry.getCategory());
        contentValues.put(DatabaseOpenHelper.COLUMN_AMOUNT,expenseEntry.getAmount());
        contentValues.put(DatabaseOpenHelper.COLUMN_COMMENT,expenseEntry.getComment());
        contentValues.put(DatabaseOpenHelper.COLUMN_PHOTO,expenseEntry.getPhoto());
        long insertId=database.insert(DatabaseOpenHelper.TABLE_EXPENSE,null,contentValues);
        expenseEntry.setId(insertId);
        return expenseEntry;
    }

    public List<ExpenseEntry> findAll(){
        //Cursor is a reference to the data that's returned from the query
        Cursor cursor=database.query(DatabaseOpenHelper.TABLE_EXPENSE,allColumns,null,null,null,null,null);
        List<ExpenseEntry> expenseEntryList = cursorToList(cursor);
        return expenseEntryList;
    }

    public List<ExpenseEntry> findFiltered(String selection, String orderBy){
        //Cursor is a reference to the data that's returned from the query
        Cursor cursor=database.query(DatabaseOpenHelper.TABLE_EXPENSE,allColumns,selection,null,null,null,orderBy);
        List<ExpenseEntry> expenseEntryList = cursorToList(cursor);
        return expenseEntryList;
    }

    private List<ExpenseEntry> cursorToList(Cursor cursor) {
        List<ExpenseEntry> expenseEntryList=new ArrayList<ExpenseEntry>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                ExpenseEntry expenseEntry=new ExpenseEntry();
                expenseEntry.setId(cursor.getLong(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_ID)));
                expenseEntry.setProject(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_PROJECT)));
                expenseEntry.setCategory(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_CATEGORY)));
                expenseEntry.setAmount(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_AMOUNT)));
                expenseEntry.setComment(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_COMMENT)));
                expenseEntry.setPhoto(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_PHOTO)));
                expenseEntryList.add(expenseEntry);
            }
        }
        return expenseEntryList;
    }

    /*public boolean removeEntry(){

    }*/
}
