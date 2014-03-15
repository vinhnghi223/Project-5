package pl.byd.wsg.promand.project5.add;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Sergey on 3/14/14.
 */

public class AddScreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_screen);

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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openCategoryChooser(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void chooseProjectButton(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    public void takePhoto(View v){
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }
/*
    public void submitExpense(View v){
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }
*/
}