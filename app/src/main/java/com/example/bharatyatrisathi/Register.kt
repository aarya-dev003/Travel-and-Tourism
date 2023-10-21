package com.example.bharatyatrisathi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bharatyatrisathi.databinding.ActivityRegisterBinding
import com.example.bharatyatrisathi.model.UserModel
import com.example.bharatyatrisathi.utils.USER_NODE
import com.example.bharatyatrisathi.utils.USER_PROFILE_FOLDER
import com.example.bharatyatrisathi.utils.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {

    lateinit var user: UserModel
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let{
            uploadImage(uri , USER_PROFILE_FOLDER){
                if(it== null ){

                }
                else{
                    user.image= it

                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }
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
                binding.password.editableText?.toString()!!.isEmpty()
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



                        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this@Register, Homepage::class.java))
                                finish()
                            }

                    }else{
                        Toast.makeText(this@Register, result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        binding.addImage.setOnClickListener{
            launcher.launch("image/*")
        }

        binding.signin.setOnClickListener{
            startActivity(Intent(this@Register, Login::class.java))
            finish()
        }

    }
}