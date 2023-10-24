package com.example.bharatyatrisathi

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bharatyatrisathi.databinding.ActivityLoginpageBinding
import com.example.bharatyatrisathi.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private val binding by lazy{
        ActivityLoginpageBinding.inflate(layoutInflater)
    }

    private lateinit var client: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        client= GoogleSignIn.getClient(this, options)
        binding.googleSignIn.setOnClickListener{
            val intent = client.signInIntent
            startActivityForResult(intent,10001 )
        }



        binding.LoginSignup.setOnClickListener{
            startActivity(Intent(this@Login , Register::class.java))
            finish()
        }
        binding.loginbutton.setOnClickListener {

            if (binding.email.editableText?.toString()!!.isEmpty() or
                binding.password.editableText?.toString()!!.isEmpty()
            ){
                Toast.makeText(this@Login,
                    "Please Fill the Details",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val user = UserModel (binding.email.editableText?.toString()!!,
                    binding.password.editableText?.toString()!!)

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            startActivity(Intent(this@Login, Homepage::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@Login, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10001){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken , null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val i = Intent(this@Login, Homepage::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }

}