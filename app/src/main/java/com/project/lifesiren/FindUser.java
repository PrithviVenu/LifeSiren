package com.project.lifesiren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FindUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
    }
    public void findUser(View v){
        String uid = ((EditText)findViewById(R.id.uid)).getText().toString();
        Database db = new Database(this);
        ArrayList<String>user=db.findUser(uid);
        ((TextView) findViewById(R.id.id1)).setText("Id:"+user.get(0));
        ((TextView) findViewById(R.id.uName1)).setText("User Name:"+user.get(1));
        ((TextView) findViewById(R.id.uCity1)).setText("City:"+user.get(2));
        ((TextView) findViewById(R.id.uAddress1)).setText("Address:"+user.get(3));
        ((TextView) findViewById(R.id.uContact_Number1)).setText("Contact Number:"+user.get(4));
    }
}
