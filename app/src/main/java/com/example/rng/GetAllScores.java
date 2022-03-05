package com.example.rng;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GetAllScores {
    String uid = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("allScores");
    Query query = null;


    public GetAllScores() {};

    public void GetAllScores(String game, String level, FireBaseCallback firebaseCallback){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        query = ref.child(uid).child(game).child(level).orderByChild("date").startAt(GetPrevWeek());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                long prevdate = 0;
                long date = 0;
                long scores, sum = 0, count = 0;
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    date = TimeUnit.MILLISECONDS.toDays((Long) snapshot.child("date").getValue());
                    scores = (long) snapshot.child("scores").getValue();
                    if (prevdate == 0) {
                        prevdate = date;

                    }
                    else if (prevdate < date){
                        Log.d("mytag", String.valueOf(prevdate)+" "+String.valueOf(sum/count));
                        series.appendData(new DataPoint(prevdate, sum /count), true, 30, true);
                        prevdate = date;
                        sum = 0;
                        count = 0;
                    }
                    sum += scores;
                    count++;
                }
                if (count > 0 && prevdate != 0) {
                    Log.d("mytag", String.valueOf(prevdate)+" "+String.valueOf(sum/count));
                    series.appendData(new DataPoint(prevdate, sum / count), true, 30, true);
                }
                firebaseCallback.onCallback(series);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public interface FireBaseCallback{
        void onCallback(LineGraphSeries<DataPoint> series);
    }
    public double GetPrevWeek(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        return (double) cal.getTime().getTime();
    }
}
