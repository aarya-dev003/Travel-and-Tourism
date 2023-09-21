package com.example.bharatyatrisathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent Exploreintent = new Intent(getApplicationContext(), Explorerpage.class);
                Intent Homeintent = new Intent(getApplicationContext(), Homepage.class);

                int id = item.getItemId();
                if (id == R.id.home) {
                    startActivity(Homeintent);
                    return true;
                }
                if (id == R.id.explore) {
                    startActivity(Exploreintent);
                    return true;
                }
                if (id == R.id.profile) {

                }

                return false;
            }
        });

    }
}