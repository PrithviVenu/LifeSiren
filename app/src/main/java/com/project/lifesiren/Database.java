package com.project.lifesiren;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class Database extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "LifeSiren.db";
    public static SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE_NAME , null, 1);
        db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE hospital (\n" +
                        "Hospital_Id INTEGER PRIMARY KEY AutoIncrement,\n" +
                        "Hospital_Name VARCHAR,\n" +
                        "City VARCHAR,\n" +
                        "Address VARCHAR,\n" +
                        "Verification_Status VARCHAR,\n" +
                        "Contact_Number INT\n" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE donor (\n" +
                        "Id INTEGER PRIMARY KEY AutoIncrement,\n" +
                        "Name VARCHAR,\n" +
                        "City VARCHAR,\n" +
                        "Address VARCHAR,\n" +
                        "Contact_Number INT,\n" +
                        "Rating INT\n" +
                        ")"
        );

        db.execSQL( " CREATE TABLE organ_request ( Id INTEGER PRIMARY KEY AutoIncrement, UserId INT, UserName   VARCHAR, BloodGroup VARCHAR, Request  VARCHAR ) "
        );

        db.execSQL( "CREATE TABLE organization_auth (\n" +
                "Id INTEGER PRIMARY KEY,\n" +
                "Password VARCHAR\n" +
                ")"
        );

        db.execSQL( "CREATE TABLE user_auth (\n" +
                "Id INTEGER PRIMARY KEY,\n" +
                "Password VARCHAR\n" +
                ")"
        );

        db.execSQL( "CREATE TABLE Organisation (\n" +
                "Id INTEGER PRIMARY KEY,\n" +
                "Organization_Name VARCHAR,\n" +
                "City VARCHAR,\n" +
                "Address VARCHAR,\n" +
                "Verification_Status VARCHAR,\n" +
                "Contact_Number INT\n" +
                ")"
        );

        db.execSQL("CREATE TABLE user (\n" +
                "Id INTEGER PRIMARY KEY,\n" +
                "Name VARCHAR,\n" +
                "City VARCHAR,\n" +
                "Address VARCHAR,\n" +
                "Contact_Number INTEGER,\n" +
                "Rating INTEGER\n" +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }


    public void  postRequest(int userId,String userName,String bloodGroup,String request)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserId",userId);
        contentValues.put("UserName", userName);
        contentValues.put("BloodGroup", bloodGroup);
        contentValues.put("Request", request);
        db.insert("organ_request", null, contentValues);

    }



    public ArrayList<Requests> getAllRequests() {
        ArrayList<Requests> array_list = new ArrayList<Requests>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from organ_request", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Requests req = new Requests();
            req.Id=res.getInt(res.getColumnIndex("Id"));
            req.UserId=res.getInt(res.getColumnIndex("UserId"));
            req.UserName=res.getString(res.getColumnIndex("UserName"));
            req.BloodGroup=res.getString(res.getColumnIndex("BloodGroup"));
            req.Request=res.getString(res.getColumnIndex("Request"));
            array_list.add(req);
            res.moveToNext();
        }
        return array_list;
    }



}
