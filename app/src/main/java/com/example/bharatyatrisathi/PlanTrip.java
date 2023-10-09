package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class PlanTrip extends AppCompatActivity {

   EditText place_to_go_edt,start_date_edt,trip_days_edt,guys_on_trip_edt;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);
        back = findViewById(R.id.backButton);
        place_to_go_edt=findViewById(R.id.place_to_go_edt);
        start_date_edt=findViewById(R.id.start_date_edt);
        trip_days_edt=findViewById(R.id.trip_days_edt);
        guys_on_trip_edt=findViewById(R.id.guys_on_trip_edt);

        String plantrip = "I want to go"+place_to_go_edt.
                getText().
                toString()+" start from "+start_date_edt.
                getText().
                toString()+" and no of days are"+trip_days_edt.
                getText().
                toString()+"no of travelers"+guys_on_trip_edt
                .getText().
                toString();





        back.setOnClickListener(new View.OnClickListener() {


            Intent toMain = new Intent(getApplicationContext(), Homepage.class);
            @Override
            public void onClick(View v) {
                startActivity(toMain);
            }
        });
    }
}