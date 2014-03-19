package pl.byd.wsg.promand.project5.add;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import pl.byd.wsg.promand.project5.QRcode.QRActivity;
import pl.byd.wsg.promand.project5.categories.CategoriesActivity;

import pl.byd.wsg.promand.project5.dashboards.DashboardListViewActivity;
import pl.byd.wsg.promand.project5.database.DataSource;

import pl.byd.wsg.promand.project5.model.ExpenseEntry;
import pl.byd.wsg.promand.project5.ocr.OCR;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;
import pl.byd.wsg.promand.project5.R;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by Sergey on 3/14/14.
 */

public class AddScreenActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
    EditText inputAmountEditText,commentEditText;
    public static TextView projectTextView, categoryTextView;
    Button selectDateButton;
    DataSource dataSource;

    //PHOTO MODULE
    ImageView addImage;
    ImageView previewImageThumbnail;
    Bitmap imageBitmap=null;
    byte imageInByte[];

    public static final String DATEPICKER_TAG = "datepicker";
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        final com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen_activity);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL

        //------------------FIND VIEW BY ID------------------
        projectTextView= (Button) findViewById(R.id.chooseProjectButton);
        categoryTextView= (Button) findViewById(R.id.chooseCategoryButton);
        inputAmountEditText= (EditText) findViewById(R.id.inputAmountEditText);
        selectDateButton=(Button) findViewById(R.id.selectDateButton);
        commentEditText= (EditText) findViewById(R.id.commentEditText);

        //----------------------PHOTO MODULE------------------------------
        previewImageThumbnail= (ImageView) findViewById(R.id.imageView);
        //open dialog for choose camera/gallery
        final String[] option = new String[] { "Take from Camera",
                "Select from Gallery" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
                    callCamera();
                }
                if (which == 1) {
                    callGallery();
                }
            }
        });
        final AlertDialog dialog = builder.create();

        addImage = (ImageView) findViewById(R.id.imageView);

        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
            }
        });

        //------------------INSTANTIATE DATASOURCE------------------
        dataSource=new DataSource(this);
        dataSource.open();

        //------------------DATE PICKER MODULE---------------------
        int parseMonth = calendar.get(Calendar.MONTH) + 1;

        String dynamicCalendarText = calendar.get(Calendar.YEAR) + "-" + parseMonth + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        Button nButton = (Button) findViewById(R.id.selectDateButton);
        if(parseMonth < 10){
            dynamicCalendarText = calendar.get(Calendar.YEAR) + "-" +  "0" + parseMonth + "-" + calendar.get(Calendar.DAY_OF_MONTH);

            if(calendar.get(Calendar.DAY_OF_MONTH) < 10){
                dynamicCalendarText = + calendar.get(Calendar.YEAR) + "-" + "0" + parseMonth + "-" + "0" + calendar.get(Calendar.DAY_OF_MONTH);
            }
        }
        nButton.setText(dynamicCalendarText);

        findViewById(R.id.selectDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setYearRange(1999, 2028);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            com.fourmob.datetimepicker.date.DatePickerDialog dpd = (com.fourmob.datetimepicker.date.DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener((com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener) this);
            }
        }
        //------------------MODIFY FUNCTIONALITY------------------
        //obtain  Intent Object send  from Modify (ExpenseEntryDetailActivity)
        Intent intent = this.getIntent();
        Log.d("MS", "Before checking from which intent it came");
        /* Obtain String from Intent  */
        if(intent !=null) {
            Log.d("MS", "Inside intent!=null");
            String strdata = intent.getStringExtra("Uniqid");
            Log.d("MS", "strdata=" + strdata);
            if (strdata != null) {
                if (strdata.equals("from_modify")) {
                    Log.d("MS", "inside strdata.equals(from_modify)");
                    String strProj = intent.getStringExtra("project");
                    String strCat = intent.getStringExtra("category");
                    String strAmount = intent.getStringExtra("amount");
                    String strDat = intent.getStringExtra("date");
                    String strComm = intent.getStringExtra("comment");
                    byte[] byteArray = intent.getByteArrayExtra("photo");

                    projectTextView.setText(strProj);
                    categoryTextView.setText(strCat);
                    inputAmountEditText.setText(strAmount);
                    selectDateButton.setText(strDat);
                    commentEditText.setText(strComm);
                    if(byteArray!=null){
                        previewImageThumbnail= (ImageView) findViewById(R.id.imageView);
                        Bitmap photo = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        previewImageThumbnail.setImageBitmap(photo);
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_screen_activity, menu);
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
                Intent intent = new Intent(this, DashboardListViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.menu_close:
                System.exit(0);
                break;
            case R.id.menu_qr:
                Intent ocrIntent = new Intent(this, QRActivity.class);
                startActivity(ocrIntent);
                break;
            case R.id.menu_ocr:
                Intent qrIntent = new Intent(this, OCR.class);
                startActivity(qrIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openCategoryChooser(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivityForResult(intent, 3);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivityForResult(intent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MS", "Inside onActivityResult");
        if (requestCode == 4) {
            Log.d("MS", "Inside requestCode");
            if(resultCode == RESULT_OK){
                Log.d("MS", "Inside OK");
                String result=data.getStringExtra("result");
                Log.d("MS", "Result="+result);
                projectTextView.setText(result);
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }
        if (requestCode == 3) {
            Log.d("MS", "Inside requestCode 3");
            if (resultCode == RESULT_OK) {
                Log.d("MS", "Inside OK");
                String result = data.getStringExtra("result");
                Log.d("MS", "Result=" + result);
                categoryTextView.setText(result);
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("MS", "It's in   resultCode == RESULT_CANCELED");
            }
        }

        //---------------------PHOTO MODULE---------------------------------
        //if (resultCode != RESULT_OK) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                setImage(extras);
                createImageInByte(extras);
                //return;
            }
        }
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                setImage(extras2);
                createImageInByte(extras2);
            }
        }
    }//onActivityResult

    public void setImage(Bundle extras){
        imageBitmap = (Bitmap) extras.get("data");
        previewImageThumbnail.setImageBitmap(imageBitmap);
    }

    public void createImageInByte(Bundle extras){
        //yourImage = extras.getParcelable("data");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);*/
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageInByte = stream.toByteArray();
        //db.addContact(new Contact("Android", imageInByte));
    }

    public void callCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 400);
        cameraIntent.putExtra("outputY", 300);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);
    }

    public void submitExpense(View v){

        String project=projectTextView.getText().toString();
        String category=categoryTextView.getText().toString();
        String amount= inputAmountEditText.getText().toString();
        String date=selectDateButton.getText().toString();
        String comment=commentEditText.getText().toString();

        if(project.equals("Choose Project")) {
            Toast.makeText(getApplicationContext(), "Please, choose a project",
                    Toast.LENGTH_LONG).show();
        } else if(category.equals("Choose Category")) {
            Toast.makeText(getApplicationContext(), "Please, choose a category",
                    Toast.LENGTH_LONG).show();
        } else if(amount.equals("")){
            Toast.makeText(getApplicationContext(), "Expense can't be empty",
                    Toast.LENGTH_LONG).show();
        } else {
            //get raw string data input
            //setup the expense entry object
            ExpenseEntry expenseEntry=new ExpenseEntry();
            expenseEntry.setProject(project);
            expenseEntry.setCategory(category);
            expenseEntry.setAmount(amount);
            expenseEntry.setDate(date);
            expenseEntry.setComment(comment);
            expenseEntry.setPhoto(imageInByte);
            dataSource.create(expenseEntry);
            Toast.makeText(getApplicationContext(), "Entry saved successfully",
                    Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, DashboardListViewActivity.class));
    }
}

    /* Maintain a persistent database connection for the entire lifetime of the activity
    The connection object within any activity is cached. So you don't have to worry about calling
    the open method too many times, but you should make sure that you're explicitly closing the connection
    whenever the activity is going away. That will eliminate the possibility of what are known as database
    connection leaks. A database connection leak can cause memory and performance issues. */
    @Override
    protected void onResume() {
        super.onResume();
        //As the activity comes to the screen, it's onResume method is called. Thats when dataSource is opened
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    @Override
    public void onDateSet( com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int year, int month, int day) {
        Button nButton = (Button) findViewById(R.id.selectDateButton);
        month += 1;
        nButton.setText(year + "-" + month + "-" + day);
        if(month < 10){
            nButton.setText(year + "-" + "0" + month + "-" + day);

            if(day < 10){
                nButton.setText(year + "-" + "0" + month + "-" + "0" + day);
            }
        }
    }
}