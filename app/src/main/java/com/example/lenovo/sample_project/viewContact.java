package com.example.lenovo.sample_project;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class viewContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
ListView contact_list;
    Databasehelper myDb;
    Spinner category_selector;
    String category;
    private String[] categorylist={"Family","Friends","Stores","Hospital","Pharmacy","Work Place","Emergency"};
    ContactAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        contact_list=(ListView)findViewById(R.id.contact);
        category_selector =(Spinner)findViewById(R.id.contactcategory);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorylist);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_selector.setAdapter(adapter_state);
        category_selector.setOnItemSelectedListener(this);
        myDb = new Databasehelper(this);

       /* Cursor crs=myDb.getcontactlist(category);
       String[] categoryarray= new String[crs.getCount()];
        String[] namearray= new String[crs.getCount()];
        String[] numberarray= new String[crs.getCount()];
        String[] urlarray=new String[crs.getCount()];
        String[] emailarray=new String[crs.getCount()];

        int i=0;

        while(crs.moveToNext())
        {
         String contact_category = crs.getString(1);
         String contact_name= crs.getString(2);
         String contact_number=crs.getString(3);
         String contact_url=crs.getString(4);
         String contact_email=crs.getString(5);
           categoryarray[i]=contact_category;
            namearray[i]=contact_name;
            numberarray[i]=contact_number;
            urlarray[i]=contact_url;
            emailarray[i]=contact_url;
            i++;


        }

         myadapter=new ContactAdapter(this,categoryarray,namearray,numberarray,urlarray,emailarray); */
        contact_list.setAdapter(myadapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        category_selector.setSelection(position);
        String selState = (String) category_selector.getSelectedItem();
        category = selState;
        //selVersion.setText("Selected Android OS:" + selState);


       Cursor crs=myDb.getcontactlist(category);
       String[] categoryarray= new String[crs.getCount()];
       String[] namearray= new String[crs.getCount()];
       String[] numberarray= new String[crs.getCount()];
       String[] urlarray=new String[crs.getCount()];
       String[] emailarray=new String[crs.getCount()];

       int i=0;

       while(crs.moveToNext())
       {
           String contact_category = crs.getString(1);
           String contact_name= crs.getString(2);
           String contact_number=crs.getString(3);
           String contact_url=crs.getString(4);
           String contact_email=crs.getString(5);
           categoryarray[i]=contact_category;
           namearray[i]=contact_name;
           numberarray[i]=contact_number;
           urlarray[i]=contact_url;
           emailarray[i]=contact_email;
           i++;


       }
       myadapter=new ContactAdapter(this,categoryarray,namearray,numberarray,urlarray,emailarray);
       contact_list.setAdapter(myadapter);
       myadapter.notifyDataSetChanged();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
