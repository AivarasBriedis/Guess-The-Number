package edu.ktu.briedis.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startClick(View view) {

        if (findViewById(R.id.start_game_btn) == view) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else if (findViewById(R.id.results_btn) == view) {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        } else if (findViewById(R.id.settings_btn) == view) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

    }
}
