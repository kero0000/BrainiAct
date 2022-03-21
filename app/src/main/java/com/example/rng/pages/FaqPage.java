package com.example.rng.pages;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rng.R;

public class FaqPage extends AppCompatActivity {

    // Main screen for leaderboards
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_page);

        TextView headerTextView = findViewById(R.id.faq_header);
        TextView contentTextView = findViewById(R.id.faq_text);
        contentTextView.setMovementMethod(new ScrollingMovementMethod());

        headerTextView.setText("Frequently Asked Questions \n");
        contentTextView.setText(
                "1. How is the progression chart plotted? \n" +
                "    The average score of the games played by the users each day will be used to plot the graph shown. \n" +
                "2. What does the Reaction Game assess? \n" +
                "    It tests the user's ability to react quickly to the change in the colour of the screen. \n" +
                "3. What does the Trail Making Test assess? \n" +
                "     It tests the user's ability to recognise the order of the numbers/alphabets. \n" +
                "4. What does the Memory Game assess? \n" +
                "    It tests the user's memory ability to remember and repeat the sequence shown in the game.");

    }

}