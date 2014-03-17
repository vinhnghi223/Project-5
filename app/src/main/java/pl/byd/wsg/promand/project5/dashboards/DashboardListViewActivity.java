package pl.byd.wsg.promand.project5.dashboards;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
            expenseEntryList=dataSource.findAll();
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
        startActivity(intent);
    }

    public void goProjects(View v){
        expenseEntryList=dataSource.findFiltered("project = \"project 1\"","amount ASC");
        refreshDisplay();

        /*Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);*/

        /*
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, v);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

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
        startActivity(intent);
    }
}