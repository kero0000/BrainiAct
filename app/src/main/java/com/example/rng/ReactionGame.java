package com.example.rng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class ReactionGame extends AppCompatActivity {

    private Timer myTimer;
    private int flag = 0;
    private TextView waitMsg;
    private Button retryButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_game_page);

        // Initialise the Views so we can ID them
        ImageView playImageView = findViewById(R.id.playButton);
        TextView startMsg = findViewById(R.id.startPromptMsgTxt);
        retryButton = findViewById(R.id.retry_button);
        exitButton = findViewById(R.id.exit_button);
        waitMsg = findViewById(R.id.waitForGreen);

        // Set buttons/Views visibility
        waitMsg.setVisibility(View.INVISIBLE);
        retryButton.setVisibility(View.GONE);
        exitButton.setVisibility(View.GONE);

        // Listener for play-button
        // This clickable removes the play button, and removes the start prompt ,sg
        // Timer for running red-green sequence is intitialised here too
        playImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // Set appropriate visibility
                v.setVisibility(View.GONE);
                startMsg.setVisibility(View.GONE);

                // Set background to red
                findViewById(R.id.reaction_game_bckgrd).setBackgroundResource(R.color.red_plum);
                waitMsg.setVisibility(View.VISIBLE);                // Displays msg informing users to wait for green screen before tapping screen

                // Initialise timer
                myTimer = new Timer();
                myTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TimerMethod();
                        flag++;
                    }

                }, 0, ThreadLocalRandom.current().nextInt(1000, 10000 + 1));       // 1 seconds == 1000, 10 seconds == 10000, Can set this as we deem fit
            }

        });
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.

        this.runOnUiThread(Timer_Tick);

    }

    // Code logic (function) which timer calls at random timer intervals
    private Runnable Timer_Tick = new Runnable() {
        double startTimer;
        double timeTaken;
        public void run() {
            TextView display_msg;
            display_msg = (TextView) findViewById(R.id.display_msg);
            if (flag == 2) {
                myTimer.cancel();
                waitMsg.setVisibility(View.INVISIBLE);
                findViewById(R.id.reaction_game_bckgrd).setBackgroundResource(R.color.teal_200);
                startTimer = System.currentTimeMillis();
                findViewById(R.id.reaction_game_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findViewById(R.id.reaction_game_bckgrd).setOnClickListener(null);
                        timeTaken = System.currentTimeMillis() - startTimer;
                        display_msg.setText("Congratulations!\n" + "Time taken: " + timeTaken);
                        TimeTracker.storeTime(timeTaken); // store time in firebase
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);
                    }
                });

            }
            else {
                findViewById(R.id.reaction_game_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTimer.cancel();
                        waitMsg.setVisibility(View.INVISIBLE);
                        display_msg.setText("Too soon! Try again!");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);

                        findViewById(R.id.reaction_game_bckgrd).setBackgroundResource(R.color.red_plum);

                    }

                });
            }

            // On click listener for retry ( Restarts the instance of the screen )
            retryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();                   // restarts the instance of the screen
                }
            });

            // On click listener for exit ( Exits the reaction game, and goes back to the game selection page )
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ReactionGame.this, GameSelectionPage.class));
                }
            });

        }

    };


}
