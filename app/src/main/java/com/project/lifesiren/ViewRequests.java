package com.project.lifesiren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewRequests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);
        requests(findViewById(R.id.requestview));

    }

    public  void requests(View v)
    {
        Database db = new Database(this);
        TextView req = findViewById(R.id.Req);
        String output="";
        ArrayList<Requests> requests = db.getAllRequests();
        if (requests.size() == 0) {
            output="No Requests";
        }
        for (int i = 0; i < requests.size(); i++) {
           Requests request= requests.get(i);
            output+="\n---------------------------------------------------------------------------\n";
            output+="Id:" + request.Id+"UserId :" + request.UserId+"UserName:" + request.UserName+"BloodGroup:" + request.BloodGroup+"Request:" + request.Request;
            output+="\n---------------------------------------------------------------------------\n";

        }
        req.setText(output);



    }





}
