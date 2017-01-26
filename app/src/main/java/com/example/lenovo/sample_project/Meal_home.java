package com.example.lenovo.sample_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class Meal_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_daylist);
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


    public void to_plan(View view) {
        Intent i = new Intent(this,Daylist.class);
        startActivity(i);

    }

    public void view_plan(View view) {
        Intent i = new Intent(this,view_plan.class);
        startActivity(i);
    }
}
