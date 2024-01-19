package com.example.myapplication;// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private EditText player1EditText;
    private EditText player2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1EditText = findViewById(R.id.player1);
        player2EditText = findViewById(R.id.player2);

        Button shakeButton = findViewById(R.id.shakeButton);
        shakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSettingsActivity();
            }
        });
    }

    private void startSettingsActivity() {
        String player1Name = player1EditText.getText().toString();
        String player2Name = player2EditText.getText().toString();

        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("player1Name", player1Name);
        intent.putExtra("player2Name", player2Name);
        startActivity(intent);
    }
}
