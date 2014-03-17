package pl.byd.wsg.promand.project5.dashboards;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.model.ExpenseEntry;

public class ExpenseEntryDetailActivity extends Activity {

    ExpenseEntry expenseEntry;
    DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_entry_detail);

        Bundle b=getIntent().getExtras();
        expenseEntry=b.getParcelable(".model.ExpenseEntry");
        refreshDisplay();

        datasource = new DataSource(this);
    }

    public void refreshDisplay(){
        TextView entryDetail = (TextView) findViewById(R.id.entryDetail);
        entryDetail.setText(expenseEntry.toString());

        /*ImageView iv = (ImageView) findViewById(R.id.imageView);
        int imageResource = getResources().getIdentifier(
                expenseEntry.getPhoto(), "drawable", getPackageName());
        if (imageResource != 0) {
            iv.setImageResource(imageResource);
        }*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_entry_detail, menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
        datasource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }
}
