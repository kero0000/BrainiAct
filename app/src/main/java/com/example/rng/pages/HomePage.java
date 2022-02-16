package com.example.rng.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rng.MemoryGameDifficulty;
import com.example.rng.R;
import com.example.rng.ReactionDifficultyPage;
import com.example.rng.TrailMakingTestDifficultyPage;

public class HomePage extends AppCompatActivity {


    private Button reactionGameButton, memoryGameButton, tmtGameButton, leaderboardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Initialise views
        reactionGameButton = findViewById(R.id.reactionGameBtn);
        memoryGameButton = findViewById(R.id.memoryGameBtn);
        tmtGameButton = findViewById(R.id.tmtBtn);
        leaderboardButton = findViewById(R.id.leaderboardButton);

        // Set the buttons such that they can link to another page ( This is an on click listener for reaction game )
        reactionGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, ReactionDifficultyPage.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for memory game )
        memoryGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, MemoryGameDifficulty.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for trailMaking test )
        tmtGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, TrailMakingTestDifficultyPage.class));
            }
        });

        // On click listener to view the leaderboards
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, LeaderboardPage.class));
            }
        });

    }
}