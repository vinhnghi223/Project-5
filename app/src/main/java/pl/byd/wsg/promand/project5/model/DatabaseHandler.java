package pl.byd.wsg.promand.project5.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.byd.wsg.promand.project5.utils.ClassLogger;

import java.sql.SQLException;

/**
 * Created by sergio on 3/14/14.
 */
public class DatabaseHandler extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "MobileExpenceMenegerDB";
    private static final int DATABASE_VERSION = 1;
    //private static final int NOT_MAPPED = -1;
    private static ClassLogger logger = new ClassLogger(DatabaseHandler.class);

    private Dao<Category, Integer> categoryDao;
    private Dao<Subcategory, Integer> subcategoryDao;
    private Dao<Project, Integer> projectDao;
    private Dao<Expense,Integer> expenseDao;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Subcategory.class);
            TableUtils.createTable(connectionSource, Project.class);
            TableUtils.createTable(connectionSource, Expense.class);

        } catch (SQLException e) {
            logger.e("Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Subcategory.class, true);
            TableUtils.dropTable(connectionSource, Project.class, true);
            TableUtils.dropTable(connectionSource, Expense.class, true);

            onCreate(db, connectionSource);
        } catch (SQLException e) {
            logger.e("Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Category, Integer> getCategoryDao() throws SQLException {
        if (categoryDao == null) {
            categoryDao = getDao(Category.class);
        }
        return categoryDao;
    }

    public Dao<Subcategory, Integer> getSubcategoryDao() throws SQLException {
        if (subcategoryDao == null) {
            subcategoryDao = getDao(Subcategory.class);
        }
        return subcategoryDao;
    }

    public Dao<Project, Integer> getProjectDao() throws SQLException {
        if (projectDao == null) {
            projectDao = getDao(Project.class);
        }
        return projectDao;
    }

    public Dao<Expense, Integer> getExpenseDao() throws SQLException {
        if (expenseDao == null) {
            expenseDao = getDao(Expense.class);
        }
        return expenseDao;
    }
}
