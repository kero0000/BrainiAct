package com.example.rng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;


public class GameSelectionPage extends AppCompatActivity {

    private Timer myTimer;
    private int flag = 0;
    private Button reactionButton, memoryButton, trailMakingButton;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_selection);

        // Assign the button variables their ID's
        reactionButton = findViewById(R.id.reactionEasy);
        memoryButton = findViewById(R.id.memoryGameButton);
        trailMakingButton = findViewById(R.id.reactionHard);



        // Set the buttons such that they can link to another page ( This is an on click listener for reaction game )
        reactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameSelectionPage.this, ReactionDifficultyPage.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for memory game )
        memoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameSelectionPage.this, MemoryGameDifficulty.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for trailMaking test )
        trailMakingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameSelectionPage.this, TrailMakingTestDifficultyPage.class));
            }
        });

    }
}


