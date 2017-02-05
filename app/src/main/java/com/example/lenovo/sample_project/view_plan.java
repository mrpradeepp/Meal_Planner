package com.example.lenovo.sample_project;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import android.support.v7.widget.Toolbar;

public class view_plan extends AppCompatActivity {

    ListView planlist;
    View view;
    Databasehelper db;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        db = new Databasehelper(this);
        planlist =(ListView)findViewById(R.id.planview);

        processadapter(view);
        toolbar = (Toolbar)findViewById(R.id.app_bar_daylist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


   public void  processadapter(View view) {

       String[] projections = {"day", "date"};
       final  String key;
       Calendar cal = Calendar.getInstance();
       int day_number=cal.get(Calendar.DAY_OF_YEAR);
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.single_row,
               db.getplan(day_number),
               projections,
               new int[]{R.id.day_name,
                       R.id.date});

       planlist = (ListView) findViewById(R.id.planview);
       planlist.setAdapter(adapter);


       planlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {

               Button showbtn = (Button) findViewById(R.id.btn_edit);

               Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
             final String link = cursor.getString(cursor.getColumnIndex("date"));
               final  String key = cursor.getString(cursor.getColumnIndex("date"));
               Intent i = new Intent(getApplicationContext(),Display_dayplan.class);
               i.putExtra("Date", link);
               startActivity(i);
              // Toast.makeText(getApplicationContext(), link, Toast.LENGTH_LONG).show();
               showbtn.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View arg0) {
                       Intent i = new Intent(getApplicationContext(),Display_dayplan.class);
                       i.putExtra("Date",link);
                     //  Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                      // String link = cursor.getString(cursor.getColumnIndex("date"));


                   }
               });





           }
       });
   }}












