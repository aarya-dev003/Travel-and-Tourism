package com.example.bharatyatrisathi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class Homepage extends AppCompatActivity {
ImageButton home_to_explore,home_to_profile;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        home_to_explore=findViewById(R.id.home_to_explore);
        home_to_profile=findViewById(R.id.home_to_profile);
        home_to_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Exploreintent=new Intent(getApplicationContext(),Explorerpage.class);
                startActivity(Exploreintent);
            }
        });
        home_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profileintent=new Intent(getApplicationContext(),Profile.class);
                startActivity(Profileintent);
            }
        });



    }
}