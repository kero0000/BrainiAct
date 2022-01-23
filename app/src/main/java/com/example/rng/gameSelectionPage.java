package com.example.rng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class gameSelectionPage extends AppCompatActivity {

    private Timer myTimer;
    private int flag = 0;
    private Button reactionButton, memoryButton, trailMakingButton;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_selection);

        // Assign the button variables their ID's
        reactionButton = findViewById(R.id.reactionGameButton);
        memoryButton = findViewById(R.id.memoryGameButton);
        trailMakingButton = findViewById(R.id.trailMakingTestButton);



        // Set the buttons such that they can link to another page ( This is an on click listener for reaction game )
        reactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gameSelectionPage.this, reactionGame.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for memory game )
        memoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gameSelectionPage.this, memoryGame.class));
            }
        });

        // Set the buttons such that they can link to another page ( This is an on click listener for trailMaking test )
        trailMakingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gameSelectionPage.this, trailMakingTest.class));
            }
        });

    }
}


