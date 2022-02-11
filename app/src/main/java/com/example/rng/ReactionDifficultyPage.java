package com.example.rng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReactionDifficultyPage extends AppCompatActivity {
    private Button easyButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_game_selection);

        easyButton = findViewById(R.id.reactionEasy);
        hardButton = findViewById(R.id.reactionHard);

        easyButton.setOnClickListener(new View.OnClickListener() {
            String value = "Easy";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReactionDifficultyPage.this, ReactionGame.class);
                i.putExtra("levelChosen", value);
                startActivity(i);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            String value = "Hard";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReactionDifficultyPage.this, ReactionGame.class);
                i.putExtra("levelChosen", value);
                startActivity(i);
            }
        });
    }
}
