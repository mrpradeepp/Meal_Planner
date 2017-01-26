package com.example.lenovo.sample_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Phonelist extends AppCompatActivity {
EditText pname,phone_num,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonelist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pname=(EditText)findViewById(R.id.phone_nameText);
        phone_num=(EditText)findViewById(R.id.Phonenum_Text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void go_search(View view) {
        Intent j = new Intent(this,Contact_home.class);
        String name=pname.getText().toString();
        String number=phone_num.getText().toString();
        j.putExtra("Contact Name",name);
        j.putExtra("Contact Number",number);
        startActivity(j);

    }
}
