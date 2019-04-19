package com.example.osamanadeem.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;


public class DBTool extends SQLiteOpenHelper {
    SQLiteDatabase objWrite = getWritableDatabase();
    SQLiteDatabase objRead = getReadableDatabase();
    public DBTool(Context context) {
        super(context, "contactsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTable = "CREATE TABLE Appointments " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT, Type TEXT,  DatenTime TEXT)";


        String sqlCreateTable1 = "CREATE TABLE Images " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,Path TEXT)";
        db.execSQL(sqlCreateTable);
        db.execSQL(sqlCreateTable1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void Insert(HashMap<String,String> contacts)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",contacts.get("name"));
        contentValues.put("Type",contacts.get("type"));
        contentValues.put("DatenTime",contacts.get("datepicker"));
        objWrite.insert("Appointments",null,contentValues);

    }

    public ArrayList<HashMap <String,String>> getAllContacts() {

        ArrayList<HashMap<String, String>> contactList =
                new ArrayList<HashMap<String, String>>();
        String Query = "SELECT * FROM Appointments ";
        Cursor cursor = objRead.
                rawQuery(Query, null);
        if (cursor.moveToNext()) {
            do {
                HashMap<String, String> contacts =
                        new HashMap<String, String>();
                contacts.put("_id", cursor.getString(0));
                contacts.put("Name", cursor.getString(1));
                contacts.put("Type", cursor.getString(2));
                contacts.put("DatenTime", cursor.getString(3));
                contactList.add(contacts);
            } while (cursor.moveToNext());
        }
        return contactList;
    }


    public void insert_images(String arr)
    {
        ContentValues contentValues =  new ContentValues();
        contentValues.put("Path",arr);
        objWrite.insert("Images",null,contentValues);
    }

    public ArrayList<HashMap<String, String>> get_img()
    {
        ArrayList<HashMap<String, String>> contactList =
                new ArrayList<HashMap<String, String>>();
        String Query = "SELECT * FROM Images ";
        Cursor cursor = objRead.
                rawQuery(Query, null);
        if (cursor.moveToNext()) {
            do {
                HashMap<String, String> contacts =
                        new HashMap<String, String>();
                contacts.put("_id", cursor.getString(0));
                contacts.put("Path", cursor.getString(1));
                contactList.add(contacts);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

}
