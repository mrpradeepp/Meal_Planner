package com.example.lenovo.sample_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Contact_home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   public EditText name,phone,url,email;
    String cname,cnumber,curl,namestring,numberstring,urlstring,selCategory;
    Bundle savedInstanceState;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Spinner category_selection;
    Databasehelper myDb;
    // Data for the spinner class
    private String[] category={"Family","Friends","Stores","Hospital","Pharmacy","Work Place","Emergency"};
    //Information for validating correct email
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_MSG = "invalid email";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        myDb = new Databasehelper(this);
       category_selection=(Spinner)findViewById(R.id.categoryspinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, category);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_selection.setAdapter(adapter_state);
        category_selection.setOnItemSelectedListener(this);

         pref = getApplicationContext().getSharedPreferences("MyPref", MODE_APPEND);
        editor = pref.edit();
        name=(EditText)findViewById(R.id.nameText);
        phone=(EditText)findViewById(R.id.PhoneText);
        url=(EditText)findViewById(R.id.website);
        email=(EditText)findViewById(R.id.emailtext);

        // Retrieve the Intent Values
        cname=getIntent().getStringExtra("Contact Name");
        cnumber=getIntent().getStringExtra("Contact Number");
        curl=getIntent().getStringExtra("URL");

        // retrieve Shared preference values
        String namestring = pref.getString("Myname",null);
        String numberstring = pref.getString("Mynumber",null);
        String urlstring=pref.getString("Myurl",null);
        String emailstring=pref.getString("Myemail",null);

        // Check whether any intent values or shared pref values are passed to this activity,from contact sample class(class to search the phone contacts
        if(cname!=null)
        {
            name.setText(cname);

        }
        else if(namestring!=null)
        {
            name.setText(namestring);
        }
        else
        {
            name.setText(" ");
        }

        if(cnumber!=null)
        {
            phone.setText(cnumber);

        }
        else if(numberstring!=null)
        {
            phone.setText(numberstring);
        }
        else
        {
            phone.setText(" ");
        }

        if(curl!=null)
        {
            url.setText(curl);

        }
        else if(urlstring!=null)
        {
            url.setText(urlstring);

        }
        else
        {
            url.setText(" ");
        }

        if(emailstring!=null)
        {
            email.setText(emailstring);
        }
        else
        {
            email.setText(" ");
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //  .setAction("Action", null).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
    }

    // code to retrieve the spinner value selected
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        category_selection.setSelection(position);
        String selState = (String) category_selection.getSelectedItem();
        selCategory = selState;
        //selVersion.setText("Selected Android OS:" + selState);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // search the phone contacts using Contact_sample class
    public void go_search(View view) {
        Intent i = new Intent(this,Contact_sample.class);
        startActivity(i);
    }



    //save the vlues in the field to Shared prefs
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Activity state is ", "onStop");
        editor.putString("Myname",name.getText().toString());
        editor.putString("Mynumber",phone.getText().toString());
        editor.putString("Myurl", url.getText().toString());
        editor.putString("Myemail",email.getText().toString());
        editor.commit();
    }



    public void search_url(View view) {
        Intent i = new Intent(this,web_search.class);
        startActivity(i);

    }


    //Trying to delete the shared preference values when quitting the app
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Status","on destroy");
        SharedPreferences myPrefs = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        myPrefs.edit().remove("MyPref");
        myPrefs.edit().clear();
        myPrefs.edit().commit();

    }

    // Code to add the contacts to databse

    public void contacts_add(View view) {
      //  boolean flag = false;
        boolean flag;
       boolean isvalid= validateui();
      String contactcategory= selCategory;
        String contact_name=name.getText().toString();
        String contact_phone = phone.getText().toString();
        String contact_url = url.getText().toString();
        String contact_email = email.getText().toString();
        if (isvalid == true)
                 {


        flag=myDb.insert_contact(contactcategory,contact_name,contact_phone,contact_url,contact_email);
        if(flag==true){
            Toast.makeText(this,"Contact Added Successfully",Toast.LENGTH_LONG).show();

        }
        else
        {

            Toast.makeText(this,"ERROR adding Contact",Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Please fill all fields correctly",Toast.LENGTH_LONG).show();
        }

                    // Toast.makeText(this,"Please fill all fields correctly",Toast.LENGTH_LONG).show();
                    }

    }

    //function to validate the UI fields and restrict adding info into databse if some errors
    public boolean validateui()
    {
       /* boolean flag = false;
        if(name.getText().toString().length()<1)
        {
            name.setError("Please Enter a Valid Name");
            flag = true;
        }
        if(phone.getText().toString().length()<1)
        {
            phone.setError("Please Enter a valid Phone Number");
            flag=true;
        }
        /*String emailtext=email.getText().toString();
        if(!Pattern.matches(EMAIL_REGEX,emailtext))
        {
            email.setError("Please Enter Email in valid email format");
            flag=true;
        } */
return true;

    }


}
