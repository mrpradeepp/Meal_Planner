package com.example.lenovo.sample_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class display_reminder extends AppCompatActivity {
String message,remindermessage;
    Databasehelper myDb;
    Button actionbutton;
    int length=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new Databasehelper(this);
        Calendar calendar = new GregorianCalendar();
        int day_n=calendar.get(Calendar.DAY_OF_YEAR);
        actionbutton=(Button)findViewById(R.id.plan_view);


        /*if(message.equals("null"))
        {
            remindermessage="You dont have any reminder set";

        }

        else
        {
            remindermessage=message;
        }*/
        Cursor res = myDb.getreminder(day_n + 1);
        if(res.getCount() > 0)
        {


            while(res.moveToNext()) {

                message =res.getString(3);
                remindermessage=message;
                actionbutton.setText("VIEW FULL DAY PLAN");
            }
        }
        else
        {
            remindermessage="Sorry you dont have any meal plan for tommorow,you can still plan it by clicking the CREATE PLAN button below !!!";
            actionbutton.setText("CREATE PLAN");

        }

        TextView reminder=(TextView)findViewById(R.id.reminder_alert);
        reminder.setText(remindermessage);


    }

    public void take_action(View view) {
        String key=actionbutton.getText().toString();
        switch(key)
        {
            case "CREATE PLAN":
                Intent i = new Intent(this,Daylist.class);
                startActivity(i);
                break;

            case "VIEW FULL DAY PLAN":
                Intent j = new Intent(this,Display_dayplan.class);
                SimpleDateFormat sdf = new SimpleDateFormat(" dd-MMM-yyyy");
                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE,1);
                String day = sdf.format(calendar.getTime());
                j.putExtra("Date",day);
                startActivity(j);
                break;

        }

    }
}
