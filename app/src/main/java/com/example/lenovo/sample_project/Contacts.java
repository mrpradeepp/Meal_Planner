package com.example.lenovo.sample_project;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Contacts extends ActionBarActivity {
Button mybutton,spbutton;
    String status,value;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView fontstyle;
    String key;
    EditText mystring;
    ListView simpleList;
    String namelist= " ";
    String Phonelist = "";
    String names[];
    String phone_number[];
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    Customadapter customAdapter;
   // public static final String s="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
      //  simpleList = (ListView) findViewById(R.id.Mylist);
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
        customAdapter = new Customadapter(this, names,phone_number);
        simpleList.setAdapter(customAdapter);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void use_sp(View view) {

        String btn_txt = spbutton.getText().toString();
        key = pref.getString("State", null);
        spbutton.setText(key);

        switch(btn_txt)
        {

            case "SET":

             editor.putString("State", "NOT SET");
               // editor.apply();
                editor.commit();
                spbutton.setText("NOT SET");
                break;
            case "NOT SET":
                editor.putString("State", "SET");
                //editor.apply();
                editor.commit();
                spbutton.setText("SET");
                break;



    }}

    public void compare_string(View view) {

        value=mystring.getText().toString();
        // String s ="null";
        if(value.equals("null"))
        {
            Toast.makeText(this,"Value is null",Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(this,"Not null",Toast.LENGTH_LONG).show();
        }
    }



}
