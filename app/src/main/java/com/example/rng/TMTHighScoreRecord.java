package com.example.rng;

public class TMTHighScoreRecord {
    private Long HighScoreEasy;
    private Long HighScoreMedium;
    private Long HighScoreHard;

    public void setHighScoreEasy(Long HighScoreEasy){
        this.HighScoreEasy = HighScoreEasy;
    }
    public void setHighScoreMedium(Long HighScoreMedium){
        this.HighScoreMedium = HighScoreMedium;
    }
    public void setHighScoreHard(Long HighScoreHard){
        this.HighScoreHard = HighScoreHard;
    }

    public Long getHighScoreEasy(){
        return HighScoreEasy;
    }

    public Long getHighScoreMedium() {
        return HighScoreMedium;
    }

    public Long getHighScoreHard() {
        return HighScoreHard;
    }
}
