package com.example.android.sosemergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08-07-2015.
 */
public class DatabaseClass extends SQLiteOpenHelper {
    private static int version=1;
    private static String DataBaseName="ContactData";
    private static String TableName="ContactTable";
    private static String KEY_Name="ContactName";
    private static String Key_No="ContactPhoneNo";
    Context context;
    public DatabaseClass(Context context)
    {
        super(context,DataBaseName,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // String querry="CREATE TABLE "+TableName+"("+KEY_Name+" TEXT PRIMARY KEY,"+Key_No+" TEXT"+")";
        String data="CREATE TABLE " + TableName + "("
                + KEY_Name + " TEXT PRIMARY KEY,"
                + Key_No + " TEXT" + ");";
        //String data1="CREATE TABLE ContactTable(ContactName TEXT PRIMARY KEY,ContactPhoneNo TEXT);";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String querry="DROP TABLE IF EXISTS"+TableName;
        db.execSQL(querry);
        onCreate(db);
    }

    //code to add a contact
    public void addContact(ContactClass a,Context c) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_Name, a.getName());
            values.put(Key_No, a.getPhoneNo());
            db.insert(TableName, null, values);
            db.close();
        }
        catch(SQLiteException e)
        {
            Toast t= Toast.makeText(c,"Contact Already Exists",Toast.LENGTH_LONG);
            t.show();
        }
    }
    //code to view all contacts
    public List<ContactClass> viewContacts() {
        List<ContactClass> l=new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String data = "SELECT * FROM " + TableName;
        Cursor c = db.rawQuery(data, null);
        if (c.moveToFirst()) {
            do {
                ContactClass a= new ContactClass(c.getString(0),c.getString(1));
                l.add(a);
            }while (c.moveToNext());
        }
        db.close();
        return l;
    }
    //code to delete a contact
    void delete (ContactClass a)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String[] p={a.getName()};
        db.delete(TableName,KEY_Name+"=?",p);
    }

}
