package com.example.rng.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.DisplayLeaderBoard;
import com.example.rng.R;

public class LeaderboardPageSelection extends AppCompatActivity {

    // Main screen for leaderboards
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_selection_page);

        Button TMT = findViewById(R.id.TMT);
        Button reaction = findViewById(R.id.reactionBtn);
        Button memory = findViewById(R.id.memoryGameButton);

        TMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LeaderboardPageSelection.this, DisplayLeaderBoard.class);
                i.putExtra("game", "TMT");
                startActivity(i);
            }
        });

        reaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LeaderboardPageSelection.this, DisplayLeaderBoard.class);
                i.putExtra("game", "reaction");
                startActivity(i);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LeaderboardPageSelection.this, DisplayLeaderBoard.class);
                i.putExtra("game", "memory");
                startActivity(i);
            }
        });
    }

}