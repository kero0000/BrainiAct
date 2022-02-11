package com.example.rng;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MemoryGameDifficulty extends AppCompatActivity {
    protected int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_game_difficulty_selector);

        // identify the level buttons
        Button Easy = findViewById(R.id.easyButton);
        Button Hard = findViewById((R.id.hardButton));

        // set onclick listeners
        Easy.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // if easy is clicked, the activity will switch to memoryGame
                // the variable level is passed into the memoryGame activity
                level = 1;
                Intent intent = new Intent(MemoryGameDifficulty.this, MemoryGame.class);
                intent.putExtra("key",level);
                startActivity(intent);
            }

        });
        Hard.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // if hard is clicked, the activity will switch to memoryGame
                // the variable level is passed into the memoryGame activity
                level = 2;
                Intent intent = new Intent(MemoryGameDifficulty.this, MemoryGame.class);
                intent.putExtra("key",level);
                startActivity(intent);
            }
        });
    }
}
