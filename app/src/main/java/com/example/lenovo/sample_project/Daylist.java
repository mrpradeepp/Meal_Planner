package com.example.lenovo.sample_project;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Daylist extends AppCompatActivity {

    ListView list;
    Toolbar tbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daylist);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(new dateadapter(this));
        tbar = (Toolbar)findViewById(R.id.app_bar_daylist);
        setSupportActionBar(tbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daylist, menu);
        return true;
    }

}

// This class is declared to hold a single entry into listview
class singlerow{
    String day;
    String date;
    int day_number;
    singlerow(String day,String date,int day_number)
    {
        this.day = day;
        this.date = date;
        this.day_number=day_number;

    }


}
// This class is used to provide a custom adapter for the listview
class dateadapter extends BaseAdapter{

    // Declare mylist as an object for singlerow arraylist
    private final Context ctx;
    ArrayList<singlerow> mylist;





    dateadapter(Context c)
    {
      ctx=c;
        mylist = new ArrayList<singlerow>();

        String Weekday;
        Resources res=c.getResources();
       // array declared for days and dates with size 7
        String[] days = new String[7];
                String[] dates = new String[7];
        int[] day_no = new int[7];

        // get the next 7 days
        SimpleDateFormat sdf = new SimpleDateFormat(" dd-MMM-yyyy");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        SimpleDateFormat daynumber = new SimpleDateFormat("D");
        for (int i = 0; i < 7; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i+1);
            // Get the name of Week Day eg:Monday,Sunday
            Weekday = dayFormat.format(calendar.getTime());
            days[i]=Weekday;
            //Get the date in format dd/mm/year eg: 07-Jul-2016
              String day = sdf.format(calendar.getTime());
            dates[i]=day;
            //get the day number in an year in the format 000 eg 196,008
             int day_n=calendar.get(Calendar.DAY_OF_YEAR);
             day_no[i]=day_n;
                    }

        // Add the day names and date to the arraylist mylist

        for(int i =0;i<=6;i++)
        {

           mylist.add(new singlerow(days[i],dates[i],day_no[i]));
        }

    }
    @Override
    public int getCount() {
        return mylist.size();
    }

    // 1)in the getView function the xml layout for a single listview row is attached
    // 2) variables for the edittext to hold day and date is initialized
    // 3) Every single entry of the arraylist mylist is fetched and asigned to temp
    // 4)Day name and date can be set to the edittext based on the values of temp.day and temp.date
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =inflater.inflate(R.layout.single_row,parent,false);
        EditText day = (EditText)row.findViewById(R.id.day_name);
        EditText date = (EditText)row.findViewById(R.id.date);
        Button mybutton = (Button)row.findViewById(R.id.btn_edit);
       final  singlerow temp = mylist.get(position);
        day.setText(temp.day);
        date.setText(temp.date);



        mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               String daymsg = temp.day;
                String datemsg = temp.date;
                int dayofyear=temp.day_number;

                Intent i = new Intent(ctx.getApplicationContext(),Mealplanner.class);
                i.putExtra("Day",daymsg);
                i.putExtra("Date",datemsg);
                i.putExtra("Daynumber",dayofyear);
                ctx.startActivity(i);

                //Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();

            }
        });
        return row;
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

