package com.project.lifesiren;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.widget.Toast;

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
                "Organization_Name VARCHAR UNIQUE,\n" +
                "City VARCHAR,\n" +
                "Address VARCHAR,\n" +
                "Verification_Status VARCHAR,\n" +
                "Contact_Number VARCHAR\n" +
                ")"
        );

        db.execSQL("CREATE TABLE user (\n" +
                "Id INTEGER PRIMARY KEY,\n" +
                "Name VARCHAR,\n" +
                "City VARCHAR,\n" +
                "Address VARCHAR,\n" +
                "Contact_Number VARCHAR\n" +
                ")"
        );

        db.execSQL("CREATE TABLE response (\n" +
                "ResponseId INTEGER PRIMARY KEY,\n" +
                "ResponderId INTEGER,\n" +
                "Membership VARCHAR,\n" +
                "RequestId INTEGER,\n" +
                "Response VARCHAR,\n" +
                "ContactNumber VARCHAR\n" +
                ")"
        );
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",1001);
        contentValues.put("Password", "Hii");
        db.insert("organization_auth", null, contentValues);
        db.insert("user_auth", null, contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }


    public int  postRequest(String userName,String bloodGroup,String request)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserId",Utility.id);
        contentValues.put("UserName", userName);
        contentValues.put("BloodGroup", bloodGroup);
        contentValues.put("Request", request);
        db.insert("organ_request", null, contentValues);
        return 1;
    }



    public ArrayList<Requests> getAllRequests() {
        ArrayList<Requests> array_list = new ArrayList<Requests>();
        SQLiteDatabase db = this.getReadableDatabase();
        if(Utility.yourReq==0) {
            Cursor res = db.rawQuery("select * from organ_request where UserId !=\"" + Utility.id + "\"", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                Requests req = new Requests();
                req.Id = res.getInt(res.getColumnIndex("Id"));
                req.UserId = res.getInt(res.getColumnIndex("UserId"));
                req.UserName = res.getString(res.getColumnIndex("UserName"));
                req.BloodGroup = res.getString(res.getColumnIndex("BloodGroup"));
                req.Request = res.getString(res.getColumnIndex("Request"));
                array_list.add(req);
                res.moveToNext();
            }
        }
        else if(Utility.yourReq==1) {
            Cursor res = db.rawQuery("select * from organ_request where UserId ==\"" + Utility.id + "\"", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                Requests req = new Requests();
                req.Id = res.getInt(res.getColumnIndex("Id"));
                req.UserId = res.getInt(res.getColumnIndex("UserId"));
                req.UserName = res.getString(res.getColumnIndex("UserName"));
                req.BloodGroup = res.getString(res.getColumnIndex("BloodGroup"));
                req.Request = res.getString(res.getColumnIndex("Request"));
                array_list.add(req);
                res.moveToNext();
            }
        }
        return array_list;

    }
    public ArrayList<Response> getAllResponses(int rid) {
        ArrayList<Response> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resu =  db.rawQuery( "select * from response where RequestId = \""+rid+"\"", null );
        resu.moveToFirst();

        while(resu.isAfterLast() == false){
            Response res = new Response();
            res.ResponseId=resu.getInt(resu.getColumnIndex("ResponseId"));
            res.ResponderId=resu.getInt(resu.getColumnIndex("ResponderId"));
            res.Membership=resu.getString(resu.getColumnIndex("Membership"));
            res.RequestId=resu.getInt(resu.getColumnIndex("RequestId"));
            res.Response=resu.getString(resu.getColumnIndex("Response"));
            res.Contact=resu.getInt(resu.getColumnIndex("ContactNumber"));
            array_list.add(res);
            resu.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> userprof() {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user where id=\""+Utility.id+"\"", null );
        res.moveToFirst();
           array_list.add(Integer.toString(res.getInt(res.getColumnIndex("Id"))));
           array_list.add(res.getString(res.getColumnIndex("Name")));
           array_list.add(res.getString(res.getColumnIndex("City")));
           array_list.add(res.getString(res.getColumnIndex("Address")));
           array_list.add(res.getString(res.getColumnIndex("Contact_Number")));

        return array_list;
    }

    public ArrayList<String> findUser(String uid){
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user where id=\""+uid+"\"", null );
        res.moveToFirst();
        array_list.add(Integer.toString(res.getInt(res.getColumnIndex("Id"))));
        array_list.add(res.getString(res.getColumnIndex("Name")));
        array_list.add(res.getString(res.getColumnIndex("City")));
        array_list.add(res.getString(res.getColumnIndex("Address")));
        array_list.add(res.getString(res.getColumnIndex("Contact_Number")));

        return array_list;
    }


    public ArrayList<String> orgprof() {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from organisation where id=\""+Utility.id+"\"", null );
        res.moveToFirst();
        array_list.add(Integer.toString(res.getInt(res.getColumnIndex("Id"))));
        array_list.add(res.getString(res.getColumnIndex("Organization_Name")));
        array_list.add(res.getString(res.getColumnIndex("City")));
        array_list.add(res.getString(res.getColumnIndex("Address")));
        array_list.add(res.getString(res.getColumnIndex("Verification_Status")));
        array_list.add(res.getString(res.getColumnIndex("Contact_Number")));
        return array_list;
    }
    public ArrayList<String> findOrganisation(String oid) {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from organisation where id=\""+oid+"\"", null );
        res.moveToFirst();
        array_list.add(Integer.toString(res.getInt(res.getColumnIndex("Id"))));
        array_list.add(res.getString(res.getColumnIndex("Organization_Name")));
        array_list.add(res.getString(res.getColumnIndex("City")));
        array_list.add(res.getString(res.getColumnIndex("Address")));
        array_list.add(res.getString(res.getColumnIndex("Verification_Status")));
        array_list.add(res.getString(res.getColumnIndex("Contact_Number")));
        return array_list;
    }

    public Boolean login(String Username , String Password){

        if(Utility.Membership.equalsIgnoreCase("User"))
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "select * from user_auth where id = "+Username+" AND "+"password=\""+Password+"\"", null );
            res.moveToFirst();
            while(res.isAfterLast() == false){
               return true;
            }
        }
        else  if(Utility.Membership.equalsIgnoreCase("Organisation"))
        {
            if(Utility.Membership.equalsIgnoreCase("Organisation"))
            {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor res =  db.rawQuery( "select * from Organization_auth where id = "+Username+" AND "+"password=\""+Password+"\"", null );
                res.moveToFirst();
                while(res.isAfterLast() == false){
                    return true;
                }
            }
        }


        return  false;

    }

    public int signupUser(String Username,String City,String Address,String Contact_Number,String Password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Username);
        contentValues.put("City", City);
        contentValues.put("Address", Address);
        contentValues.put("Contact_Number", Contact_Number);
        db.insert("user", null, contentValues);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Id from user where Name = \"" + Username+"\"", null );
        res.moveToFirst();
        int Id=res.getInt(res.getColumnIndex("Id"));
        ContentValues content = new ContentValues();
        content.put("Id",Id);
        content.put("Password", Password);
        db.insert("user_auth", null, content);
        return Id;


    }

    public int sendResponse(int reqId,String response,String contact)
    {
        ContentValues content = new ContentValues();
        content.put("ResponderId",Utility.id);
        content.put("Membership", Utility.Membership);
        content.put("RequestId",reqId);
        content.put("Response", response);
        content.put("ContactNumber", contact);
        db.insert("response", null, content);
        return 1 ;
    }




    public int signupOrganisation(String organizationName,String City,String Address,String Contact_Number,String Verification_Status ,String Password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Organization_Name",organizationName);
        contentValues.put("City", City);
        contentValues.put("Address", Address);
        contentValues.put("Verification_Status", Verification_Status);
        contentValues.put("Contact_Number", Contact_Number);
        db.insert("organisation", null, contentValues);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Id from organisation where Organization_Name =\"" + organizationName+"\"", null );
        res.moveToFirst();
        int Id=res.getInt(res.getColumnIndex("Id"));
        ContentValues content = new ContentValues();
        content.put("Id",Id);
        content.put("Password", Password);
        db.insert("Organization_auth", null, content);
        return Id;


    }





}
