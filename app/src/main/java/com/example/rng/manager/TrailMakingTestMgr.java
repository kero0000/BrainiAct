package com.example.rng.manager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.rng.R;
import com.example.rng.TimeTracker;
import com.example.rng.utilities.TrailMakingUtilities;
import com.example.rng.pages.TrailMakingTestPage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TrailMakingTestMgr extends AppCompatActivity {
    private Canvas canvas;
    private Paint paint;
    private ImageView imageView;
    private Bitmap bitmap;
    private ArrayList<Integer> userPath = new ArrayList<Integer>();
    private int height, width;
    private int GREEN = Color.GREEN;
    private int RED = Color.RED;
    private int incorrectSequence = 0;
    private double startTimer, endTimer, timeTaken;
    private Button retryButton, exitButton, playButton;
    private TextView playTextView, headerTextView;
    private String gameDifficulty = "Easy";
    // Main screen for trail making test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circles);

        imageView = findViewById(R.id.background_canvas);
        width = ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).width;
        height = ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).height;
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        retryButton = findViewById(R.id.retry);
        exitButton = findViewById(R.id.exit);
        playButton = findViewById(R.id.play);
        playTextView = findViewById(R.id.playText);
        headerTextView = findViewById(R.id.headerText);

        retryButton.setVisibility(View.GONE);
        exitButton.setVisibility(View.GONE);

        // initialize array of ImageViews
        ArrayList<Integer> listImgView = new ArrayList<Integer>(
                Arrays.asList(
                        R.id.circles_1,R.id.circles_2,R.id.circles_3,R.id.circles_4,
                        R.id.circles_5,R.id.circles_6,R.id.circles_7,R.id.circles_8,
                        R.id.circles_9,R.id.circles_10,R.id.circles_11,R.id.circles_12,
                        R.id.circles_13,R.id.circles_14,R.id.circles_15
                ));

        ArrayList<Integer> AlphabetImgView = new ArrayList<Integer>(
                Arrays.asList(
                        R.drawable.circle_a, R.drawable.circle_b, R.drawable.circle_c, R.drawable.circle_d,
                        R.drawable.circle_e, R.drawable.circle_f, R.drawable.circle_g, R.drawable.circle_h,
                        R.drawable.circle_i, R.drawable.circle_j, R.drawable.circle_k, R.drawable.circle_l,
                        R.drawable.circle_m, R.drawable.circle_n, R.drawable.circle_o
                ));

        ArrayList<Integer> NumbersImgView = new ArrayList<Integer>(
                Arrays.asList(
                        R.drawable.circles1, R.drawable.circles2, R.drawable.circles3, R.drawable.circles4,
                        R.drawable.circles5, R.drawable.circles6, R.drawable.circles7, R.drawable.circles8,
                        R.drawable.circles9, R.drawable.circles10, R.drawable.circles11, R.drawable.circles12,
                        R.drawable.circles13, R.drawable.circles14, R.drawable.circles15
                ));

        String gameLevel = getIntent().getStringExtra("levelChosen"); //The key argument here must match that used in the other activity

        if (gameLevel.equals("Medium"))
        {
            gameDifficulty = "medium";
            for (int i = 0; i < AlphabetImgView.size(); i++)
            {
                ImageView imageview;
                imageview= (ImageView)findViewById(listImgView.get(i));
                imageview.setImageResource(AlphabetImgView.get(i));
            }
        }

        if (gameLevel.equals("Hard"))
        {
            gameDifficulty = "hard";
            int intCount = 0;
            int alphaCount = 0;
            for (int i = 0; i < AlphabetImgView.size(); i++)
            {
                ImageView imageview;
                if (i % 2 == 0)
                {
                    imageview= (ImageView)findViewById(listImgView.get(i));
                    imageview.setImageResource(NumbersImgView.get(intCount));
                    intCount += 1;
                }
                else
                {
                    imageview= (ImageView)findViewById(listImgView.get(i));
                    imageview.setImageResource(AlphabetImgView.get(alphaCount));
                    alphaCount += 1;
                }
            }
        }



        for (int count = 0; count < listImgView.size(); count++) {
            findViewById(listImgView.get(count)).setVisibility(View.GONE);
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playButton.setVisibility(View.GONE);
                playTextView.setVisibility(View.GONE);
                headerTextView.setVisibility(View.GONE);

                for (int count = 0; count < listImgView.size(); count++) {
                    findViewById(listImgView.get(count)).setVisibility(View.VISIBLE);
                }

                // randomize the images' positions
                randomizeImagePos(listImgView);

                // startTimer
                startTimer = System.currentTimeMillis();

                TrailMakingUtilities trailMakingUtilities = new TrailMakingUtilities();
                // initialize onClick listeners for all the buttons
                for (int i = 0; i < 15; i++) {

                    findViewById(listImgView.get(i)).setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {

                              Integer lastClickedID = view.getId();
                              int elementToAdd = 0;
                              int previousClick;
                              int seq;
                              int duplicate = 0;

                              if (userPath.size() > 0) {
                                  previousClick = userPath.get(userPath.size()-1);
                                  double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(previousClick)));
                                  double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(lastClickedID));

                                  // Find the element to add to the users path
                                  for (int j = 0; j < listImgView.size(); j++) {
                                      if (lastClickedID.equals(listImgView.get(j))) { // equals() compare Integer objects' value
                                          elementToAdd = j;
                                          break;
                                      }
                                  }

                                  // check if element is already in the userPath before adding
                                  // optimized/made it look nicer it from the above
                                  // sets the duplicate flag
                                  if (userPath.contains(elementToAdd))
                                      duplicate = 1;

                                  // add to user path if its not a duplicate
                                  if (duplicate == 0)
                                      userPath.add(userPath.size(), elementToAdd);

                                  // check for incorrectSequence
                                  // sets the incorrectSequence flag
                                  for (seq = 0; seq < userPath.size(); seq ++) {
                                      if (listImgView.get(userPath.get(seq)) != listImgView.get(seq)) {
                                          incorrectSequence = 1;
                                          break;
                                      }
                                      incorrectSequence = 0;
                                  }

                                  if (incorrectSequence != 1 && duplicate != 1)
                                      trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], GREEN);
                                  else if (incorrectSequence == 1 && duplicate != 1)
                                      trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], RED);
                                  else if (duplicate == 1 && incorrectSequence == 1)
                                      bitmap = trailMakingUtilities.undrawLine(TrailMakingTestMgr.this, userPath, listImgView, height, width);
                                      imageView.setImageBitmap(bitmap);

                              } else {
                                  // add element to path chosen by user
                                  for (int j = 0; j < listImgView.size(); j++) {
                                      if (lastClickedID.equals(listImgView.get(j))) { // equals() compare Integer objects' value
                                          elementToAdd = j;
                                          break;
                                      }
                                  }
                                  userPath.add(elementToAdd);
                              }

                              if (checkGameOver()) {
                                  endTimer = System.currentTimeMillis();
                                  timeTaken = endTimer - startTimer;
                                  displayWinMsg(timeTaken, gameDifficulty);

                                  //de-initialize onClick listeners for the nodes
                                  for (int i = 0; i < 15; i++) {
                                      findViewById(listImgView.get(i)).setOnClickListener(null);
                                  }

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
                                          startActivity(new Intent(TrailMakingTestMgr.this, TrailMakingTestPage.class));
                                      }
                                  });
                              }
                          }
                      });
                }
            }
        });
    }

    private boolean checkGameOver() {
        boolean gameOver = false;

        if (userPath.size() == 15) {
            for (int i = 0; i < userPath.size(); i++) {
                if (userPath.get(i) != i) {
                    gameOver = false;
                    break;
                }
                gameOver = true;
            }
        }

        return gameOver;
    }

    private void displayWinMsg(double timeTaken, String gameDifficulty) {
        // overwrite current bitmap
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        imageView.bringToFront();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        canvas = new Canvas(bitmap);

        float x = imageView.getX() + imageView.getWidth() / 4;
        float y = imageView.getY() + imageView.getHeight() / 2;

        canvas.drawText("Congratulations!", x + 150 , y , paint);
        canvas.drawText("Time Taken: " + timeTaken/1000 + " s", x + 100, y + 120, paint);
        retryButton.setVisibility(View.VISIBLE);
        exitButton.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(bitmap);

        TimeTracker.storeTime(timeTaken, gameDifficulty, "TMT");
    }

    private void randomizeImagePos(ArrayList<Integer> listImgView) {
        int lenOfArray;
        ArrayList<Integer> listImgViews = new ArrayList<Integer>(listImgView);

        lenOfArray = listImgView.size();

        for (int count = 0; count < lenOfArray / 2; count++) {
            ConstraintLayout.LayoutParams paramsOne, paramsTwo;
            Random rd = new Random();
            Integer removeOne, removeTwo;
            int biasOne, biasTwo;
            float tempBiasX, tempBiasY;

            if (listImgView.size() > 1) {
                biasOne = rd.nextInt(listImgViews.size()) % listImgViews.size();
                biasTwo = rd.nextInt(listImgViews.size()) % listImgViews.size();

                paramsOne = (ConstraintLayout.LayoutParams) findViewById(listImgViews.get(biasOne)).getLayoutParams();
                paramsTwo = (ConstraintLayout.LayoutParams) findViewById(listImgViews.get(biasTwo)).getLayoutParams();

                // store temp bias
                tempBiasX = paramsOne.horizontalBias;
                tempBiasY = paramsOne.verticalBias;

                // swap pos for first node
                paramsOne.horizontalBias = paramsTwo.horizontalBias;
                paramsOne.verticalBias = paramsTwo.verticalBias;

                // swap pos for second node
                paramsTwo.horizontalBias = tempBiasX;
                paramsTwo.verticalBias = tempBiasY;

                // set the layout
                findViewById(listImgViews.get(biasOne)).setLayoutParams(paramsOne);
                findViewById(listImgViews.get(biasTwo)).setLayoutParams(paramsTwo);

                // swapped nodes to be removed
                removeOne = listImgViews.get(biasOne);
                removeTwo = listImgViews.get(biasTwo);

                listImgViews.remove(removeOne);
                listImgViews.remove(removeTwo);
            }
        }
    }
}
