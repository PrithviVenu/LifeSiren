package com.project.lifesiren;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Utility.Membership.equalsIgnoreCase("User")) {
            setContentView(R.layout.activity_signup_user);
            Utility.Membership = "User";
        }
        else if(Utility.Membership.equalsIgnoreCase("Organisation")) {
            setContentView(R.layout.activity_signup_organisation);
            Utility.Membership="Organisation";

        }

    }
    public void SignupUser(View v)  {

        String Username = ((EditText)findViewById(R.id.uName)).getText().toString();
        String City = ((EditText)findViewById(R.id.uCity)).getText().toString();
        String Address = ((EditText)findViewById(R.id.uAddress)).getText().toString();
        String Contact_Number = ((EditText)findViewById(R.id.uContact_Number)).getText().toString();
        String Password = ((EditText)findViewById(R.id.uPassword)).getText().toString();
        Database db = new Database(this);
        int userId = db.signupUser(Username,City,Address,Contact_Number,Password);
        Utility.id = userId;
        Toast.makeText(getApplicationContext(),"Your User Id:"+userId,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }
    public void SignupOrganisation(View v)  {

        String organizationName = ((EditText)findViewById(R.id.OrganizationName)).getText().toString();
        String City = ((EditText)findViewById(R.id.City)).getText().toString();
        String Address = ((EditText)findViewById(R.id.Address)).getText().toString();
        String Verification_Status = ((EditText)findViewById(R.id.Verification_Status)).getText().toString();
        String Contact_Number = ((EditText)findViewById(R.id.Contact)).getText().toString();
        String Password = ((EditText)findViewById(R.id.Password)).getText().toString();
        Database db = new Database(this);
        int OrganisationId = db.signupOrganisation(organizationName,City,Address,Contact_Number,Verification_Status,Password);
        Utility.id = OrganisationId;
        Toast.makeText(getApplicationContext(),"Your Organisation Id:"+OrganisationId,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }


}
