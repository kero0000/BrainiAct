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
        TMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LeaderboardPageSelection.this, DisplayLeaderBoard.class));
            }
        });
    }

}
