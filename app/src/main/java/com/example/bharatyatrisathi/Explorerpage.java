package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Explorerpage extends AppCompatActivity {
ImageButton explore_to_profile,explore_to_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorerpage);

        explore_to_home=findViewById(R.id.explore_to_home);
        explore_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homeintent=new Intent(getApplicationContext(),Homepage.class);
                startActivity(Homeintent);
            }
        });
        explore_to_profile=findViewById(R.id.explore_to_home);
        explore_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profileintent=new Intent(getApplicationContext(),Homepage.class);
                startActivity(Profileintent);
            }
        });





    }
}