package com.example.lenovo.sample_project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.support.v7.widget.Toolbar;

// The main intension of this activity is to provide the UI and functionality for creating Meal plan for a particular day
// Based on whether we have planned for a day or not either the already created plan will appear or we can create a new plan(ie eithe Create/Update)
public class Mealplanner extends AppCompatActivity {

    String day,date,status;
    int day_no;
    EditText breakfast,lunch,dinner,snacks,reminder;
    TextView weekday,weekdate;
    Button plan,alarm;
    Databasehelper myDb;
    FileInputStream fileIn;
    Toolbar toolbar;
    PendingIntent pendingintent;
    AlarmManager alarmManager;
    //Create objects for UI elements  and initialise the database object
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplanner);
        //fileIn=openFileInput("alarm_status.txt");
        myDb= new Databasehelper(this);
        Intent i = getIntent();
        day = i.getStringExtra("Day");
        date = i.getStringExtra("Date");
        day_no=i.getIntExtra("Daynumber", 0);
        breakfast = (EditText)findViewById(R.id.breakfasttxt);
        lunch=(EditText)findViewById(R.id.lunchtxt);
        dinner=(EditText)findViewById(R.id.dinnertext);
        snacks=(EditText)findViewById(R.id.snacktxt);


        weekday=(TextView)findViewById(R.id.day);

        weekdate=(TextView)findViewById(R.id.date);
        reminder = (EditText) findViewById(R.id.remindertext);
        plan=(Button)findViewById(R.id.btn_plan);
       // alarm=(Button) findViewById(R.id.btn_alarm);
        weekday.setText(day);
        weekdate.setText(date);

        Intent alarmintent= new Intent(this,Alarmreceiver.class);

        pendingintent=PendingIntent.getBroadcast(Mealplanner.this,0,alarmintent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

       modify_ui(date);
        //update_alarmbutton();
        toolbar = (Toolbar)findViewById(R.id.app_bar_daylist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mealplanner, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Function to create a new plan for a day
    // for a day the plan includes breakfast,lunch,snacks,dinner and a reminder and day attributes along with day of year is stored
    // in the database to make the entry unique
    // The database function from database helper class insert plan or update plan will be used based upon whether a plan already existe or not
    public void create_plan(View view) {

        boolean flag=false,flag1=false;
        String Breakfast = breakfast.getText().toString();
        String Lunch = lunch.getText().toString();
        String Snacks = snacks.getText().toString();
        String Dinner = dinner.getText().toString();
        String Reminder = reminder.getText().toString();



        //Intent alarmintent= new Intent(this,Alarmreceiver.class);
        //alarmintent.putExtra("current_day",day_no);
       //PendingIntent pendingintent=PendingIntent.getBroadcast(Mealplanner.this,0,alarmintent,PendingIntent.FLAG_UPDATE_CURRENT);
      //  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        String btn_text = plan.getText().toString();
        switch(btn_text)
        {
            case "Create":
                 flag = myDb.insert_plan(day,date,Breakfast,Lunch,Snacks,Dinner,Reminder,day_no);

                  break;

            case "Update Plan":
              flag1=myDb.updatedata(date,Breakfast,Lunch,Snacks,Dinner,Reminder);
                 break;


        }



        if(flag1==true)
        {
            Toast.makeText(getApplicationContext()," Meal Plan updated successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Meal Plan Created",Toast.LENGTH_LONG).show();
        }

    }
    // This function will provide some functions to modify the UI for planning your meal
    // 1) Check whether a meal plan is set for the date,if set then fill the fields with that values and provide option to update the entries
    public void modify_ui(String date)
    {
      int value =  myDb.searchdata(date);
        if (value==1)
        {

            plan.setText("Update Plan");

            myDb.getdateplan(date);

            Cursor res= myDb.getdateplan(date);
            if(res.getCount() > 0)
            {


                while(res.moveToNext()) {
                    breakfast.setText(res.getString(0));
                    lunch.setText(res.getString(1));
                    snacks.setText(res.getString(2));
                    dinner.setText(res.getString(3));
                    reminder.setText(res.getString(4));
                }
            }


    }




    }

    public void alarm_set(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE,30);
        calendar.set(Calendar.SECOND, 00);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingintent);

    }

    public void alarm_off(View view) {

        alarmManager.cancel(pendingintent);
    }






}

