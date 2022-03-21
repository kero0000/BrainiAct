package com.example.rng.manager;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMgr extends AppCompatActivity {
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
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // this will check for 8-64 character with at least 3 numbers or special characters
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&.()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        // this is inside your password check function
        Matcher match = pattern.matcher(password);

        if (email.equals("") || password.equals("")){
            Toast.makeText(c, "All field must be entered!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!email.endsWith("@gmail.com")){
            Toast.makeText(c, "Invalid email entered", Toast.LENGTH_LONG).show();
            return;
        }
        if (!match.matches()){
            Toast.makeText(c, "Invalid password entered", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        boolean[] verified = new boolean[2];

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