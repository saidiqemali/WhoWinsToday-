package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
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

    }
}