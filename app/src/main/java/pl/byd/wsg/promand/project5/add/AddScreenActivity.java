package pl.byd.wsg.promand.project5.add;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.dashboards.DashboardListViewActivity;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.database.DatabaseOpenHelper;
import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.model.ExpenseEntry;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Sergey on 3/14/14.
 */

public class AddScreenActivity extends ActionBarActivity {
    EditText inputAmountEditText,commentEditText;
    public static TextView projectTextView, categoryEditText;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen_test);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        //References for user input edit text
        projectTextView= (TextView) findViewById(R.id.projectTextView);
        categoryEditText= (TextView) findViewById(R.id.categoryEditText);
        inputAmountEditText= (EditText) findViewById(R.id.inputAmountEditText);
        commentEditText= (EditText) findViewById(R.id.commentEditText);

        //instantiate DataSource
        dataSource=new DataSource(this);
        dataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openCategoryChooser(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    public void takePhoto(View v){
      /*  Intent intent = new Intent(this, Camera.class);
        startActivity(intent);  */
    }

    public void submitExpense(View v){
     /*   Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);    */

        //get raw string data input
        String project=projectTextView.getText().toString();
        String category=categoryEditText.getText().toString();
        String amount= inputAmountEditText.getText().toString();
        String comment=commentEditText.getText().toString();

        //setup the expense entry object
        ExpenseEntry expenseEntry=new ExpenseEntry();
        expenseEntry.setProject(project);
        expenseEntry.setCategory(category);
        expenseEntry.setAmount(amount);
        expenseEntry.setComment(comment);
        expenseEntry=dataSource.create(expenseEntry);

        Toast.makeText(this, "Added "+expenseEntry.getAmount(), Toast.LENGTH_LONG).show();
        Log.i("Added", expenseEntry.getAmount());
        //not  complete
        startActivity(new Intent(this, DashboardListViewActivity.class));
    }

    /* Maintain a persistent database connection for the entire lifetime of the activity
    The connection object within any activity is cached. So you don't have to worry about calling
    the open method too many times, but you should make sure that you're explicitly closing the connection
    whenever the activity is going away. That will eliminate the possibility of what are known as database
    connection leaks. A database connection leak can cause memory and performance issues. */

    @Override
    protected void onResume() {
        super.onResume();
        //As the activity comes to the screen, it's onResume method is called. Thats when dataSource is opened
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }
}