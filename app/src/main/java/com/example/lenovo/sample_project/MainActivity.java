package com.example.lenovo.sample_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start_daylist(View view) {
        Intent i = new Intent(this,Meal_home.class);
        startActivity(i);
    }

    public void test(View view) {
       /* Button b = (Button)findViewById(R.id.btn_test);
        String btn_text = b.getText().toString();
        switch(btn_text)
        {
            case "test1":
                Toast.makeText(this,"Test1 clicked",Toast.LENGTH_SHORT).show();
                break;

            case "test2":
                Toast.makeText(this,"Test2 clicked",Toast.LENGTH_SHORT).show();
                break;


        }*/
        Intent i = new Intent(this,view_plan.class);
        startActivity(i);
    }

    public void contact_test(View view) {
        Intent i = new Intent(this,Contact_page.class);
        startActivity(i);

    }
}
