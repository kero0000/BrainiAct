package com.example.rng.pages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.rng.R;
import com.google.firebase.auth.FirebaseAuth;
import com.example.rng.manager.RegisterMgr;


public class RegisterPage extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextName, editTextAge;
    private FirebaseAuth mAuth;
    private Button registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterMgr registerMgr = new RegisterMgr();
                registerMgr.registerUser(getApplicationContext(), mAuth, progressBar, editTextEmail, editTextPassword, editTextName, editTextAge);

            }
        });
    }

}