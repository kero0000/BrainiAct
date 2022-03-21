package com.example.rng.pages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rng.R;

public class MemoryHelpPage extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmt_help_page);

        TextView headerTextView = findViewById(R.id.headerText);


        headerTextView.setText(" Instructions for Memory Game\n" +
                "1. The screen will first display blinking squares.\n" +
                "2. Remember the blinking squares' pattern.\n" +
                "3. Click on the squares that was blinking previously.\n" +
                "4. You lose a life from clicking the wrong square.\n" +
                "5. Losing 3 lives ends the game.");

        headerTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
