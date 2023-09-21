package com.example.bharatyatrisathi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity {
BottomNavigationView navigationView;
Button plantrip;
    FloatingActionButton chatBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        plantrip=findViewById(R.id.Homestartplanning);
        navigationView=findViewById(R.id.navigation_drawer);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent Exploreintent = new Intent(getApplicationContext(), Explorerpage.class);
                Intent Profileintent = new Intent(getApplicationContext(), Profile.class);
                Intent Homeintent = new Intent(getApplicationContext(), Homepage.class);

                int id = item.getItemId();
                        if (id == R.id.home) {

                        }
                        if (id == R.id.explore) {
                            startActivity(Exploreintent);
                        }
                        if (id == R.id.profile) {
                            startActivity(Profileintent);
                        }
                return true;
            }
        });
        plantrip.setOnClickListener(new View.OnClickListener() {
           Intent intent=new Intent(getApplicationContext(),PlanTrip.class);
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });


        chatBot = findViewById(R.id.chatBotButton);

        chatBot.setOnClickListener(new View.OnClickListener() {
            Intent toChatBox = new Intent(getApplicationContext(),ChatBotActivity.class);
            @Override
            public void onClick(View v) {
                startActivity(toChatBox);
            }
        });
    }
}