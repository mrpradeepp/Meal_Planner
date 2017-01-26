package com.example.lenovo.sample_project;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class Contact_sample extends AppCompatActivity {
    ListView simpleList;
    Databasehelper myDb;
    String namelist= " ";
    String Phonelist = "";
    String names[];
    String phone_number[];

    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
      myDb= new Databasehelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        simpleList = (ListView) findViewById(R.id.simpleListView);
       /* Cursor crs = myDb.getalldata();
        String[] sample_array = new String[crs.getCount()];
        int i = 0;

        while(crs.moveToNext()){
            String uname = crs.getString(2);
            sample_array[i] = uname;
            i++;
        }
        */

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {

            // REad contact name & number
            String contactname = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phonemumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (contactname != null) {
                namelist += contactname + ",";
                Phonelist += phonemumber + ",";
            }
            // phones.close();

        }
        // convert csv into array

        names =  namelist.split(",");
        phone_number = Phonelist.split(",");

        Customadapter customAdapter = new Customadapter(this, names,phone_number);
        simpleList.setAdapter(customAdapter);
    }


}
