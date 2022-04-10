package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Login_Form extends AppCompatActivity {

    private Object Signup_Form;
    EditText mobileno1,password1;
    Button btnlogin1,btnsignup1;

    DBHelper DB=new DBHelper(Login_Form.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login");
        mobileno1=findViewById(R.id.mobileno1);
        password1=findViewById(R.id.password1);
        btnlogin1=findViewById(R.id.btnlogin1);
        btnsignup1=findViewById(R.id.btnsignup);

        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String mobileNo=mobileno1.getText().toString();
            String pass=password1.getText().toString();

            if(mobileNo.equals("") || pass.equals(""))
            {
                Toast.makeText(Login_Form.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            }else {
                Boolean checkuserpass = DB.checkusernamepassword(mobileNo, pass);
                if(checkuserpass)
                {
                    mobileno1.setText("");
                    password1.setText("");
                    Toast.makeText(Login_Form.this, "User Logged In Successfully ", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(Login_Form.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });


    }

   public void btn_signupForm(View view) {

        Intent i = new Intent(getApplicationContext(),Signup_Form.class);
        startActivity(i);
    }

}