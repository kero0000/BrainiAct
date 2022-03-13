package com.example.rng.pages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rng.R;

public class TMTHelpPage extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmt_help_page);

        TextView headerTextView = findViewById(R.id.headerText);

        headerTextView.setText(" Instructions for Trail Making test \n" +
                "1. Click on the nodes in increasing order \n" +
                "2. For Easy mode, nodes would be numbered from 1 to 15 \n" +
                "3. For Medium mode, nodes would be numbered from A to O \n" +
                "4. For Hard mode, nodes would be numbered from 1 to 8, and alphabets from A to G \n" +
                "nodes for hard mode should be ordered as such, 1->A->2->B and so on...");

        headerTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
