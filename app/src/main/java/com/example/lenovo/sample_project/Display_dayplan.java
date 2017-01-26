package com.example.lenovo.sample_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class Display_dayplan extends AppCompatActivity {
TextView heading,breakfast,lunch,snacks,dinner,reminder;
    Databasehelper myDb;
    String key;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dayplan);
        myDb= new Databasehelper(this);
        heading=(TextView)findViewById(R.id.heading_txt);
        breakfast=(TextView)findViewById(R.id.bfast_txt);
        lunch=(TextView)findViewById(R.id.lunch_txt);
        snacks=(TextView)findViewById(R.id.snacks_txt);
        dinner=(TextView)findViewById(R.id.dinner_txt);
        reminder=(TextView)findViewById(R.id.Reminder_txt);
        Intent i = getIntent();
       key= i.getStringExtra("Date");
        heading.setText(key);
        myDb.getdateplan(key);
        toolbar = (Toolbar)findViewById(R.id.app_bar_daylist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Cursor res= myDb.getdateplan(key);
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

}
