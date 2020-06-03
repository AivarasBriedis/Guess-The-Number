package edu.ktu.briedis.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    MediaPlayer mpWrong, mpCorrect;

    private int minNumber = 0;
    private int maxNumber = 2;
    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    private int randomNumber;

    private int maxTurns = 7;
    private int currentTurn = 0;

    private int result = 0;

    private TextView numberRangeText;
    private TextView resultText;
    private TextView turnsText;

    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mpWrong = MediaPlayer.create(this, R.raw.wrong);
        mpCorrect = MediaPlayer.create(this, R.raw.correct);

        numberRangeText = findViewById(R.id.numberRange);
        resultText = findViewById(R.id.resultText);
        turnsText = findViewById(R.id.turnsText2);
        numberField = findViewById(R.id.numberField);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);

        int difficulty = prefs.getInt("difficulty", 0);

        if (difficulty == 0) {
            maxNumber = 10;
            maxTurns = 3;
        } else if (difficulty == 1) {
            maxNumber = 100;
            maxTurns = 5;
        } else if (difficulty == 2) {
            maxNumber = 1000;
            maxTurns = 20;
        } else {
            maxNumber = 1000000;
            maxTurns = 50;
        }

        updateText(0);

        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber) + minNumber;

    }

    boolean checkLowerOrHigher (int x, int y) {
        boolean answer = y > x;
        return answer;
    }

    private void updateText(int guessedNumber) {
        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber, maxNumber));

        if (currentTurn!=0) {
            if (checkLowerOrHigher(guessedNumber, randomNumber)) {
                resultText.setText("Your guessing number is higher!");
            } else if (checkLowerOrHigher(randomNumber, guessedNumber)) {
                resultText.setText("Your guessing number is lower!");
            } else {
                resultText.setText("You won!");
            }
        }

        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn, maxTurns));
    }

    public void guessClick(View view) {

        if (numberField.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter a number", Toast.LENGTH_SHORT).show();
            return;
        } else {
            currentTurn++;

            int guessedNumber = Integer.parseInt(numberField.getText().toString());
            int result = 0;

            SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
            boolean sound = prefs.getBoolean("sound", true);

            if (randomNumber > guessedNumber) {
                result = -1;
                if (sound == true) {
                    mpWrong.start();
                }
            } else if (randomNumber < guessedNumber) {
                result = 1;
                if (sound == true) {
                    mpWrong.start();
                }
            }

            if (result == 0 && sound == true) {
                mpCorrect.start();
            }

            if (currentTurn >= maxTurns && result != 0) {

                //lose
                Intent intent = new Intent(this, GameOverActivity.class);
                intent.putExtra("guessedNumber", guessedNumber);
                intent.putExtra("randomNumber", randomNumber);
                intent.putExtra("win", false);
                startActivity(intent);
                finish();

            } else if (result == 0) {

                //win
                Intent intent = new Intent(this, GameOverActivity.class);
                intent.putExtra("guessedNumber", guessedNumber);
                intent.putExtra("randomNumber", randomNumber);
                intent.putExtra("score", currentTurn);
                intent.putExtra("win", true);
                startActivity(intent);
                finish();
            }

            updateText(guessedNumber);
        }
    }

}
