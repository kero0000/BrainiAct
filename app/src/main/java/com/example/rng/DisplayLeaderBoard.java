package com.example.rng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rng.pages.MyCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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


        String game = getIntent().getStringExtra("game");

        switch (game) {
            case "TMT":

                // first callback for displaying easy high score's percentile
                MyCallBack myCallbackTMTEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }
                };
                // callback for displaying medium high score's percentile
                MyCallBack myCallbackTMTMedium = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewMedium.setText(String.valueOf(value));
                    }
                };
                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackTMTHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }
                };

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TMTHighScore");
                Query query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreEasy();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTEasy.onCallback((long) percentile);
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

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreMedium();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTMedium.onCallback((long) percentile);
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

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(TMTHighScoreRecord.class).getHighScoreHard();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackTMTHard.onCallback((long) percentile);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
                break;

            case "reaction":
                textView5.setVisibility(View.INVISIBLE); // SET MEDIUM TEXT INVISIBLE SINCE MEMORY AND REACTION GAME NO MEDIUM
                // first callback for displaying easy high score's percentile
                MyCallBack myCallbackReactionEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }
                };

                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackReactionHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }
                };

                ref = FirebaseDatabase.getInstance().getReference("reactionHighScore");
                query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreEasy();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackReactionEasy.onCallback((long) percentile);
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

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreHard();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackReactionHard.onCallback((long) percentile);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
                break;
            case "memory":
                textView5.setVisibility(View.INVISIBLE); // SET MEDIUM TEXT INVISIBLE SINCE MEMORY AND REACTION GAME NO MEDIUM
                // first callback for displaying easy high score's percentile
                MyCallBack myCallbackMemoryEasy = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewEasy.setText(String.valueOf(value));
                    }
                };

                // callback for displaying Hard high score's percentile
                MyCallBack myCallbackMemoryHard = new MyCallBack() {
                    @Override
                    public void onCallback(Long value) {
                        textViewHard.setText(String.valueOf(value));
                    }
                };

                ref = FirebaseDatabase.getInstance().getReference("memoryHighScore");
                query = ref.orderByChild("highScoreEasy");
                query.addValueEventListener(new ValueEventListener() {
                    int count = 0;
                    double percentile;
                    boolean check = true;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreEasy();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackMemoryEasy.onCallback((long) percentile);
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

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childsnapshot : snapshot.getChildren()) {
                            if (check) {
                                count++;
                            }
                            if (childsnapshot.getKey().equals(uid)) {
                                Long currentHighScore = childsnapshot.getValue(MemoryReactionHighScoreRecord.class).getHighScoreHard();
                                check = false;
                            }
                        }
                        percentile = snapshot.getChildrenCount() - count;
                        percentile = (percentile / snapshot.getChildrenCount()) * 100;
                        myCallbackMemoryHard.onCallback((long) percentile);
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
