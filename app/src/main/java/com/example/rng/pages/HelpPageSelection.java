package com.example.rng.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.DisplayLeaderBoard;
import com.example.rng.R;

public class HelpPageSelection extends AppCompatActivity{

    // Main screen for leaderboards
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_selection_page);

        Button TMT = findViewById(R.id.TMTBtnHelp);
        Button reaction = findViewById(R.id.reactionBtnHelp);
        Button memory = findViewById(R.id.memoryGameButtonHelp);

        reaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HelpPageSelection.this, ReactionHelpPage.class);
                //i.putExtra("game", "reaction");
                startActivity(i);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HelpPageSelection.this, MemoryHelpPage.class);
                i.putExtra("game", "memory");
                startActivity(i);
            }
        });

        TMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HelpPageSelection.this, TMTHelpPage.class);
               // i.putExtra("game", "TMT");
                startActivity(i);
            }
        });
    }

}


