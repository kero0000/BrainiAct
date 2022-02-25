package com.example.rng.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.R;
import com.example.rng.manager.ReactionGameMgr;

public class ReactionGamePage extends AppCompatActivity {
    private Button easyButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_game_selection);

        easyButton = findViewById(R.id.reactionBtn);
        hardButton = findViewById(R.id.reactionHard);

        easyButton.setOnClickListener(new View.OnClickListener() {
            String value = "Easy";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReactionGamePage.this, ReactionGameMgr.class);
                i.putExtra("levelChosen", value);
                startActivity(i);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            String value = "Hard";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReactionGamePage.this, ReactionGameMgr.class);
                i.putExtra("levelChosen", value);
                startActivity(i);
            }
        });
    }
}
