package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {
ImageButton Profile_to_explore,Profile_to_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile_to_explore=findViewById(R.id.Profile_to_explore);
        Profile_to_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Exploreintent=new Intent(getApplicationContext(),Explorerpage.class);
                startActivity(Exploreintent);
            }
        });
        Profile_to_home=findViewById(R.id.Profile_to_home);
        Profile_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homeintent=new Intent(getApplicationContext(),Homepage.class);
                startActivity(Homeintent);
            }
        });
    }
}