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

    final Calendar calendar = Calendar.getInstance();
    final com.fourmob.datetimepicker.date.DatePickerDialog from_datePickerDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    public static final String FROM_DATEPICKER_TAG = "from_datepicker";

    //TODO Calculation for DB

    float c1_total_amount = (float) 10.12;
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

        //Create Bar chart for categories by default
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Meals");
        d.setValue(c1_total_amount);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Transport");
        d2.setValue(c2_total_amount);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Company Car");
        d3.setValue(c3_total_amount);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Office Material");
        d4.setValue(c4_total_amount);

        Bar d5 = new Bar();
        d5.setColor(Color.parseColor("#FF4444"));
        d5.setName("Representation Expenses");
        d5.setValue(c5_total_amount);

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
        //Intent intent = new Intent(this, CategoriesActivity.class);
        //startActivity(intent);

        //Change Bar chart to Category view
        //TODO some logic with DB

        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Meals");
        d.setValue(c1_total_amount);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Transport");
        d2.setValue(c2_total_amount);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Company Car");
        d3.setValue(c3_total_amount);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Office Material");
        d4.setValue(c4_total_amount);

        Bar d5 = new Bar();
        d5.setColor(Color.parseColor("#FF4444"));
        d5.setName("Representation Expenses");
        d5.setValue(c5_total_amount);

        points.add(d);
        points.add(d2);
        points.add(d3);
        points.add(d4);
        points.add(d5);

        BarGraph g = (BarGraph)findViewById(R.id.pieGraph);
        g.setBars(points);


    }

    public void goProjects(View v){
        //Intent intent = new Intent(this, DashboardGraphActivity.class);
        //startActivity(intent);

        //Change Bar chart to Project view
        //TODO some logic with DB
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#33B5E5"));
        d.setName("Project 1");
        d.setValue(c1_total_amount);

        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#AA66CC"));
        d2.setName("Project 2");
        d2.setValue(c2_total_amount);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("Project 3");
        d3.setValue(c3_total_amount);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("Project 4");
        d4.setValue(c4_total_amount);

        points.add(d);
        points.add(d2);
        points.add(d3);
        points.add(d4);

        BarGraph g = (BarGraph)findViewById(R.id.pieGraph);
        g.setBars(points);
    }

    public void goReportList(View v){
        Intent intent = new Intent(this, DashboardListViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet( com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int year, int month, int day) {
        Button nButton = (Button) findViewById(R.id.graphview_selectDateFromButton);
        month += 1;
        nButton.setText(day + " / " + month + " / " + year);
    }

}