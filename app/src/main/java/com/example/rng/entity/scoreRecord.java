package com.example.rng.entity;

public class scoreRecord {
    double date;
    double scores;

    public scoreRecord(double date, double scores){
        this.date = date;
        this.scores = scores;
    }
    public scoreRecord(){}


    public double getDate(){
        return this.date;
    }

    public double getScores(){
        return this.scores;
    }

    public void setDate(double date){
        this.date = date;
    }

    public void setScores(double scores){
        this.scores = scores;
    }


}
