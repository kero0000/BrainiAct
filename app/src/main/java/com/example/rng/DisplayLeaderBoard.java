package com.example.rng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.rng.entity.MemoryReactionHighScoreRecord;
import com.example.rng.entity.TMTHighScoreRecord;
import com.example.rng.pages.MyCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DisplayLeaderBoard extends AppCompatActivity {

    String uid = (String) FirebaseAuth.getInstance().getCurrentUser().getUid();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_leader_board);
        TextView textViewEasy = findViewById(R.id.textViewEasy);
        TextView textViewHard = findViewById(R.id.textViewHard);
        TextView textViewMedium = findViewById(R.id.textViewMedium);
        TextView textView5 = findViewById(R.id.textView5);

        TextView yourBest = findViewById(R.id.yourBestScore);
        TextView yourBestScore = findViewById(R.id.bestScore);
        TextView yourBestScoreMed = findViewById(R.id.yourBestMed);
        TextView yourBestScoreHard = findViewById(R.id.yourBestHard);

        String game = getIntent().getStringExtra("game");

        switch (game) {
            case "TMT":

                // Callback for best score for TMT
                MyCallBack myCallbackYourBestTMTEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScore.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScore.setText(s);
                    }
                };

                MyCallBack myCallbackYourBestTMTMedium= new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScoreMed.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScoreMed.setText(s);
                    }
                };


                MyCallBack myCallbackYourBestTMTHard= new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScoreHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScoreHard.setText(s);
                    }
                };

                // first callback for displaying easy high score's percentile
                MyCallBack myCallbackTMTEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };
                // callback for displaying medium high score's percentile
                MyCallBack myCallbackTMTMedium = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewMedium.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };
                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackTMTHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TMTHighScore");
                Query query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreEasy();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTEasy.onCallback((long) percentile);
                        myCallbackYourBestTMTEasy.onCallback((long) highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });

                query = ref.orderByChild("highScoreMedium");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreMedium();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTMedium.onCallback((long) percentile);
                        myCallbackYourBestTMTMedium.onCallback((long)highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });

                query = ref.orderByChild("highScoreHard");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreHard();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTHard.onCallback((long) percentile);
                        myCallbackYourBestTMTHard.onCallback((long) highScore);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
                break;

            case "reaction":
                textView5.setVisibility(View.GONE);
                textViewMedium.setVisibility(View.GONE);// SET MEDIUM TEXT INVISIBLE SINCE MEMORY AND REACTION GAME NO MEDIUM
                // first callback for displaying easy high score's percentile

                yourBestScoreMed.setVisibility(View.GONE);

                MyCallBack myCallbackYourBestRxnEasy= new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScore.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScore.setText(s);
                    }
                };

                MyCallBack myCallbackYourBestRxnHard= new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScoreHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScoreHard.setText(s);
                    }
                };



                MyCallBack myCallbackReactionEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };

                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackReactionHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };

                ref = FirebaseDatabase.getInstance().getReference("reactionHighScore");
                query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreEasy();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackReactionEasy.onCallback((long) percentile);
                        myCallbackYourBestRxnEasy.onCallback((long) highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });

                query = ref.orderByChild("highScoreHard");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreHard();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackReactionHard.onCallback((long) percentile);
                        myCallbackYourBestRxnHard.onCallback((long) highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
                break;
            case "memory":
                textView5.setVisibility(View.GONE);
                textViewMedium.setVisibility(View.GONE);// SET MEDIUM TEXT INVISIBLE SINCE MEMORY AND REACTION GAME NO MEDIUM
                // first callback for displaying easy high score's percentile
                yourBestScoreMed.setVisibility(View.GONE);

                MyCallBack myCallbackMemoryYourBestEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScore.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScore.setText(s);
                    }
                };

                MyCallBack myCallbackMemoryYourBestHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        yourBestScoreHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        yourBestScoreHard.setText(s);
                    }
                };


                MyCallBack myCallbackMemoryEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };

                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackMemoryHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }

                    @Override
                    public void onCallback(String s) {
                        textViewEasy.setText(s);
                    }
                };

                ref = FirebaseDatabase.getInstance().getReference("memoryHighScore");
                query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreEasy();
                                highScore = currentHighScore;
                                check = false;

                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackMemoryEasy.onCallback((long) percentile);
                        myCallbackMemoryYourBestEasy.onCallback((long) highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });

                query = ref.orderByChild("highScoreHard");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;
                    long highScore = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreHard();
                                highScore = currentHighScore;
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackMemoryHard.onCallback((long) percentile);
                        myCallbackMemoryYourBestHard.onCallback((long) highScore);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + game);
        }
    }
}
