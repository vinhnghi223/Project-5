package pl.byd.wsg.promand.project5.add;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.database.DatabaseOpenHelper;
import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Sergey on 3/14/14.
 */

public class AddScreenActivity extends ActionBarActivity {
    EditText projectEditText,categoryEditText,inputAmountEditText,commentEditText;
    DatabaseOpenHelper databaseOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen_test);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        //References for user input edit text
        projectEditText= (EditText) findViewById(R.id.projectEditText);
        categoryEditText= (EditText) findViewById(R.id.categoryEditText);
        inputAmountEditText= (EditText) findViewById(R.id.inputAmountEditText);
        commentEditText= (EditText) findViewById(R.id.commentEditText);

        //create an instance of DatabaseOpenHelper class
        databaseOpenHelper=new DatabaseOpenHelper(this);

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

        String project=projectEditText.getText().toString();
        String category=categoryEditText.getText().toString();
        String amount= inputAmountEditText.getText().toString();
        String comment=commentEditText.getText().toString();

        //create instance of writable database so that we can modify our database through this instance
        SQLiteDatabase db= databaseOpenHelper.getWritableDatabase();

        //create instance of ContentValues which act like a map to store our key-value pair
        ContentValues contentValues=new ContentValues();
        Toast.makeText(this, "added", Toast.LENGTH_LONG).show();

        //not  complete
    }

}