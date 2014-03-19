package pl.byd.wsg.promand.project5.dashboards;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.add.AddScreenActivity;
import pl.byd.wsg.promand.project5.database.DataSource;
import pl.byd.wsg.promand.project5.model.ExpenseEntry;

public class ExpenseEntryDetailActivity extends FragmentActivity {

    ExpenseEntry expenseEntry;
    DataSource datasource;
    TextView entryDetail;
    Bitmap photo;
    ImageView previewImageThumbnail;
    byte[] byteArray;
    Bundle b=null; //The bundle b object to hold the bundle got back from the intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_entry_detail);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        b=getIntent().getExtras();
        expenseEntry=b.getParcelable(".model.ExpenseEntry");
        refreshDisplay();

        datasource = new DataSource(this);
    }

    public void refreshDisplay(){
        entryDetail = (TextView) findViewById(R.id.entryDetail);
        entryDetail.setText(expenseEntry.toString());

        //PHOTO MODULE
        byteArray = expenseEntry.getPhoto();
        if(byteArray!=null){
            previewImageThumbnail= (ImageView) findViewById(R.id.imageView2);
            Bitmap photo = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            previewImageThumbnail.setImageBitmap(photo);
        }
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
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent0 = new Intent(this, DashboardListViewActivity.class);
                intent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent0);
                return true;
            case R.id.menu_delete:
                if (datasource.removeEntry(expenseEntry)) {
                    setResult(-1);
                    Intent intent1 = new Intent(this, DashboardListViewActivity.class);
                    startActivityForResult(intent1, 1);
                }
                break;
            case R.id.menu_modify:
                String project = expenseEntry.getProject();
                String category = expenseEntry.getCategory();
                String amount = expenseEntry.getAmount();
                String date = expenseEntry.getDate();
                String comment = expenseEntry.getComment();
                byte[] byteArray2=byteArray;

                Intent intent = new Intent();
                intent.setClass(this,AddScreenActivity.class);
                intent.putExtra("Uniqid", "from_modify");
                Log.d("MS", "Value Uniqid=" + intent.getStringExtra("Uniqid"));
                intent.putExtra("project", project);
                intent.putExtra("category",category);
                intent.putExtra("amount",amount);
                intent.putExtra("date",date);
                intent.putExtra("comment",comment);
                intent.putExtra("photo",byteArray2);

                startActivity(intent);

                if (datasource.removeEntry(expenseEntry)) {
                    setResult(-1);
                    this.finish();
                }
            case R.id.menu_close:
                System.exit(0);
                break;
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MS", "Inside onActivityResult");
        if (requestCode == 1) {
            Log.d("MS", "Inside requestCode");
            if(resultCode == RESULT_OK){
                Log.d("MS", "Inside OK");
                this.finish();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }

    }//onActivityResult

}
