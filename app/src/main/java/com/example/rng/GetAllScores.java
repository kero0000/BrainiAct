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
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetAllScores {
    String uid = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("allScores");
    Query query = null;

    public GetAllScores() {};

    public void GetAllScores(String game, String level, FireBaseCallback firebaseCallback){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        query = ref.child(uid).child(game).child(level).orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                Date prevdate = null;
                Date date = null;
                long scores, sum = 0, count = 0;
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    try {
                        date = new SimpleDateFormat("dd-MM-yyyy").parse((String) snapshot.child("date").getValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    scores = (long) snapshot.child("scores").getValue();
                    if (prevdate == null) {
                        prevdate = date;
                    } else if (prevdate.compareTo(date) != 0) {
                        Log.d("mytag", String.valueOf(prevdate)+" "+String.valueOf(sum/count));
                        series.appendData(new DataPoint(prevdate, sum / count), true, 30, true);
                        prevdate = date;
                        sum = 0;
                        count = 0;
                    }
                    sum += scores;
                    count++;
                }
                if (count > 0 && prevdate != null) {
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
}
