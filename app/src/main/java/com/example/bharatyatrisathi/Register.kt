package com.example.bharatyatrisathi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bharatyatrisathi.databinding.ActivityRegisterBinding
import com.example.bharatyatrisathi.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {

    lateinit var user: UserModel
    val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = UserModel()
        binding.signUpBtn.setOnClickListener{
            if (binding.name.editableText?.toString().equals("") or
                binding.email.editableText?.toString()!!.isEmpty() or
                binding.password.editableText?.toString()!!.isEmpty() or
                binding.confirmPassword.editableText?.toString().equals("")
                ) {

                Toast.makeText(this@Register, "Please fill the details", Toast.LENGTH_SHORT).show()
                // Handle empty fields
            }

            else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.editableText?.toString()!!,
                    binding.password.editableText?.toString()!!
                ).addOnCompleteListener {
                    result ->
                    if(result.isSuccessful){
                        user.name = binding.name.editableText?.toString()
                        user.password = binding.password.editableText?.toString()
                        user.email = binding.email.editableText?.toString()

                        Firebase.firestore.collection("User").document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this@Register, "login", Toast.LENGTH_SHORT).show()
                            }

                    }else{
                        Toast.makeText(this@Register, result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }
}