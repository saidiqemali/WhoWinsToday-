package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
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
    private TextView isShakingTextView;

    private int randomNumber1 = 0;
    private int randomNumber2 = 0;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private boolean isShaking = false;

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
        isShakingTextView = findViewById(R.id.isShaking);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        updateDatas();
    }

    private void updateDatas() {
        player1GameTextView.setText(player1Name);
        player2GameTextView.setText(player2Name);
        player1PointsTextView.setText(String.valueOf(randomNumber1));
        player2PointsTextView.setText(String.valueOf(randomNumber2));
        isShakingTextView.setText(isShaking ? "Shaking" : "Not Shaking");
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        randomNumber1 = random.nextInt(10) + 1;
        randomNumber2 = random.nextInt(10) + 1;
        randomNumberTextView.setText(String.valueOf(randomNumber1 + " - " + randomNumber2));
    }

    boolean hasShaken = false;

    int repeatIndex = -1;


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!hasShaken) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            double acceleration = Math.sqrt(x * x + y * y + z * z);
            if (acceleration > 30) {
                hasShaken = true;
                isShaking = true; // bool that looks if phone is shaking for later for the vibration
                generateRandomNumbers();
                updateDatas();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isShaking = true; // bool that looks phone is shaking to stop vibration
                        goToFinalActivity();
                        hasShaken = false;
                    }
                }, 3000);
                if (isShaking) {
                    long[] timings = new long[] { 50, 50, 50, 50, 50, 100, 350, 250 };
                    int[] amplitudes = new int[] { 150, 150, 150, 150, 150, 150, 150, 150 };
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitudes, repeatIndex));
                    } // Code von : https://developer.android.com/develop/ui/views/haptics/actuators#java
                }
            }
        }
    }

    private String decleareWinnerAndLoser() {
        if (randomNumber1 > randomNumber2) {
            return player1Name;
        } else if (randomNumber2 > randomNumber1) {
            return player2Name;
        } else {
            return "It's a Draw, you can play again";
        }
    }

    private void goToFinalActivity() {
        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("Winner-Loser", decleareWinnerAndLoser());
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
