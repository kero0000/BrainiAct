package com.example.rng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {


    private Button gameButton, leaderboardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Initialise views
        gameButton = findViewById(R.id.gameButton);
        leaderboardButton = findViewById(R.id.leaderboardButton);

        // On click listener for "Game" button. Redirects user to game selection page, where theyll be presented with a few options of games to choose from
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, gameSelectionPage.class));
            }
        });

        // On click listener to view the leaderboards
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, leaderboardPage.class));
            }
        });

    }
}