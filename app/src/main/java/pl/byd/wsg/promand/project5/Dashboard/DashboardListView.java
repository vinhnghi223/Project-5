package pl.byd.wsg.promand.project5.Dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.byd.wsg.promand.project5.Categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.Dashboard.DashboardGraphActivity;
import pl.byd.wsg.promand.project5.Project.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Miguel on 14-03-2014.
 */
public class DashboardListView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_listview);
    }

    public void goCategories(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    public void goDashboard(View v){
        Intent intent = new Intent(this, DashboardGraphActivity.class);
        startActivity(intent);
    }
}
