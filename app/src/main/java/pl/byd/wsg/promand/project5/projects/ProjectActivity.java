package pl.byd.wsg.promand.project5.projects;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.add.AddScreenActivity;
import pl.byd.wsg.promand.project5.menus.MenuActivity;

/**
 * Created by Miguel on 14-03-2014.
 */
public class ProjectActivity extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_screen);
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
                Intent intent = new Intent(this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inputProject1(View v){
        Button buttonProj1 = (Button) findViewById(R.id.buttonProj1);
        String str = buttonProj1.getText().toString();
        Log.d("MS", "String="+str);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK,returnIntent);
        this.finish();
/*
        AddScreenActivity.projectTextView.setText(str);
        this.finish();
        */
    }

    public void inputProject2(View v){
        Button buttonProj2 = (Button) findViewById(R.id.buttonProj2);
        String str = buttonProj2.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK,returnIntent);
        this.finish();
    }

    public void inputProject3(View v){
        Button buttonProj3 = (Button) findViewById(R.id.buttonProj3);
        String str = buttonProj3.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK,returnIntent);
        this.finish();
    }

    public void inputProject4(View v){
        Button buttonProj4 = (Button) findViewById(R.id.buttonProj4);
        String str = buttonProj4.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK,returnIntent);
        this.finish();
    }




}