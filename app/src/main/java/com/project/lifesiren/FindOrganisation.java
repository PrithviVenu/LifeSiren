package com.project.lifesiren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FindOrganisation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_organisation);
    }

    public void findOrganisation(View v){
        String oid = ((EditText)findViewById(R.id.oid)).getText().toString();
        Database db = new Database(this);
        ArrayList<String> org =db.findOrganisation(oid);
        ((TextView) findViewById(R.id.oid1)).setText("Organisation Id:"+org.get(0));
        ((TextView) findViewById(R.id.oName1)).setText("Organisation Name:"+org.get(1));
        ((TextView) findViewById(R.id.oCity1)).setText("City:"+org.get(2));
        ((TextView) findViewById(R.id.oAddress1)).setText("Address:"+org.get(3));
        ((TextView) findViewById(R.id.oVerification_Status1)).setText("Verification Status:"+org.get(4));
        ((TextView) findViewById(R.id.oContact_Number1)).setText("Contact Number:"+org.get(5));
    }
}
