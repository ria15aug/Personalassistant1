package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class music extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    TextView speech_txt;

    private EditText song_name_et ;

    private ImageView search_song_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        speech_txt = findViewById(R.id.speech_text);

        song_name_et = findViewById(R.id.song_name_et);
        search_song_btn = findViewById(R.id.song_search_btn);

    }
    public void listen(View view) {

        SharedPreferences sp = getSharedPreferences("settings_info", MODE_PRIVATE);

        if (sp.getBoolean("voice_alert", false)) {

            search_song_btn.setVisibility(View.INVISIBLE);
            song_name_et.setVisibility(View.INVISIBLE);

            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "I am listening !!");

            startActivityForResult(i, REQUEST_CODE);
        }

        else {

            Toast.makeText(music.this , "voice command are inactive " , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            TextView speech_txt = findViewById(R.id.speech_text);
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String txt = result.get(0);


            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/results?search_query=" + txt));

            startActivity(webIntent);
        }
    }


    public void icon(View view) {
        finish();
    }

    public void txt(View view) {

        search_song_btn.setVisibility(View.VISIBLE);
        song_name_et.setVisibility(View.VISIBLE);


    }

    public void song_name_et(View view) {

        String song_name = song_name_et.getText().toString();

        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=" + song_name));

        startActivity(webIntent);

    }

    public void music(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
        startActivity(intent);
    }

    public void you_tube(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/**YOURCHANNEL**"));
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }
}
