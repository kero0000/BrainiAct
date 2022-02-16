package com.example.rng.manager;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.rng.entity.User;
import com.example.rng.pages.RegisterPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterMgr extends RegisterPage {

    private EditText editTextEmail, editTextPassword, editTextName, editTextAge;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public void registerUser(Context parentContext, FirebaseAuth firebaseAuth, ProgressBar progressB, EditText editEmail, EditText editPassword, EditText editName, EditText editAge){

        mAuth = firebaseAuth;
        progressBar = progressB;
        editTextName = editName;
        editTextAge = editAge;
        editTextEmail = editEmail;
        editTextPassword = editPassword;

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){ // check if user registered successfully
                    User user = new User(name, age, email);

                    //send new user object to rtdb
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // get id of registered user which is current user
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){ // save the new user data to firebase rtdb
                              Toast.makeText(parentContext, "User has been successfully registered", Toast.LENGTH_LONG).show();
                            }
                            else{
                               Toast.makeText(parentContext, "Failed to register!", Toast.LENGTH_LONG).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }else{
                    Toast.makeText(parentContext, "Failed to register!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
