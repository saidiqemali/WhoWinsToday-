package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputPlayer1;
    private EditText inputPlayer2;
    private Button shakeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputPlayer1 = findViewById(R.id.player1);
        inputPlayer2 = findViewById(R.id.player2);
        shakeButton = findViewById(R.id.shakeButton);

        shakeButton.setOnClickListener(new View.OnClickListener() { // Namen der Spieler in SharedPreferences speichern.
            public void onClick(View view) {
                savePlayer1(inputPlayer1.getText().toString());
                savePlayer2(inputPlayer2.getText().toString());
            }
        });
    }


    // Diese 2 speichern Namen in den SharedPreferences für Player1 und Player2
    public void savePlayer1(String name){
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Player 1", name);
        editor.apply();
    }

    public void savePlayer2(String name){
        SharedPreferences sharedPreferences = getSharedPreferences("Speicher", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Player 2", name);
        editor.apply();
    }
}