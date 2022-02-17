package com.example.rng.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.R;
import com.example.rng.manager.TrailMakingTestMgr;

public class TrailMakingTestPage extends AppCompatActivity {

    private Button easyButton, mediumButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_making_game_selection);

        easyButton = findViewById(R.id.TmtEasy);
        mediumButton = findViewById(R.id.TmtMedium);
        hardButton = findViewById(R.id.TmtHard);

        easyButton.setOnClickListener(new View.OnClickListener() {
            String value="Easy";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrailMakingTestPage.this, TrailMakingTestMgr.class);
                i.putExtra("levelChosen",value);
                startActivity(i);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            String value="Medium";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrailMakingTestPage.this, TrailMakingTestMgr.class);
                i.putExtra("levelChosen",value);
                startActivity(i);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            String value="Hard";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrailMakingTestPage.this, TrailMakingTestMgr.class);
                i.putExtra("levelChosen",value);
                startActivity(i);
            }
        });
    }

}
