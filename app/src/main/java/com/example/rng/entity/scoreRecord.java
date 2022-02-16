package com.example.rng.entity;

public class scoreRecord {
    String date;
    double scores;

    public scoreRecord(String date, double scores){
        this.date = date;
        this.scores = scores;
    }
    public scoreRecord(){}


    public String getDate(){
        return this.date;
    }

    public double getScores(){
        return this.scores;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setScores(double scores){
        this.scores = scores;
    }


}
