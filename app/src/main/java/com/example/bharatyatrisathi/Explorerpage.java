package com.example.bharatyatrisathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Explorerpage extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorerpage);
        navigationView=findViewById(R.id.navigation_drawer);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent Profileintent = new Intent(getApplicationContext(), Profile.class);
                Intent Homeintent = new Intent(getApplicationContext(), Homepage.class);

                int id = item.getItemId();
                if (id == R.id.home) {
                    startActivity(Homeintent);
                    return true;
                }
                if (id == R.id.explore) {

                }
                if (id == R.id.profile) {
                    startActivity(Profileintent);
                    return true;
                }

                return false;
            }
        });






    }
}