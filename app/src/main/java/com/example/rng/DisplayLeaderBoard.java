package com.example.rng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rng.pages.MyCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DisplayLeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_leader_board);
        TextView leaderBoardText = findViewById(R.id.LeaderBoardText);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        MyCallBack myCallback = new MyCallBack() {
            @Override
            public void onCallback(Long value) {
                leaderBoardText.setText(String.valueOf(value));
            }
        };
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TMTHighScore");
        Query query = ref.orderByChild("highScoreEasy");
        String finalUid = uid;
        query.addValueEventListener(new ValueEventListener() {
            int count = 0;
            double percentile;
            boolean check = true;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childsnapshot : snapshot.getChildren()){
                    if(check) {
                        count++;
                    }
                    if (childsnapshot.getKey().equals(finalUid)){
                        Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreEasy();
                        check = false;
                    }
                }
                percentile = snapshot.getChildrenCount() - count;
                percentile = (percentile/ snapshot.getChildrenCount()) *100;
                myCallback.onCallback((long) percentile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}