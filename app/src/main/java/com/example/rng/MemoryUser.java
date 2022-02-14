package com.example.rng;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoryUser {
    protected int stage = 1;
    protected int lives = 3;
    protected String gameDifficulty;

    //constructor
    MemoryUser(){};

    protected void setLives(int lives){
        this.lives = lives;
    }

    protected int getLives(){
        return this.lives;
    }

    protected void setStage(int stage){
        this.stage = stage;
    }

    protected int getStage(){
        return this.stage;
    }

    protected void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    protected String getGameDifficulty(){
        return this.gameDifficulty;
    }

    protected void storeStage() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("allScores");
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Record record = new Record(date, this.stage);

        rootRef.child(uid).child("memory").child(this.gameDifficulty).push().setValue(record);
    }

}
