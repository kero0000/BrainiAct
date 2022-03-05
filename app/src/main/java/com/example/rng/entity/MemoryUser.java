package com.example.rng.entity;

import androidx.annotation.NonNull;

import com.example.rng.entity.MemoryReactionHighScoreRecord;
import com.example.rng.entity.scoreRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoryUser {
    protected int stage = 1;
    protected int lives = 3;
    protected String gameDifficulty;

    //constructor
    public MemoryUser(){};

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getLives(){
        return this.lives;
    }

    public void setStage(int stage){
        this.stage = stage;
    }

    public int getStage(){
        return this.stage;
    }

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public String getGameDifficulty(){
        return this.gameDifficulty;
    }

    public void storeStage() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("allScores");
        double date = System.currentTimeMillis();
        scoreRecord record = new scoreRecord(date, this.stage);

        rootRef.child(uid).child("memory").child(this.gameDifficulty).push().setValue(record);
        overWriteHighScore((long) this.stage,this.gameDifficulty);
    }

    public void overWriteHighScore(long stage, String gameDifficulty){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("memoryHighScore").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            String gameLevel = gameDifficulty;
            Long stageCleared = stage;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MemoryReactionHighScoreRecord record = snapshot.getValue(MemoryReactionHighScoreRecord.class);
                if(gameLevel.equals("easy")){
                    if(stageCleared > record.getHighScoreEasy()){
                        record.setHighScoreEasy((long) stageCleared);
                        ref.setValue(record);
                    }
                }

                else if(gameLevel.equals("hard")){
                    if(stageCleared > record.getHighScoreHard()){
                        record.setHighScoreHard((long) stageCleared);
                        ref.setValue(record);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
