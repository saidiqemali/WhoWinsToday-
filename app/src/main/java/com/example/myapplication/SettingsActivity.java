package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SettingsActivity extends AppCompatActivity implements SensorEventListener {

    private String player1Name;
    private String player2Name;

    private TextView player1GameTextView;
    private TextView player2GameTextView;
    private TextView player1PointsTextView;
    private TextView player2PointsTextView;
    private TextView randomNumberTextView;
    private TextView currentPlayerTextView;
    private TextView isShakingTextView;

    private int player1Points = 0;
    private int player2Points = 0;
    private int randomNumber1 = 0;
    private int randomNumber2 = 0;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private boolean isShaking = false;
    private int shakeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("player1Name");
        player2Name = intent.getStringExtra("player2Name");

        player1GameTextView = findViewById(R.id.player1Game);
        player2GameTextView = findViewById(R.id.player2Game);
        player1PointsTextView = findViewById(R.id.player1Points);
        player2PointsTextView = findViewById(R.id.player2Points);
        randomNumberTextView = findViewById(R.id.randomNumber);
        currentPlayerTextView = findViewById(R.id.currentPlayer);
        isShakingTextView = findViewById(R.id.isShaking);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        updateUI();
    }

    private void updateUI() {
        player1GameTextView.setText(player1Name);
        player2GameTextView.setText(player2Name);
        player1PointsTextView.setText(String.valueOf(player1Points));
        player2PointsTextView.setText(String.valueOf(player2Points));
        currentPlayerTextView.setText(player1Name);
        isShakingTextView.setText("Not Shaking");
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        randomNumber1 = random.nextInt(10) + 1;
        randomNumber2 = random.nextInt(10) + 1;
        player1PointsTextView.setText(String.valueOf(randomNumber1));
        player2PointsTextView.setText(String.valueOf(randomNumber2));
        randomNumberTextView.setText(String.valueOf(randomNumber1 + " - " + randomNumber2));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        boolean hasShaken = false;

        double acceleration = Math.sqrt(x * x + y * y + z * z);
        if (acceleration > 15) {
            hasShaken = true;
            generateRandomNumbers();
            updateUI();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToFinalActivity();
                }
            }, 3000);
        }
    }

    private void goToFinalActivity() {
        Intent intent = new Intent(this, FinalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
