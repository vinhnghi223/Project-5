package pl.byd.wsg.promand.project5;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import com.j256.ormlite.dao.Dao;
import pl.byd.wsg.promand.project5.model.DatabaseHandler;
import pl.byd.wsg.promand.project5.model.Expense;

import java.sql.SQLException;

/**
 * Created by Miguel on 14-03-2014.
 */
public class reportListActivity extends Activity {

    DatabaseHandler db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_listview);

        db = new DatabaseHandler(getApplicationContext());

        Expense exp1 = new Expense(1, "20.00", "12/03/2014");
        Expense exp2 = new Expense(2, "19.00", "11/03/2014");
        Expense exp3 = new Expense(3, "18.00", "10/03/2014");
        Expense exp4 = new Expense(4, "17.00", "09/03/2014");

        try {
            Dao<Expense,Integer> dao = db.getExpenseDao();
            dao.create(exp1);
            dao.create(exp2);
            dao.create(exp3);
            dao.create(exp4);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
