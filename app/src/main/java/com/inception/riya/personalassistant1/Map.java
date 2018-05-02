package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void arrow(View view) {
        finish();
    }


    public void search_location(View view) {
    }

    public void listen(View view) {
        SharedPreferences sp = getSharedPreferences("settings_info", MODE_PRIVATE);

        if (sp.getBoolean("voice_alert", false)) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=an+address+city"));
        } else {

            Toast.makeText(Map.this, "voice command are inactive ", Toast.LENGTH_SHORT).show();
        }

    }
    public void txt(View view) {
    }

    public void show_nearby(View view) {

        Intent i = new Intent(Map.this , Nearbyplaces.class);

        startActivity(i);
    }

    public void search_address(View view) {

        Intent i = new Intent(Map.this , Searchaddresses.class);

        startActivity(i);
    }
}
