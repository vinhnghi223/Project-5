package pl.byd.wsg.promand.project5.categories;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.dashboards.DashboardListViewActivity;
import pl.byd.wsg.promand.project5.menus.MenuActivity;

public class CategoriesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL
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
                Intent intent = new Intent(this, DashboardListViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMeal_subcategory(View v){
        Intent intent = new Intent(this, MealActivity.class);
        startActivityForResult(intent, 1);

    }
    public void openTransport_subcategory(View v){
        Intent intent = new Intent(this, TransportActivity.class);
        startActivityForResult(intent, 1);
    }
    public void openCar_subcategory(View v){
        Intent intent = new Intent(this, CarActivity.class);
        startActivityForResult(intent, 1);
    }


    public void openOffice_subcategory(View v){
        Intent intent = new Intent(this, OfficeActivity.class);
        startActivityForResult(intent, 1);
    }

    public void openRepresentation_subcategory(View v) {
        Intent intent = new Intent(this, RepresentationActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",result);
                setResult(RESULT_OK,returnIntent);
                this.finish();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS","onActivityResult     resultCode=RESULT_CANCELED ");//Write the code if there's no result
            }
        }
    }//onActivityResult

}