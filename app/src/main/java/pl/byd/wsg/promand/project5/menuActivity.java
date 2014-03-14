package pl.byd.wsg.promand.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Miguel on 14-03-2014.
 */
public class menuActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

    }

    public void goDashboard(View v){
        Intent intent = new Intent(this, dashboardGraphActivity.class);
        startActivity(intent);
    }

    public void goAddScreen(View v){
        Intent intent = new Intent(this, AddScreenActivity.class);
        startActivity(intent);
    }
}
