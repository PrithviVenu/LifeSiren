package com.project.lifesiren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Utility.Membership.equalsIgnoreCase("User"))
        {
            setContentView(R.layout.user_profile);
            user();
        }
        else if(Utility.Membership.equalsIgnoreCase("organisation"))
        {
            setContentView(R.layout.organisation_profile);
            organisation();
        }
    }

    public void user()
    {

        Database db = new Database(this);
        ArrayList<String> user =db.userprof();
        ((TextView) findViewById(R.id.id1)).setText(user.get(0));
        ((TextView) findViewById(R.id.uName1)).setText(user.get(1));
        ((TextView) findViewById(R.id.uCity1)).setText(user.get(2));
        ((TextView) findViewById(R.id.uAddress1)).setText(user.get(3));
        ((TextView) findViewById(R.id.uContact_Number1)).setText(user.get(4));

    }
    public void organisation()
    {

        Database db = new Database(this);
        ArrayList<String> org =db.orgprof();
        ((TextView) findViewById(R.id.oid1)).setText(org.get(0));
        ((TextView) findViewById(R.id.oName1)).setText(org.get(1));
        ((TextView) findViewById(R.id.oCity1)).setText(org.get(2));
        ((TextView) findViewById(R.id.oAddress1)).setText(org.get(3));
        ((TextView) findViewById(R.id.oVerification_Status1)).setText(org.get(4));
        ((TextView) findViewById(R.id.oContact_Number1)).setText(org.get(5));
    }

}
