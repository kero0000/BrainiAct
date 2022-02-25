package com.example.rng.pages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.rng.manager.LoginMgr;
import com.example.rng.R;

public class LoginPage extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

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
        setContentView(R.layout.activity_main);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        EditText editTextEmail = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        // Redirect to register page
        buttonRegister.setOnClickListener(v -> startActivity(new Intent(LoginPage.this, RegisterPage.class)));

        // Redirects to homepage upon successful sign in

        LoginMgr loginMgr = new LoginMgr(getApplicationContext(), editTextEmail, editTextPassword);
        buttonLogin.setOnClickListener(v -> loginMgr.userLogin(new LoginMgr.verifyCallBack(){
            @Override
            public void verify(boolean[] verified) {
                if (verified[0] && verified[1]) {
                    startActivity(new Intent(LoginPage.this, HomePage.class));
                }
            }
        }));

    }

}