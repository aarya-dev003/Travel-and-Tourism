package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bharatyatrisathi.databinding.ActivityLoginpageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class Opening_page extends AppCompatActivity {
Button openingbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);
        openingbutton=findViewById(R.id.openingbutton);






        openingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Intent intent = new Intent(Opening_page.this, Login.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Opening_page.this, Homepage.class);
                    startActivity(intent);
                }
            }
        });


    }
}