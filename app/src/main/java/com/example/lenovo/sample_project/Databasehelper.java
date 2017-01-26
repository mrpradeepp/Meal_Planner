package com.example.lenovo.sample_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 7/7/2016.
 */
public class Databasehelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "personal.db";
    public static final int DATABASE_VERSION=2;
    public static final String TABLE_NAME = "mealplan_table";
    public static final String TABLE_NAME1 = "contacts_table";
    public static final String COL1 = "_id";
    public static final String COL2 = "day";
    public static final String COL3 = "date";
    public static final String COL4 = "breakfast";
    public static final String COL5 = "lunch";
    public static final String COL6 = "snacks";
    public static final String COL7 = "dinner";
    public static final String COL8 = "reminder";
    public static final String COL9 = "valid";
    public static final String Contact_COL1= "_id";
    public static final String Contact_COL2= "category";
    public static final String Contact_COL3= "name";
    public static final String Contact_COL4= "phone";
    public static final String Contact_COL5= "url";
    public static final String Contact_COL6= "email";


    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db= this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,day TEXT,date TEXT,breakfast TEXT,lunch TEXT,snacks TEXT,dinner TEXT,reminder TEXT,valid INTEGER )");

        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,category TEXT,name TEXT,phone TEXT,url TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
    }

    public boolean insert_contact(String category,String name,String phone,String url,String email)
    {
       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contact_COL2,category);
        values.put(Contact_COL3,name);
        values.put(Contact_COL4,phone);
        values.put(Contact_COL5,url);
        values.put(Contact_COL6,email);
        long status= db.insert(TABLE_NAME1,null,values);
        if(status== -1)
        {
            return false;
        }
        else
        {
        return true;
    }}
    public boolean insert_plan(String day,String date,String breakfast,String lunch,String snacks,String dinner,String reminder,int valid)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, day);
        values.put(COL3, date);
        values.put(COL4, breakfast);
        values.put(COL5, lunch);
        values.put(COL6, snacks);
        values.put(COL7, dinner);
        values.put(COL8,reminder);
        values.put(COL9,valid);

        long inserted = db.insert(TABLE_NAME, null, values);
        if (inserted == -1) {
            return false;
        } else {
            return true;
        }


    }

//function to search whether there is an plan entry for a date in the database table
    public int searchdata(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT date from " + TABLE_NAME;
        Cursor c = db.rawQuery(query,null);
        String a,b;
        int key = 0;
        if(c.moveToFirst())
        {
            do {
                a = c.getString(0);
                if (a.equals(date)) {
                 //   b = c.getString(1);
                    key=1;
                    break;
                }
            }

            while (c.moveToNext());
        }
        return key;
    }

// function to retrieve all date plans

    public Cursor getdateplan(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT breakfast,lunch,snacks,dinner,reminder  from " + TABLE_NAME + " WHERE date = " + "'" + date + "'";
        Cursor c = db.rawQuery(query, null);
        return c;

    }

    public Cursor getcontactlist(String contactcategory)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query ="SELECT * from " + TABLE_NAME1 + " where category = " + "'" + contactcategory + "'";
        Cursor c =db.rawQuery(query,null);
        return c;

    }

    public Cursor deletecontactlist()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE from " + TABLE_NAME1 + " where _id = '4'";
        Cursor c = db.rawQuery(query,null);
        return c;

    }
    public Cursor getplan(int daynumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT _id,day,date,reminder from  " + TABLE_NAME + " WHERE valid >= " + "'" + daynumber + "'";
        Cursor c = db.rawQuery(query, null);
        return c;

    }

    public Cursor getreminder(int daynumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT _id,day,date,reminder from  " + TABLE_NAME + " WHERE valid = " + "'" + daynumber + "'";
        Cursor c = db.rawQuery(query, null);
        return c;

    }

    //A function to retrueve all data
    public Cursor getalldata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT * from  " + TABLE_NAME ;
        Cursor c = db.rawQuery(query, null);
        return c;

    }
    // function to update the meal plan
    public boolean updatedata(String date,String breakfast,String lunch,String snacks,String dinner,String reminder)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL4, breakfast);
        values.put(COL5, lunch);
        values.put(COL6, snacks);
        values.put(COL7, dinner);
        values.put(COL8,reminder);

        long updated = db.update (TABLE_NAME, values,"date = ?",new String[] { date } );
        if (updated  == -1) {
            return false;
        } else {
            return true;
        }
    }



}
