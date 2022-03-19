package com.example.rng;

import android.app.DownloadManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.rng.entity.MemoryReactionHighScoreRecord;
import com.example.rng.entity.TMTHighScoreRecord;
import com.example.rng.pages.MyCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class DisplayHealthTracker extends AppCompatActivity {
    String uid = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_health_tracker);

        GraphView easyGraphView = findViewById(R.id.idEasyGraphView);
        GraphView mediumGraphView = findViewById(R.id.idMediumGraphView);
        GraphView hardGraphView = findViewById(R.id.idHardGraphView);

        LinearLayout easyht = findViewById(R.id.easyht);
        LinearLayout mediumht = findViewById(R.id.mediumht);
        LinearLayout hardht = findViewById(R.id.hardht);

        TextView easyAverageScore = findViewById(R.id.easyAverageScore);
        TextView mediumAverageScore = findViewById(R.id.mediumAverageScore);
        TextView hardAverageScore = findViewById(R.id.hardAverageScore);

        TextView easyHighScore = findViewById(R.id.easyHighScore);
        TextView mediumHighScore = findViewById(R.id.mediumHighScore);
        TextView hardHighScore = findViewById(R.id.hardHighScore);

        TextView easyLowScore = findViewById(R.id.easyLowScore);
        TextView mediumLowScore = findViewById(R.id.mediumLowScore);
        TextView hardLowScore = findViewById(R.id.hardLowScore);

        TextView EasyTitle = findViewById(R.id.EasyTitle);
        TextView MediumTitle = findViewById(R.id.MediumTitle);
        TextView HardTitle = findViewById(R.id.HardTitle);

        easyht.setVisibility((View.GONE));
        mediumht.setVisibility(View.GONE);
        hardht.setVisibility(View.GONE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        easyGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(TimeUnit.DAYS.toMillis((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        mediumGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(TimeUnit.DAYS.toMillis((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        hardGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(TimeUnit.DAYS.toMillis((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        String game = getIntent().getStringExtra("game");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("allScores");
        GetAllScores GetAllScores = new GetAllScores();
        switch (game) {
            case "TMT":
                easyht.setVisibility((View.VISIBLE));
                mediumht.setVisibility(View.VISIBLE);
                hardht.setVisibility(View.VISIBLE);
                GetAllScores.GetAllScores("TMT", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        Log.d("mtyag", String.valueOf(series.isEmpty()));
                        if (series.isEmpty()){
                            easyht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,easyGraphView);
                            easyAverageScore.setText(CalculateAverage(series));
                            easyHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            easyLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            EasyTitle.setText("Trail Making Test : Easy");
                        }
                    }
                });
                GetAllScores.GetAllScores("TMT", "medium", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            mediumht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,mediumGraphView);
                            mediumAverageScore.setText(CalculateAverage(series));
                            mediumHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            mediumLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            MediumTitle.setText("Trail Making Test : Medium");
                        }
                    }
                });
                GetAllScores.GetAllScores("TMT", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            hardht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,hardGraphView);
                            hardAverageScore.setText(CalculateAverage(series));
                            hardHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            hardLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            HardTitle.setText("Trail Making Test : Hard");
                        }
                    }
                });
                break;
            case "reaction":
                easyht.setVisibility((View.VISIBLE));
                hardht.setVisibility(View.VISIBLE);
                GetAllScores.GetAllScores("reaction", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            easyht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,easyGraphView);
                            easyAverageScore.setText(CalculateAverage(series));
                            easyHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            easyLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            EasyTitle.setText("Reaction Game : Easy");
                        }
                    }
                });
                GetAllScores.GetAllScores("reaction", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            hardht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,hardGraphView);
                            hardAverageScore.setText(CalculateAverage(series));
                            hardHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            hardLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            HardTitle.setText("Reaction Game : Hard");
                        }
                    }
                });
               break;
            case "memory":
                easyht.setVisibility((View.VISIBLE));
                hardht.setVisibility(View.VISIBLE);
                GetAllScores.GetAllScores("memory", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            easyht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,easyGraphView);
                            easyAverageScore.setText(CalculateAverage(series));
                            easyHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            easyLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            EasyTitle.setText("Memory Game : Easy");
                        }
                    }
                });
                GetAllScores.GetAllScores("memory", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        if (series.isEmpty()){
                            hardht.setVisibility(View.GONE);
                        }
                        else{
                            DrawGraph(series,hardGraphView);
                            hardAverageScore.setText(CalculateAverage(series));
                            hardHighScore.setText(String.valueOf((int)series.getHighestValueY()));
                            hardLowScore.setText(String.valueOf((int)series.getLowestValueY()));
                            HardTitle.setText("Memory Game : Hard");
                        }
                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + game);
        }
    }

    protected void DrawGraph(LineGraphSeries<DataPoint> series, GraphView graphView){
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        series.setColor(ContextCompat.getColor(this, R.color.Background));
        series.setThickness(5);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);
        graphView.setBackgroundResource(R.drawable.rounded_corners);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.VERTICAL);
        graphView.getGridLabelRenderer().reloadStyles();
        graphView.getViewport().setMaxX(series.getHighestValueX());
        graphView.getViewport().setMinX(series.getLowestValueX());
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.addSeries(series);
    }

    protected String CalculateAverage(LineGraphSeries<DataPoint> series){
        double sum = 0, count = 0;
        Iterator value = series.getValues(series.getLowestValueX(),series.getHighestValueX());
        while(value.hasNext()){
            DataPoint data = (DataPoint) value.next();
            sum += data.getY();
            count += 1;
        }
        int average = (int) (sum/count);
        return String.valueOf(average);
    }

}

