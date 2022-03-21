package com.example.rng.pages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.rng.R;
import com.google.firebase.auth.FirebaseAuth;
import com.example.rng.manager.RegisterMgr;

public class RegisterPage extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextName, editTextAge;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    // Makes keyboard disappear when you press somewhere else
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] screenCoords = new int[2];
            view.getLocationOnScreen(screenCoords);
            float x = ev.getRawX() + view.getLeft() - screenCoords[0];
            float y = ev.getRawY() + view.getTop() - screenCoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

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

        Button registerButton = findViewById(R.id.registerButton);
        RegisterMgr registerMgr = new RegisterMgr();

        // Calls registerManager to do the registering logic
        registerButton.setOnClickListener(v -> registerMgr.registerUser(getApplicationContext(), mAuth, progressBar, editTextEmail, editTextPassword, editTextName, editTextAge));

    }
}