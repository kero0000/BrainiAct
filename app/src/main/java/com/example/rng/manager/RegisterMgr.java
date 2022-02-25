package com.example.rng.manager;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.rng.entity.MemoryReactionHighScoreRecord;
import com.example.rng.entity.TMTHighScoreRecord;
import com.example.rng.entity.User;
import com.example.rng.pages.RegisterPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.*;


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

        // this will check for 8-64 character with at least 3 numbers or special characters
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&.()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        // this is inside your password check function
        Matcher match = pattern.matcher(password);

        if (email.equals("") || password.equals("") || name.equals("") || age.equals("")){
            Toast.makeText(parentContext, "All field must be entered!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!email.endsWith("@gmail.com")){
            Toast.makeText(parentContext, "Invalid email entered", Toast.LENGTH_LONG).show();
            return;
        }
        if (Integer.valueOf(age) < 5 || Integer.valueOf(age) > 99){
            Toast.makeText(parentContext, "Invalid age entered", Toast.LENGTH_LONG).show();
            return;
        }
        if (!match.matches()){
            Toast.makeText(parentContext, "Invalid password entered", Toast.LENGTH_LONG).show();
            return;
        }

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

                    TMTHighScoreRecord tmtHighScoreRecord = new TMTHighScoreRecord(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);

                    FirebaseDatabase.getInstance().getReference("TMTHighScore")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(tmtHighScoreRecord);

                    MemoryReactionHighScoreRecord reactionHighScoreRecord = new MemoryReactionHighScoreRecord(Long.MAX_VALUE, Long.MAX_VALUE);

                    FirebaseDatabase.getInstance().getReference("reactionHighScore")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(reactionHighScoreRecord);

                    MemoryReactionHighScoreRecord memoryHighScoreRecord = new MemoryReactionHighScoreRecord(Long.MIN_VALUE, Long.MIN_VALUE);

                    FirebaseDatabase.getInstance().getReference("memoryHighScore")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(memoryHighScoreRecord);

                }
                else{
                    Toast.makeText(parentContext, "Failed to register!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
