package com.example.bharatyatrisathi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bharatyatrisathi.databinding.ActivityProfileBinding
import com.example.bharatyatrisathi.databinding.ActivitySettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Setting : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            startActivity(Intent(this@Setting, ProfileActivity::class.java))
            finish()
        }

        binding.btnlogout.setOnClickListener {
            if (Firebase.auth.currentUser != null) {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@Setting, Login::class.java))
                Toast.makeText(this@Setting, "Logged out successfully", Toast.LENGTH_SHORT).show()
                finish()
            }



        }
    }
}