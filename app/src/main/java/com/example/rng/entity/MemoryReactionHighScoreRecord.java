package com.example.rng.entity;

public class MemoryReactionHighScoreRecord {
    private Long HighScoreEasy;
    private Long HighScoreHard;

    public MemoryReactionHighScoreRecord(Long HighScoreEasy, Long HighScoreHard){
        this.HighScoreEasy = HighScoreEasy;
        this.HighScoreHard = HighScoreHard;
    }

    public MemoryReactionHighScoreRecord(){}

    public void setHighScoreEasy(Long HighScoreEasy){
        this.HighScoreEasy = HighScoreEasy;
    }

    public void setHighScoreHard(Long HighScoreHard){
        this.HighScoreHard = HighScoreHard;
    }

    public Long getHighScoreEasy(){
        return HighScoreEasy;
    }

    public Long getHighScoreHard() {
        return HighScoreHard;
    }
}
