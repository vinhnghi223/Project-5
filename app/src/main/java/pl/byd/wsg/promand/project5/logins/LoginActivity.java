package pl.byd.wsg.promand.project5.logins;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.R;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

/* eddited by Marta 16.03*/

        EditText et = (EditText) findViewById(R.id.editTextPIN);

        Button butt = (Button) findViewById(R.id.buttonLogin);
    }


    public void enterApp(View v){

        EditText et = (EditText) findViewById(R.id.editTextPIN);
        String PIN =et.getText().toString();

        et.getEditableText().toString();

        if (PIN.equals("1234")) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

        else {

            Toast toast_login_failed = Toast.makeText(this, "Incorrect PIN - try again", Toast.LENGTH_SHORT);
            toast_login_failed.setGravity(Gravity.CENTER, 0, 0);
            toast_login_failed.show();
        }
        }

    }

