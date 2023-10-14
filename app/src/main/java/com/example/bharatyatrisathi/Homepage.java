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
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                        if (id == R.id.home) {
                            return true;
                        }
                        if (id == R.id.explore) {
                            Intent intent = new Intent(getApplicationContext(), Explorerpage.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.profile) {
                            Intent intent = new Intent(getApplicationContext(), Profile.class);
                            startActivity(intent);
                            return true;
                        }
                        return false;
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
            Intent toChatBox = new Intent(getApplicationContext(), ChatBotKotlin.class);

            @Override
            public void onClick(View v) {
                startActivity(toChatBox);
            }
        });
    }
}