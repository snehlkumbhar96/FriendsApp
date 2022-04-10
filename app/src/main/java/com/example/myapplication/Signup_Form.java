package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Signup_Form extends AppCompatActivity {

    private String Signup_Form = "Signup";
    DBHelper DB=new DBHelper(Signup_Form.this);
    EditText firstname, lastname, mobileno, password, confirmpassword;
    Button signup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle(Signup_Form);


        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        mobileno = (EditText) findViewById(R.id.mobileno);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        signup = (Button) findViewById(R.id.btnsignup);
        DB = new DBHelper(this);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstname.getText().toString();
                String lastName = lastname.getText().toString();
                String mobileNo = mobileno.getText().toString();
                String pass = password.getText().toString();
                String cpass = confirmpassword.getText().toString();

                if (firstName.equals("") || pass.equals("") || cpass.equals("") || lastName.equals("") || mobileNo.equals(""))
                    Toast.makeText(Signup_Form.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(cpass)) {
                        Boolean checkuser = DB.checkusername(mobileNo);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(firstName, lastName, mobileNo, pass);
                            if (insert) {
                                Toast.makeText(Signup_Form.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(Signup_Form.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup_Form.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup_Form.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
