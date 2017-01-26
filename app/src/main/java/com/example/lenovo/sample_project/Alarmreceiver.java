package com.example.lenovo.sample_project;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by lenovo on 7/16/2016.
 * This class helps in providing notification service as
 */
public class Alarmreceiver extends BroadcastReceiver {

Databasehelper myDb;
    String message,remindermessage;
    @Override
    public void onReceive(Context context, Intent intent) {
myDb = new Databasehelper(context);
        Calendar calendar = new GregorianCalendar();
        int day_n=calendar.get(Calendar.DAY_OF_YEAR);
     // int  currentday = intent.getIntExtra("current_day", 0);


       Intent mainintent = new Intent(context, display_reminder.class);
       PendingIntent pmainintent = PendingIntent.getActivity(context, 0, mainintent, 0);
        NotificationManager nmanager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
       // Log.i("Hello", "Alarm Called for " + c_day);
       Cursor res = myDb.getreminder(day_n+1);
        if(res.getCount() > 0)
        {


            while(res.moveToNext()) {

                message =res.getString(3);
            }
        }


        Notification.Builder mynotification = new Notification.Builder(context)
                .setTicker("ALARM!!")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Your Meal Plan reminder for tommorow")
                .setContentText("Meal plan is   " + message)
                .setContentIntent(pmainintent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mynotification.setSound(alarmSound);
        nmanager.notify(12, mynotification.build());

    }
}
