package com.example.rng;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;
import java.util.Date;

public class DisplayHealthTracker extends AppCompatActivity {
    String uid = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_health_tracker);

        GraphView easyGraphView = findViewById(R.id.idEasyGraphView);
        GraphView mediumGraphView = findViewById(R.id.idMediumGraphView);
        GraphView hardGraphView = findViewById(R.id.idHardGraphView);

        mediumGraphView.setVisibility(View.INVISIBLE);
        hardGraphView.setVisibility(View.INVISIBLE);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        easyGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        mediumGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        hardGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long)value));
                }
                return super.formatLabel(value,isValueX);
            }
        });

        String game = getIntent().getStringExtra("game");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("allScores");
        GetAllScores GetAllScores = new GetAllScores();
        switch (game) {
            case "TMT":
                GetAllScores.GetAllScores("TMT", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,easyGraphView);
                    }
                });
                GetAllScores.GetAllScores("TMT", "medium", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,mediumGraphView);
                    }
                });
                GetAllScores.GetAllScores("TMT", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,hardGraphView);
                    }
                });
                break;
            case "reaction":
                GetAllScores.GetAllScores("reaction", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,easyGraphView);
                    }
                });
                GetAllScores.GetAllScores("reaction", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,hardGraphView);
                    }
                });
               break;
            case "memory":
                GetAllScores.GetAllScores("memory", "easy", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,easyGraphView);
                    }
                });
                GetAllScores.GetAllScores("memory", "hard", new GetAllScores.FireBaseCallback() {
                    @Override
                    public void onCallback(LineGraphSeries<DataPoint> series) {
                        DrawGraph(series,hardGraphView);
                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + game);
        }
    }

    protected void DrawGraph(LineGraphSeries<DataPoint> series, GraphView graphView){
        series.setDrawDataPoints(true);
        graphView.getViewport().setMaxX(series.getHighestValueX() + 10000);
        graphView.getViewport().setMinX(series.getLowestValueX() + 10000);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.addSeries(series);

    }
}

