package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginpage extends AppCompatActivity {
EditText editTextemail,editTextpassword;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        editTextemail=findViewById(R.id.EditTextEmailAddress);
        editTextpassword=findViewById(R.id.editTextTextPassword);
        submit=findViewById(R.id.submitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString();
                String password = editTextpassword.getText().toString();
                Intent intent = new Intent(Loginpage.this, Homepage.class);
                if (email.isEmpty()) {
                    Toast.makeText(Loginpage.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(Loginpage.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });



    }
}