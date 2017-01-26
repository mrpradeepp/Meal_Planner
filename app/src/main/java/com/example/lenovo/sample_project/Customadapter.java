package com.example.lenovo.sample_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by lenovo on 8/9/2016.
 */
public class Customadapter extends BaseAdapter {
    Context context;
    String nameList[];
    String numberList[];
    int flags[];
    LayoutInflater inflter;

    public Customadapter(Context applicationContext, String[] nameList,String[] numberList) {
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

      view = inflter.inflate(R.layout.phonelist_row, null);
        final TextView name = (TextView) view.findViewById(R.id.contact_name);
        //ImageView icon = (ImageView) view.findViewById(R.id.icon);
        name.setText(nameList[i]);
        //country.setText(result[i].toString());
        final TextView number = (TextView)view.findViewById(R.id.contact_number);
        number.setText(numberList[i]);

        final Button countrybutton=(Button)view.findViewById(R.id.addbutton);

        countrybutton.setText("ADD" );
                countrybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(v.getContext(), numberList[i].toString(), Toast.LENGTH_SHORT).show();

                Intent j = new Intent(v.getContext(),Contact_home.class);
               j.putExtra("Contact Name", nameList[i]);
                j.putExtra("Contact Number", numberList[i]);
                j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              v.getContext().startActivity(j);
            }
        });


        return view;
    }
}



