package com.example.bharatyatrisathi

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import com.example.bharatyatrisathi.databinding.ActivityProfileBinding
import com.example.bharatyatrisathi.model.UserModel
import com.example.bharatyatrisathi.utils.USER_NODE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.message.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, MeetStranger::class.java))
            finish()
        }

        binding.edit.setOnClickListener{
            val intent = Intent(this@ProfileActivity , Register::class.java)
            intent.putExtra("MODE", 1)
            startActivity(intent)
            finish()
        }




        binding.navigationDrawer.selectedItemId = R.id.profile

        binding.navigationDrawer.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(applicationContext, Homepage::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.explore -> {
                    val intent = Intent(applicationContext, Explorerpage::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                val user:UserModel = it.toObject<UserModel>()!!
                binding.nametv.text = user.name
                binding.email.text = user.email

                if(!user.image.isNullOrEmpty()){
                    Picasso.get().load(user.image).into(binding.profileImage)
                }
            }
        }

}