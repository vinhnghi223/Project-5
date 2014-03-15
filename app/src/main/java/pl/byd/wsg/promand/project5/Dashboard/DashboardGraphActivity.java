package pl.byd.wsg.promand.project5.dashboard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Miguel on 14-03-2014.
 */

public class DashboardGraphActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_graphview);
    }

    public void goCategories(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    public void goReportList(View v){
        Intent intent = new Intent(this, DashboardListView.class);
        startActivity(intent);
    }
}
