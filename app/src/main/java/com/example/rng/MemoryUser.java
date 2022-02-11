package com.example.rng;

public class MemoryUser {
    protected int stage = 1;
    protected int lives = 3;

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

}
