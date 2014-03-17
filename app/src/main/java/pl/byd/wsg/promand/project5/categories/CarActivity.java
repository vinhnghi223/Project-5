package pl.byd.wsg.promand.project5.categories;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.add.AddScreenActivity;
import pl.byd.wsg.promand.project5.menus.MenuActivity;

public class CarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_subcategory);
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
        switch (item.getItemId()) {
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

    public void inputDieselFuel(View v) {
        Button buttonDiesel = (Button) findViewById(R.id.button_diesel);
        String str = buttonDiesel.getText().toString();
        AddScreenActivity.categoryTextView.setText(str);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
      }
    public void inputFuel(View v) {
        Button buttonFuel = (Button) findViewById(R.id.button_petrol);
        String str1 = buttonFuel.getText().toString();
        AddScreenActivity.categoryTextView.setText(str1);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputPark(View v) {
        Button buttonPark = (Button) findViewById(R.id.button_parking);
        String str2 = buttonPark.getText().toString();
        AddScreenActivity.categoryTextView.setText(str2);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputMain(View v) {
        Button buttonMain = (Button) findViewById(R.id.button_maintenance);
        String str3 = buttonMain.getText().toString();
        AddScreenActivity.categoryTextView.setText(str3);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputOil(View v) {
        Button buttonOil = (Button) findViewById(R.id.button_oil);
        String str4 = buttonOil.getText().toString();
        AddScreenActivity.categoryTextView.setText(str4);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }

    public void inputTire(View v) {
        Button buttonTire = (Button) findViewById(R.id.button_tires);
        String str5 = buttonTire.getText().toString();
        AddScreenActivity.categoryTextView.setText(str5);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }

    public void inputTuneUp(View v) {
        Button buttonTuneUp = (Button) findViewById(R.id.button_tune_up);
        String str6 = buttonTuneUp.getText().toString();
        AddScreenActivity.categoryTextView.setText(str6);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }

    public void inputOther(View v) {
        Button buttonOther = (Button) findViewById(R.id.button_other);
        String str7 = buttonOther.getText().toString();
        AddScreenActivity.categoryTextView.setText(str7);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        this.finish();
}
}