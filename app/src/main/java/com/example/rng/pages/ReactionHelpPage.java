package com.example.rng.pages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.R;

public class ReactionHelpPage extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_help_page);

        TextView headerTextView = findViewById(R.id.header2);


        headerTextView.setText(" Instructions for Reaction Game\n" +
                "1. Wait for the green screen to display.\n" +
                "2. Click on the green screen when it appears.\n" +
                "3. Do not click other colour screens.");

        headerTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
