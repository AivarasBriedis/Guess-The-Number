package edu.ktu.briedis.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class GameOverActivity extends AppCompatActivity {

    TextView text;
    DBHelper mydb;
    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("win", false);

        mydb = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        String name = prefs.getString("playerName", "name");
        int age = prefs.getInt("playerAge", 1);
        int difficulty = prefs.getInt("difficulty", 0);

        if (win) {
            setContentView(R.layout.activity_game_win);
            text = findViewById(R.id.win);
            text.setText("Congratulation you won!");
            int score = intent.getIntExtra("score", 0);
            //insert
            if(mydb.insertData(name,age,difficulty,score)) {
            } else {
            }

        } else {
            setContentView(R.layout.activity_game_over);
            text = findViewById(R.id.win);
            text.setText("You lost try again!");
        }

    }

    public void startClick(View view) {

        if (findViewById(R.id.start_game_btnn) == view) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
