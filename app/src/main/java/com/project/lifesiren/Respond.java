package com.project.lifesiren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Respond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond);
    }
    public void sendResponse(View v)  {
        Database db = new Database(this);
        String  response,contact;
        int reqId;
        reqId = Integer.parseInt(((EditText)findViewById(R.id.req)).getText().toString());
        response = ((EditText)findViewById(R.id.res)).getText().toString();
        contact = ((EditText)findViewById(R.id.con)).getText().toString();
       int res =  db.sendResponse(reqId,response,contact);
        if(res==1)
        {
            Toast.makeText(getApplicationContext(),"Response Posted",Toast.LENGTH_LONG).show();

        }

    }



}
