package com.example.bharatyatrisathi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bharatyatrisathi.databinding.ActivityProfileBinding
import com.example.bharatyatrisathi.databinding.ActivitySettingBinding

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
    }
}