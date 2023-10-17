package com.example.bharatyatrisathi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class Loginpage extends AppCompatActivity {
    EditText editTextemail,editTextpassword;
    Button submit,signup,SignIn;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    private static final int RC_SIGN_IN = 1;


    GoogleSignInClient mgoogleSignInClient;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        editTextemail=findViewById(R.id.EditTextEmailAddress);
        editTextpassword=findViewById(R.id.editTextTextPassword);
        submit=findViewById(R.id.submitbutton);
        signup=findViewById(R.id.Login_signup);
        SignIn=findViewById(R.id.SignIn);

        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog( Loginpage.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mgoogleSignInClient= GoogleSignIn.getClient(this,gso);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString();
                String password = editTextpassword.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Homepage.class);


                if (email.isEmpty()) {
                    Toast.makeText(Loginpage.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(Loginpage.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login_to_Register = new Intent(Loginpage.this, Register.class);
                startActivity(Login_to_Register);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



    }

    private void signIn() {
        Intent signInIntent = mgoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_SIGN_IN:
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    FirebaseAuthWithGoogle(account.getIdToken());

                } catch (ApiException e) {
                    throw new RuntimeException();
                }
                break;
        }


    }

    private void FirebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();


                    Users users = new Users();
                    users.setUserId(user.getUid());
                    users.setName(user.getDisplayName());
                    users.setProfile(user.getPhotoUrl().toString());

                    database.getReference().child("Users").child(user.getUid()).setValue(users);
                    Intent intent = new Intent(Loginpage.this,Homepage.class);
                    startActivity(intent);



                }
                else {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}