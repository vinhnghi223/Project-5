package pl.byd.wsg.promand.project5.dashboards;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.graphs.Bar;
import pl.byd.wsg.promand.project5.graphs.BarGraph;
import pl.byd.wsg.promand.project5.graphs.PieGraph;
import pl.byd.wsg.promand.project5.graphs.PieSlice;
import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Miguel on 14-03-2014.
 */

public class DashboardGraphActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener {
    EditText inputAmountEditText,commentEditText;
    Button selectDateButton;
    public static TextView projectTextView, categoryTextView;
    DataSource dataSource;

    //SET UP BUTTON
    static final String LIGHT_BLUE="#33B5E5";
    Button btnListView;
    Button btnGraphView;
    Button btnFilteredBy;

    final Calendar calendar = Calendar.getInstance();
    final com.fourmob.datetimepicker.date.DatePickerDialog from_datePickerDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    public static final String FROM_DATEPICKER_TAG = "from_datepicker";

    //TODO Calculation for DB

    float c1_total_amount = (float) 30.12;
    float c2_total_amount = (float) 20.15;
    float c3_total_amount = (float) 30.43;
    float c4_total_amount = (float) 40.04;
    float c5_total_amount = (float) 50.99;

    boolean isCategory = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_graphview);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        //SET BUTTON COLOR
        btnListView=(Button) findViewById(R.id.buttonGoToListView);
        btnListView.setBackgroundColor(Color.WHITE);
        btnListView.setTextColor(Color.parseColor(LIGHT_BLUE));
        btnGraphView=(Button) findViewById(R.id.buttonGoToGraphView);
        btnGraphView.setBackgroundColor(Color.parseColor(LIGHT_BLUE));
        btnGraphView.setTextColor(Color.WHITE);

        //FILTER BY
        btnFilteredBy = (Button) findViewById(R.id.filteredByButton);
        btnFilteredBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(DashboardGraphActivity.this, btnFilteredBy);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle()=="Projects"){
                            goProjects();
                        }else{
                            goCategories();
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method

        dataSource=new DataSource(this);
        dataSource.open();

        Float meal_total = dataSource.sumAllMeal();
        Float transport_total = dataSource.sumAllTransport();
        Float comcar_total = dataSource.sumAllCompanyCar();
        Float office_total = dataSource.sumAllOfficeMaterials();
        Float repexp_total = dataSource.sumAllRepresentationExpenses();

        //Create Bar chart for categories by default
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Meals");
        d.setValue(meal_total);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Transport");
        d2.setValue(transport_total);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Company Car");
        d3.setValue(comcar_total);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Office Material");
        d4.setValue(office_total);

        Bar d5 = new Bar();
        d5.setColor(Color.parseColor("#FF4444"));
        d5.setName("Representation Expenses");
        d5.setValue(repexp_total);

        points.add(d);
        points.add(d2);
        points.add(d3);
        points.add(d4);
        points.add(d5);

        BarGraph g = (BarGraph)findViewById(R.id.pieGraph);
        g.setBars(points);

        int parseMonth = calendar.get(Calendar.MONTH) + 1;

        String dynamicCalendarText = calendar.get(Calendar.DAY_OF_MONTH) + " / " + parseMonth + " / " + calendar.get(Calendar.YEAR);

        Button nButton = (Button) findViewById(R.id.graphview_selectDateFromButton);
        nButton.setText(dynamicCalendarText);

        findViewById(R.id.graphview_selectDateFromButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_datePickerDialog.setYearRange(1999, 2028);
                from_datePickerDialog.show(getSupportFragmentManager(), FROM_DATEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            com.fourmob.datetimepicker.date.DatePickerDialog dpd = (com.fourmob.datetimepicker.date.DatePickerDialog) getSupportFragmentManager().findFragmentByTag(FROM_DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener((com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener) this);
            }
        }

    }
    public void GoToListView(View v){
        Intent intent = new Intent(this, DashboardListViewActivity.class);
        //this.finish();
        startActivity(intent);
    }
    public void GoToGraphView(View v){
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
        goCategories();
    }
    public void goCategories(){
        //Intent intent = new Intent(this, CategoriesActivity.class);
        //startActivity(intent);

        //Change Bar chart to Category view
        //TODO some logic with DB
        Float meal_total = dataSource.sumAllMeal();
        Float transport_total = dataSource.sumAllTransport();
        Float comcar_total = dataSource.sumAllCompanyCar();
        Float office_total = dataSource.sumAllOfficeMaterials();
        Float repexp_total = dataSource.sumAllRepresentationExpenses();

        //Create Bar chart for categories by default
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Meals");
        d.setValue(meal_total);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Transport");
        d2.setValue(transport_total);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Company Car");
        d3.setValue(comcar_total);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Office Material");
        d4.setValue(office_total);

        Bar d5 = new Bar();
        d5.setColor(Color.parseColor("#FF4444"));
        d5.setName("Representation Expenses");
        d5.setValue(repexp_total);

        points.add(d);
        points.add(d2);
        points.add(d3);
        points.add(d4);
        points.add(d5);

        BarGraph g = (BarGraph)findViewById(R.id.pieGraph);
        g.setBars(points);


    }

    public void goProjects(View v){
        goProjects();
    }
    public void goProjects(){
        //Intent intent = new Intent(this, DashboardGraphActivity.class);
        //startActivity(intent);

        //Change Bar chart to Project view
        //TODO some logic with DB

        Float project1_total = dataSource.sumAllProject("Project 1");
        Float project2_total = dataSource.sumAllProject("Project 2");
        Float project3_total = dataSource.sumAllProject("Project 3");
        Float project4_total = dataSource.sumAllProject("Project 4");

        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Project 1");
        d.setValue(project1_total);

        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Project 2");
        d2.setValue(project2_total);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Project 3");
        d3.setValue(project3_total);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Project 4");
        d4.setValue(project4_total);

        points.add(d);
        points.add(d2);
        points.add(d3);
        points.add(d4);

        BarGraph g = (BarGraph)findViewById(R.id.pieGraph);
        g.setBars(points);
    }

    @Override
    public void onDateSet( com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int year, int month, int day) {
        Button nButton = (Button) findViewById(R.id.graphview_selectDateFromButton);
        month += 1;
        nButton.setText(day + " / " + month + " / " + year);
    }

}