package com.project.lifesiren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewResponse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_response);
    }
    public void viewResponses(View v){
        Database db = new Database(this);
        int rid =Integer.parseInt(((EditText) findViewById(R.id.rid)).getText().toString());
        String output = "";
        ArrayList<Response> res = db.getAllResponses(rid);
        TextView req = findViewById(R.id.text);
        if (res.size() == 0) {
            output="No Response";
        }
        for (int i = 0; i < res.size(); i++) {
            Response response= res.get(i);
            output+="\n----------------------------------------------------------------------------------------\n";
            output+="Response Id : " + response.ResponseId+" Responder Id : " + response.ResponderId+" Membership : " +response.Membership+" RequestId : " + response.RequestId+" Response : " + response.Response+" Contact Number : " + response.Contact;
            output+="\n----------------------------------------------------------------------------------------\n";

        }
        req.setText(output);


    }
}
