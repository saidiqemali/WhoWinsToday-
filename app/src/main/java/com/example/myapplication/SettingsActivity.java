package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private TextView inputPlayer1;
    private TextView inputPlayer2;
    private TextView player1points;
    private TextView player2points;
    private TextView isShaking;
    private boolean isPlayer1Turn = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inputPlayer1 = findViewById(R.id.player1Game);
        inputPlayer2 = findViewById(R.id.player2Game);
        player1points = findViewById(R.id.player1points);
        player2points = findViewById(R.id.player2points);
        isShaking = findViewById(R.id.isShaking);

        inputPlayer1.setText(getSpieler1());
        inputPlayer2.setText(getSpieler2());
    }


    public String getSpieler1() {
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        String player1 = sharedPreferences.getString("spieler1", null);
        return player1;
    }


    public String getSpieler2() {
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        String player2 = sharedPreferences.getString("spieler2", null);
        return player2;
    }

    public void savePlayer1Points(int amount) {
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("player1points", amount);
        editor.apply();
    }

    public void savePlayer2Points(int amount) {
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("player2points", amount);
        editor.apply();
    }
}
