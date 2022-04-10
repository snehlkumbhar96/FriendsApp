package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewFriends extends AppCompatActivity {
    DBHelper DB=new DBHelper(NewFriends.this);
    EditText firstname, lastname, mobileno, email;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);
        getSupportActionBar().setTitle("Add Friend");
        firstname = (EditText) findViewById(R.id.fname);
        lastname = (EditText) findViewById(R.id.lname);
        mobileno = (EditText) findViewById(R.id.mno);
        email = (EditText) findViewById(R.id.email);

        save = (Button) findViewById(R.id.save);
        DB = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName=firstname.getText().toString();
                String lastName=lastname.getText().toString();
                String mobileNo=mobileno.getText().toString();
                String Email=email.getText().toString();

                if (firstName.equals("") || email.equals("") || lastName.equals("") || mobileNo.equals(""))
                    Toast.makeText(NewFriends.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkfriend = DB.checkfriend(mobileNo);
                    if (!checkfriend) {
                        Boolean insert = DB.insertDataFriends(firstName, lastName, mobileNo, Email);
                        if (insert) {
                            Toast.makeText(NewFriends.this, "Contact Save successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(NewFriends.this, "insert failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(NewFriends.this, "Friend Contact already exists !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}