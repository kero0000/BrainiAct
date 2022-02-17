package com.example.rng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rng.pages.ReactionGamePage;

public class ReactionGame extends AppCompatActivity {

    private Timer myTimer;
    private int flag = 0;
    private TextView waitMsg;
    private Button retryButton, exitButton;
    private int randomInt;
    private String gameDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_game_page);
        TextView display_msg;
        display_msg = (TextView) findViewById(R.id.display_msg);
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

        String gameLevel = getIntent().getStringExtra("levelChosen");

        // Listener for play-button
        // This clickable removes the play button, and removes the start prompt ,sg
        // Timer for running red-green sequence is intitialised here too
        playImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // Set appropriate visibility
                v.setVisibility(View.GONE);
                startMsg.setVisibility(View.GONE);

                // Set background to red
                findViewById(R.id.circles_bckgrd).setBackgroundResource(R.color.red_plum);
                waitMsg.setVisibility(View.VISIBLE);                // Displays msg informing users to wait for green screen before tapping screen
                findViewById(R.id.circles_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTimer.cancel();
                        waitMsg.setVisibility(View.INVISIBLE);
                        display_msg.setText("Too soon! Try again!");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);
                    }
                });

                // Initialise timer
                myTimer = new Timer();
                myTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TimerMethod(gameLevel);
                        flag++;
                    }

                }, 0, ThreadLocalRandom.current().nextInt(1000, 6000 + 1));       // 1 seconds == 1000, 10 seconds == 10000, Can set this as we deem fit
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
                        startActivity(new Intent(ReactionGame.this, ReactionGamePage.class));
                    }
                });
            }


        });
    }

    private void TimerMethod(String gameLevel)
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.

        // random integer to decide which screen to display
        Random rd = new Random();
        randomInt =  rd.nextInt(10);

        if (gameLevel.equals("Easy")) {
            gameDifficulty = "easy";
            this.runOnUiThread(Green);
        } else if (gameLevel.equals("Hard")){
            gameDifficulty = "hard";
            if (randomInt == 1 || randomInt == 3 || randomInt == 5) {
                this.runOnUiThread(White);
            } else if (randomInt == 2 || randomInt == 4) {
                this.runOnUiThread(Red);
            } else {
                this.runOnUiThread(Green);
            }
        }
    }



    // Code logic (function) which timer calls at random timer intervals
    // Green Screen
    private Runnable Green = new Runnable() {
        double startTimer, endTimer, timeTaken;
        public void run() {
            TextView display_msg;
            display_msg = (TextView) findViewById(R.id.display_msg);
            if (flag >= 2) {
                myTimer.cancel();
                waitMsg.setVisibility(View.INVISIBLE);
                findViewById(R.id.circles_bckgrd).setBackgroundResource(R.color.teal_200);
                startTimer = System.currentTimeMillis();
                findViewById(R.id.circles_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findViewById(R.id.circles_bckgrd).setOnClickListener(null);
                        endTimer = System.currentTimeMillis();
                        timeTaken = endTimer - startTimer;
                        TimeTracker.storeTime(timeTaken, gameDifficulty, "reaction");
                        display_msg.setText("Congratulations!\n" + "Time taken: " + timeTaken + " ms");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);
                    }
                });

            }
            else {
                findViewById(R.id.circles_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTimer.cancel();
                        waitMsg.setVisibility(View.INVISIBLE);
                        display_msg.setText("Too soon! Try again!");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);

                        findViewById(R.id.circles_bckgrd).setBackgroundResource(R.color.red_plum);

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
                    startActivity(new Intent(ReactionGame.this, ReactionGamePage.class));
                }
            });

        }

    };
    // White Screen
    private Runnable White = new Runnable() {
        public void run() {
            TextView display_msg;
            display_msg = (TextView) findViewById(R.id.display_msg);
            if(flag >= 2) {
                findViewById(R.id.circles_bckgrd).setBackgroundResource(R.color.white);
                findViewById(R.id.circles_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTimer.cancel();
                        waitMsg.setVisibility(View.INVISIBLE);
                        display_msg.setText("Too soon! Try again!");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);
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
                    startActivity(new Intent(ReactionGame.this, ReactionGamePage.class));
                }
            });
        }
    };

    // Red Screen
    private Runnable Red = new Runnable() {
        public void run() {
            TextView display_msg;
            display_msg = (TextView) findViewById(R.id.display_msg);
            if(flag >= 2) {
                findViewById(R.id.circles_bckgrd).setBackgroundResource(R.color.red_plum);
                findViewById(R.id.circles_bckgrd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTimer.cancel();
                        waitMsg.setVisibility(View.INVISIBLE);
                        display_msg.setText("Too soon! Try again!");
                        retryButton.setVisibility(View.VISIBLE);
                        exitButton.setVisibility(View.VISIBLE);
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
                    startActivity(new Intent(ReactionGame.this, ReactionGamePage.class));
                }
            });
        }
    };
}
