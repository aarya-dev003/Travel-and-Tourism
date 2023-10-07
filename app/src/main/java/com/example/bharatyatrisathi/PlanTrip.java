package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PlanTrip extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);
        back = findViewById(R.id.backButton);


        back.setOnClickListener(new View.OnClickListener() {


            Intent toMain = new Intent(getApplicationContext(), Homepage.class);
            @Override
            public void onClick(View v) {
                startActivity(toMain);
            }


        });
    }
}