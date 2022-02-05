package com.example.rng;

public class record {
    String date;
    double scores;

    public record(String date, double scores){
        this.date = date;
        this.scores = scores;
    }
    public record(){}


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
