package com.example.bites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText loginView      = findViewById(R.id.login);
                EditText motPassView      = findViewById(R.id.motPass);

                String login    = loginView.getText().toString();
                String password = motPassView.getText().toString();

                login(login,password);
            }
        });

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

   public void login (String email,String password){
       mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       String TAG="singin";
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.d(TAG, "signInWithEmail:success");
                           FirebaseUser user = mAuth.getCurrentUser();
                           Toast.makeText(Login.this, "ok", Toast.LENGTH_SHORT).show();
                           //updateUI(user);
                           goToMain();
                       } else {
                           // If sign in fails, display a message to the user.
                           Log.w(TAG, "signInWithEmail:failure", task.getException());
                           Toast.makeText(Login.this, "Authentication failed.",
                                   Toast.LENGTH_LONG).show();
                           //updateUI(null);
                       }

                       // ...
                   }
               });
   }

    private void goToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    private void goToSignUp() {
        Intent myIntent = new Intent(this, SignUp.class);
        startActivity(myIntent);
    }
}
