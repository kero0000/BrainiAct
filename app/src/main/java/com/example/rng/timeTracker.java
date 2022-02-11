package com.example.rng;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class timeTracker {

    static void storeTime(double timeTaken) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("allScores");
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        record record = new record(date, timeTaken);

        rootRef.child(uid).child("reaction").push().setValue(record);
    }
}
