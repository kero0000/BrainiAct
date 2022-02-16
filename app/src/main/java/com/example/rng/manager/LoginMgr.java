package com.example.rng.manager;


import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginMgr extends AppCompatActivity {
    private String email, password;
    private FirebaseAuth mAuth;
    Context c;
    EditText editTextEmail, editTextPassword;

    public LoginMgr(Context c, EditText editTextEmail, EditText editTextPassword) {
        this.c = c;
        this.editTextEmail = editTextEmail;
        this.editTextPassword = editTextPassword;
    }

    public interface verifyCallBack {
        void verify(boolean[] verified);
    }

    public void userLogin(verifyCallBack verifyUser) {
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        mAuth = FirebaseAuth.getInstance();
        boolean[] verified = new boolean[2];
        verified[0] = false;
        verified[1] = false;

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                verified[0] = true;
                //redirect user profile
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user.isEmailVerified()) {
                    verified[1] = true;
                    verifyUser.verify(verified);
                } else {
                    user.sendEmailVerification();
                    Toast.makeText(c, "Check your email to verify account", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(c, "Failed to login, please check credentials!", Toast.LENGTH_LONG).show();
            }
        });
    }
}