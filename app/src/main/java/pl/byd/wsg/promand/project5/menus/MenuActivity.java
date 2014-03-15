package pl.byd.wsg.promand.project5.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.byd.wsg.promand.project5.add.AddScreenActivity;
import pl.byd.wsg.promand.project5.dashboards.DashboardGraphActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Miguel on 14-03-2014.
 */
public class MenuActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

    }

    public void goDashboard(View v){
        Intent intent = new Intent(this, DashboardGraphActivity.class);
        startActivity(intent);
    }

    public void goAddScreen(View v){
        Intent intent = new Intent(this, AddScreenActivity.class);
        startActivity(intent);
    }
}
