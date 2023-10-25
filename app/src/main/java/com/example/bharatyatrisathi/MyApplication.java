package com.example.bharatyatrisathi;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MyApplication extends Application {
    private GoogleSignInAccount googleSignInAccount;

    public GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    public void setGoogleSignInAccount(GoogleSignInAccount account) {
        this.googleSignInAccount = account;
    }
}