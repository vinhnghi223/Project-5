package pl.byd.wsg.promand.project5.Categories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.byd.wsg.promand.project5.R;

public class CategoriesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        Context context = this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories, menu);
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
    public void openMeal_subcategory(View v){
        startActivity(new Intent(this, MealActivity.class));
        this.finish();  //GOTTA TRY ALL OF THIS IN EVERY TIME I GET OUT OF AN ACTIVITY
    }
    public void openTransport_subcategory(View v){
        startActivity(new Intent(this, TransportActivity.class));
    }
    public void openCar_subcategory(View v){
        startActivity(new Intent(this, CarActivity.class));
    }
    public void openOffice_subcategory(View v){
        startActivity(new Intent(this, OfficeActivity.class));
    }
    public void openRepresentation_subcategory(View v) {
        startActivity(new Intent(this, RepresentationActivity.class));
    }
}
