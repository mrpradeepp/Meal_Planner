package com.example.lenovo.sample_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 9/5/2016.
 */
public class ContactAdapter extends BaseAdapter {

   Context context;
    String categoryarray[];
    String namearray[];
    String numberarray[];
    String urlarray[];
    String emailarray[];
    LayoutInflater layoutinflater;

    public ContactAdapter(Context applicationcontext,String[] categoryarray,String[] namearray,String[] numberarray,String[] urlarray,String[] emailarray)
    {

        this.context = context;
        this.categoryarray=categoryarray;
        this.namearray=namearray;
        this.numberarray=numberarray;
        this.urlarray=urlarray;
        this.emailarray=emailarray;
        layoutinflater=(LayoutInflater.from(applicationcontext));


    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return namearray.length;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        view=layoutinflater.inflate(R.layout.contact_view,null);
        final TextView names = (TextView)view.findViewById(R.id.contactname);
        final TextView numbers=(TextView)view.findViewById(R.id.contactnumber);
        final ImageButton phonebutton = (ImageButton)view.findViewById(R.id.phonebutton);
        final ImageButton browserbutton =(ImageButton)view.findViewById(R.id.browserbutton);
        final ImageButton emailbutton =(ImageButton) view.findViewById(R.id.emailbutton);
        names.setText(namearray[position]);
        numbers.setText(numberarray[position]);

        phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + numberarray[position]));
                v.getContext().startActivity(callIntent);


            }
        });

        emailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "subject");
                email.putExtra(Intent.EXTRA_TEXT, "message");
                email.setType("message/rfc822");
                v.getContext().startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });


        browserbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),View_url.class);
                i.putExtra("Url_data",urlarray[position]);
                v.getContext().startActivity(i);
            }
        });
        return view;
    }




}
