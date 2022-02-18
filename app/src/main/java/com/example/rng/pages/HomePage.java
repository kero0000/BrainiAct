package com.example.rng.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.rng.HealthTrackerPage;
import com.example.rng.MemoryGameDifficulty;
import com.example.rng.R;

public class HomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Initialise views
        Button reactionGameButton = findViewById(R.id.reactionGameBtn);
        Button memoryGameButton = findViewById(R.id.memoryGameBtn);
        Button tmtGameButton = findViewById(R.id.tmtBtn);
        Button leaderboardButton = findViewById(R.id.leaderboardButton);
        Button helpButton = findViewById(R.id.helpButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button healthTrackerButton = findViewById(R.id.healthTrackerBtn);

        // v -> is basically just a lambda expression

        // logout and brings user back to main page
        logoutButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, LoginPage.class)));

        // Set the buttons such that they can link to another page ( This is an on click listener for reaction game )
        reactionGameButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, ReactionGamePage.class)));

        // Set the buttons such that they can link to another page ( This is an on click listener for memory game )
        memoryGameButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, MemoryGameDifficulty.class)));

        // Set the buttons such that they can link to another page ( This is an on click listener for trailMaking test )
        tmtGameButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, TrailMakingTestPage.class)));

        // On click listener to view the leaderboards
        leaderboardButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, LeaderboardPageSelection.class)));

        // help me
        helpButton.setOnClickListener(v ->  startActivity(new Intent(HomePage.this, HelpPage.class)));


        healthTrackerButton.setOnClickListener(v ->  startActivity(new Intent(HomePage.this, HealthTrackerPage.class)));

    }
}