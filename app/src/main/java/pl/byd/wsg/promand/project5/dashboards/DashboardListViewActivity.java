package pl.byd.wsg.promand.project5.dashboards;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.model.ExpenseEntry;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;

/**
 * Created by Miguel on 14-03-2014.
 */
public class DashboardListViewActivity extends ListActivity {
    DataSource dataSource;
    List<ExpenseEntry> expenseEntryList;
    private static final int EXPENSE_ENTRY_DETAIL_ACTIVITY = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_listview);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        //instantiate DataSource
        dataSource=new DataSource(this);
        dataSource.open();
        expenseEntryList=dataSource.findAll();

        if (expenseEntryList.size()==0){
            expenseEntryList=dataSource.findAll();  /// NECESSARY??
        }
        refreshDisplay();
    }

    public void refreshDisplay(){
        ListView dataList=(ListView)findViewById(android.R.id.list);
        ArrayAdapter<ExpenseEntry> adapter=new ArrayAdapter<ExpenseEntry>(this, android.R.layout.simple_list_item_1,expenseEntryList);
        dataList.setAdapter(adapter);
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


    public void goCategories(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivityForResult(intent, 2);
    }

    public void filterProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivityForResult(intent, 1);


        /*Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);*/

        /*
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, v);
        //Inflating the Popup using xml file
        //popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                if(item.getTitle()=="Project 1"){
                    expenseEntryList=dataSource.findFiltered("project = \"project 1\"","amount ASC");
                }
                if(item.getTitle()=="Project 2"){
                    expenseEntryList=dataSource.findFiltered("project = \"project 2\"","amount ASC");
                }
                refreshDisplay();
                return true;
            }
        });
        popup.show();//showing popup menu
        // */

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result=data.getStringExtra("result");
                expenseEntryList=dataSource.findFiltered("project = \""+result+"\"" ,"amount ASC");
                refreshDisplay();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }
        if (requestCode ==2){
            if (resultCode==RESULT_OK){
                String result=data.getStringExtra("result");
                expenseEntryList=dataSource.findFiltered("category = \""+result+"\"" ,"amount ASC");
                refreshDisplay();
            }
            if (resultCode == RESULT_CANCELED){
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }
    }//onActivityResult

    public void goDashboard(View v){
        Intent intent = new Intent(this, DashboardGraphActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ExpenseEntry expenseEntry=expenseEntryList.get(position);
        Intent intent = new Intent(this, ExpenseEntryDetailActivity.class);
        intent.putExtra(".model.ExpenseEntry",expenseEntry);

        startActivityForResult(intent, EXPENSE_ENTRY_DETAIL_ACTIVITY);
    }



}