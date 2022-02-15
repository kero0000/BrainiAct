package com.example.rng;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeTracker {
    private String gameLevel;

    static void storeTime(double timeTaken, String gameLevel, String game) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("allScores");
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Record record = new Record(date, timeTaken);

        rootRef.child(uid).child(game).child(gameLevel).push().setValue(record);
        overWriteHighScore(timeTaken, gameLevel, game);
    }

    static void overWriteHighScore(double timeTaken, String gameLevel, String game){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        if(game.equals("TMT")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("TMTHighScore").child(uid);
            rootRef.addValueEventListener(new ValueEventListener() {
                String gameDifficulty = gameLevel;
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    TMTHighScoreRecord record = snapshot.getValue(TMTHighScoreRecord.class);
                    if(gameDifficulty.equals("easy")){
                        if(timeTaken < record.getHighScoreEasy()){
                            record.setHighScoreEasy((long) timeTaken);
                            rootRef.setValue(record);
                        }
                    }
                    else if(gameDifficulty.equals("hard")){
                        if(timeTaken < record.getHighScoreHard()){
                            record.setHighScoreHard((long) timeTaken);
                            rootRef.setValue(record);
                        }
                    }
                    else if (gameDifficulty.equals("medium")){
                        if(timeTaken < record.getHighScoreMedium()){
                            record.setHighScoreMedium((long) timeTaken);
                            rootRef.setValue(record);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else if(game.equals("reaction")){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("reactionHighScore").child(uid);
            rootRef.addValueEventListener(new ValueEventListener() {
                String gameDifficulty = gameLevel;
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    MemoryReactionHighScoreRecord record = snapshot.getValue(MemoryReactionHighScoreRecord.class);
                    if(gameDifficulty.equals("easy")){
                        if(timeTaken < record.getHighScoreEasy()){
                            record.setHighScoreEasy((long) timeTaken);
                            rootRef.setValue(record);
                        }
                    }
                    else if(gameDifficulty.equals("hard")){
                        if(timeTaken < record.getHighScoreHard()){
                            record.setHighScoreHard((long) timeTaken);
                            rootRef.setValue(record);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}