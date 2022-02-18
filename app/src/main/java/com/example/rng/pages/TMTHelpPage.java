package com.example.rng.pages;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.rng.R;
import com.example.rng.manager.TrailMakingTestMgr;
import com.example.rng.utilities.TrailMakingUtilities;

import java.util.ArrayList;
import java.util.Arrays;

public class TMTHelpPage extends AppCompatActivity {

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
    private TrailMakingUtilities trailMakingUtilities = new TrailMakingUtilities();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmt_help_page);

        imageView = findViewById(R.id.background_canvas);
        width = ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).width;
        height = ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).height;
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        boolean oneClicked = false;
        headerTextView = findViewById(R.id.headerText);

        imageView = findViewById(R.id.background_canvas);

        ArrayList<Integer> listImgView = new ArrayList<Integer>(
                Arrays.asList(
                        R.id.circles_1, R.id.circles_2, R.id.circles_3, R.id.circles_4,
                        R.id.circles_5
                )
        );

        for (int count = 2; count < listImgView.size(); count++) {
            findViewById(listImgView.get(count)).setVisibility(View.GONE);
        }

        headerTextView.setText("First, click on 1.");

        findViewById(listImgView.get(0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPath.add(userPath.size(), 0);
                headerTextView.setText("Next, click on 2.");
                findViewById(listImgView.get(0)).setOnClickListener(null);
                findViewById(listImgView.get(1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPath.add(1);
                        double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(0)));
                        double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(1)));
                        trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], GREEN);
                        findViewById(listImgView.get(3)).setVisibility(View.VISIBLE);
                        findViewById(listImgView.get(1)).setOnClickListener(null);
                        headerTextView.setText("Now, click on 4.");
                        findViewById(listImgView.get(3)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userPath.add(3);
                                double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(1)));
                                double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(3)));
                                trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], RED);
                                headerTextView.setText("And, that was wrong...");
                                findViewById(listImgView.get(3)).setOnClickListener(null);
                                new CountDownTimer(3000, 1000) {
                                    public void onFinish() {
                                        // When timer is finished
                                        // Execute your code here

                                        headerTextView.setText("Click 4 again to remove the red line!");
                                        findViewById(listImgView.get(3)).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                bitmap = trailMakingUtilities.undrawLine(TMTHelpPage.this, userPath,listImgView, height, width);
                                                imageView.setImageBitmap(bitmap);
                                                findViewById(listImgView.get(3)).setOnClickListener(null);
                                                findViewById(listImgView.get(4)).setVisibility(View.VISIBLE);
                                                findViewById(listImgView.get(2)).setVisibility(View.VISIBLE);
                                                new CountDownTimer(2000, 1000) {
                                                    public void onFinish() {
                                                        // When timer is finished
                                                        // Execute your code here
                                                        headerTextView.setText("Now, complete the tutorial");
                                                        findViewById(listImgView.get(3)).setOnClickListener(null);
                                                        findViewById(listImgView.get(2)).setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(1)));
                                                                double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(2)));
                                                                trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], GREEN);
                                                                findViewById(listImgView.get(3)).setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(2)));
                                                                        double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(3)));
                                                                        trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], GREEN);
                                                                        findViewById(listImgView.get(4)).setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                double[] coordStartPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(3)));
                                                                                double[] coordEndPoint = trailMakingUtilities.getCoordinates(findViewById(listImgView.get(4)));
                                                                                trailMakingUtilities.drawLine(imageView, bitmap, coordStartPoint[0], coordStartPoint[1], coordEndPoint[0], coordEndPoint[1], GREEN);
                                                                                headerTextView.setText("Congratulations, you have completed the tutorial.");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });

                                                    }
                                                    public void onTick(long millisUntilFinished) {
                                                        // millisUntilFinished    The amount of time until finished.
                                                    }
                                                }.start();
                                            }
                                        });

                                    }

                                    public void onTick(long millisUntilFinished) {
                                        // millisUntilFinished    The amount of time until finished.
                                    }
                                }.start();

                            }
                        });
                    }
                });
            }
        });








    }
}
