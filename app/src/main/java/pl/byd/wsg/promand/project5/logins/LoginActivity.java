package pl.byd.wsg.promand.project5.logins;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.R;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }


    public void enterApp(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }

}