package com.example.bites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bites.authentication.Login;
import com.example.bites.authentication.SignUp;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.buttonSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    signOutE();
                    finish();
            }
        });

    }

    @Override
    protected void onStart() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        super.onStart();

    }

    private void signOutE() {

        FirebaseAuth.getInstance().signOut();

    }


    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }


    private void goToSignIn() {
        Intent myIntent = new Intent(this, Login.class);
        startActivity(myIntent);
    }

    private void goToSignUp() {
        Intent myIntent = new Intent(this, SignUp.class);
        startActivity(myIntent);
    }
}
