package com.example.bharatyatrisathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        navigationView=findViewById(R.id.navigation_drawer);
        Fragment fragment =new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
        navigationView.setSelectedItemId(R.id.profile);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.explore) {
                    Intent intent = new Intent(getApplicationContext(), Explorerpage.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.profile) {
                    return true;
                }

                return false;
            }
        });

    }

}