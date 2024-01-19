package com.example.myapplication;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        String winner = getIntent().getStringExtra("Winner-Loser");
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("Winner: " + winner);
    }
}
