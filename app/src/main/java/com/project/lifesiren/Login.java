package com.project.lifesiren;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String value="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_login);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            value = bundle.getString("Membership");
        if(value.equalsIgnoreCase("User")) {
            Utility.Membership="User";
        }
        else if(value.equalsIgnoreCase("Organisation")) {
            Utility.Membership="Organisation";
        }

    }
    public void login(View v)  {
        String Username = ((EditText)findViewById(R.id.userrIdd)).getText().toString();
        String Password = ((EditText)findViewById(R.id.pass1)).getText().toString();
        Database db = new Database(this);

        if(db.login(Username,Password))
        {
            Intent intent = new Intent(this, HomeActivity.class);
            Utility.id=Integer.parseInt(Username);
            Bundle b = new Bundle();
            b.putString("Membership", Utility.Membership); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
            finish();
        }
        else
        Toast.makeText(getApplicationContext(),"Incorrect Password Or User Name",Toast.LENGTH_LONG).show();



    }
    public void newUser(View v)  {

        Intent intent = new Intent(this, Signup.class);
        Bundle b = new Bundle();
        b.putString("Membership", Utility.Membership); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();

    }






}
