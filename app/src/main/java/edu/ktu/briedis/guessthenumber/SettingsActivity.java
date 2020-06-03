package edu.ktu.briedis.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);

        String name = prefs.getString("playerName", "name");
        int age = prefs.getInt("playerAge", 1);
        int difficulty = prefs.getInt("difficulty", 0);
        boolean sound = prefs.getBoolean("sound", true);

        EditText nameField = findViewById(R.id.playerName);
        EditText ageField = findViewById(R.id.playerAge);
        Spinner spinner = findViewById(R.id.difficultySpin);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        nameField.setText(name);
        ageField.setText(Integer.toString(age));
        spinner.setSelection(difficulty);
        soundSwitch.setChecked(sound);

    }

    public void saveSettings(View v) {
        EditText playerNameField = findViewById(R.id.playerName);
        EditText playerAgeField = findViewById(R.id.playerAge);
        Spinner spinner = findViewById(R.id.difficultySpin);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        String playerName = playerNameField.getText().toString();
        int playerAge = Integer.parseInt(playerAgeField.getText().toString());
        int difficulty = spinner.getSelectedItemPosition();
        boolean sound = soundSwitch.isChecked();

        SharedPreferences.Editor prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();

        prefs.putString("playerName", playerName);
        prefs.putInt("playerAge", playerAge);
        prefs.putInt("difficulty", difficulty);
        prefs.putBoolean("sound", sound);

        prefs.apply();

        finish();
    }
}
