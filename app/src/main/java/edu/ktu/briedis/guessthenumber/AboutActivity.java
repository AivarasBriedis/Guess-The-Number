package edu.ktu.briedis.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void startClick(View view) {

        String url;

        if (findViewById(R.id.urlFacebook) == view) {
            url = "https://www.facebook.com/aivaras.briedis";
        } else {
            url = "https://twitter.com/aivarasbriedis";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}
