package com.example.bharatyatrisathi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Intent iHome=new Intent(Splash_activity.this,MainActivity.class);
                    startActivity(iHome);
                }

                else{
                    Intent intent = new Intent(Splash_activity.this, Homepage.class);
                    startActivity(intent);
                }

            }
        },1500);

    }
}