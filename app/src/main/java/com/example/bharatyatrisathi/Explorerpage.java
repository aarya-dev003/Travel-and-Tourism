package com.example.bharatyatrisathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Explorerpage extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorerpage);
        navigationView=findViewById(R.id.navigation_drawer);
        navigationView.setSelectedItemId(R.id.explore);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                if (id == R.id.profile) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }

                return false;
            }
        });
    }

}