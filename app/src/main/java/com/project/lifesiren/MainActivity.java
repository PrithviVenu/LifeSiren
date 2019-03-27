package com.project.lifesiren;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void authenticateUser(View v)  {
        Intent intent = new Intent(MainActivity.this, Login.class);
        Bundle b = new Bundle();
        b.putString("Membership", "User"); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();


    }
    public void authenticateOrganisation(View v)  {
        Intent intent = new Intent(MainActivity.this, Login.class);
        Bundle b = new Bundle();
        b.putString("Membership", "Organisation"); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();



    }




}
