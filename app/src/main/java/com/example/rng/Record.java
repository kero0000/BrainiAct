package com.example.rng;

public class Record {
    String date;
    double scores;

    public Record(String date, double scores){
        this.date = date;
        this.scores = scores;
    }
    public Record(){}


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
