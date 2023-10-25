package com.example.bharatyatrisathi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Homepage extends AppCompatActivity {
BottomNavigationView navigationView;
ImageButton minigames_btn;
Button plantrip;
    ImageButton chatBot;
    Button FindStranger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        plantrip=findViewById(R.id.Homestartplanning);
        FindStranger=findViewById(R.id.FindStranger);
        navigationView=findViewById(R.id.navigation_drawer);
        navigationView.setSelectedItemId(R.id.home);
        minigames_btn=findViewById(R.id.minigames_btn);


        FindStranger.setOnClickListener(new View.OnClickListener() {
            Intent intent=new Intent(getApplicationContext(),MeetStranger.class);
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                        if (id == R.id.home) {
                            return true;
                        }
                        if (id == R.id.explore) {
                            Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.profile) {
                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
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
            Intent toChatBox = new Intent(getApplicationContext(), ChatBotActivity.class);

            @Override
            public void onClick(View v) {
                startActivity(toChatBox);
            }
        });
        Intent intent_minigames =new Intent(getApplicationContext(),Minigames.class);
        minigames_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_minigames);
            }
        });
    }

}
