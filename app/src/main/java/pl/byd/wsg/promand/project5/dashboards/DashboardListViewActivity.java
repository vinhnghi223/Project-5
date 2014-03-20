package pl.byd.wsg.promand.project5.dashboards;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import java.util.List;
import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.add.AddScreenActivity;
import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.model.ExpenseEntry;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;

/**
 * Created by Miguel on 14-03-2014.
 */
public class DashboardListViewActivity extends ListActivity {
    DataSource dataSource;
    List<ExpenseEntry> expenseEntryList;
    private static final int EXPENSE_ENTRY_DETAIL_ACTIVITY = 1001;
    String result;

    //SET UP BUTTON
    static final String LIGHT_BLUE="#33B5E5";
    Button btnListView;
    Button btnGraphView;
    Button btnFilteredBy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_listview_activity);
        //SET UP ACTION BAR



        //SET UP BUTTON
        btnListView=(Button) findViewById(R.id.buttonGoToListView);
        btnListView.setBackgroundColor(Color.parseColor(LIGHT_BLUE));
        btnListView.setTextColor(Color.WHITE);
        btnGraphView=(Button) findViewById(R.id.buttonGoToGraphView);
        btnGraphView.setBackgroundColor(Color.WHITE);
        btnGraphView.setTextColor(Color.parseColor(LIGHT_BLUE));

        //FILTER MODULE
        btnFilteredBy = (Button) findViewById(R.id.filteredByButton);
        btnFilteredBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(DashboardListViewActivity.this, btnFilteredBy);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_filter, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Log.i("MenuItemClick", "Item Id"+item.getItemId());
                        if(item.getTitle().toString().equals("Projects")){
                            filteredByProjects();
                        }else{
                            filteredByCategories();
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method

    //instantiate DataSource
        dataSource=new DataSource(this);
        dataSource.open();
        expenseEntryList=dataSource.findAll();

        if (expenseEntryList.size()==0){
            expenseEntryList=dataSource.findAll();  /// NECESSARY??
        }
        refreshDisplay();
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard_activites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menu_add:
                Intent intent3 = new Intent(this, AddScreenActivity.class);
                startActivityForResult(intent3, 3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void onBackPressed() {
        System.runFinalizersOnExit(true);
            *//*
             * Force the system to close the app down completely instead of
             * retaining it in the background. The virtual machine that runs the
             * app will be killed. The app will be completely created as a new
             * app in a new virtual machine running in a new process if the user
             * starts the app again.
             *//*
        System.exit(0);
    }*/


    public void refreshDisplay(){
        ListView dataList=(ListView)findViewById(android.R.id.list);
        ArrayAdapter<ExpenseEntry> adapter=new ArrayAdapter<ExpenseEntry>(this, android.R.layout.simple_list_item_1,expenseEntryList);
        dataList.setAdapter(adapter);
    }

    public void GoToListView(View v){
    }

    public void GoToGraphView(View v){
        Intent intent = new Intent(this, DashboardGraphActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void filteredByCategories(){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivityForResult(intent, 2);
    }

    public void filteredByProjects(){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                result=data.getStringExtra("result");
                expenseEntryList=dataSource.findFiltered("project = \""+result+"\"" ,"amount ASC");
                refreshDisplay();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }
        if (requestCode ==2){
            if (resultCode==RESULT_OK){
               result=data.getStringExtra("result");
                expenseEntryList=dataSource.findFiltered("category = \""+result+"\"" ,"amount ASC");
                refreshDisplay();
            }
            if (resultCode == RESULT_CANCELED){
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }

        //Display what has been select as a filter on the Filter button
        if(result==null){
            btnFilteredBy.setText("Select filter");
        }else {
            btnFilteredBy.setText("Filtered by " + result);
        }

        if (requestCode==3){
            if (resultCode==RESULT_OK){
                Log.d("MS", "Inside finishing request_code=3...");
                this.finish();
            }
            if (resultCode == RESULT_CANCELED){
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED  request_code=3...");
            }

        }
        if (requestCode==EXPENSE_ENTRY_DETAIL_ACTIVITY){
            if (resultCode==RESULT_OK){
                Log.d("MS", "Inside finishing rquest_code=EXPENSE_ENTRY...");
                this.finish();
            }
            if (resultCode == RESULT_CANCELED){
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED  EXPENSE_ENTRY_DETAIL...");
            }

        }
    }//onActivityResult

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ExpenseEntry expenseEntry=expenseEntryList.get(position);
        Intent intent = new Intent(this, ExpenseEntryDetailActivity.class);
        intent.putExtra(".model.ExpenseEntry",expenseEntry);
        startActivityForResult(intent, EXPENSE_ENTRY_DETAIL_ACTIVITY);
    }
}