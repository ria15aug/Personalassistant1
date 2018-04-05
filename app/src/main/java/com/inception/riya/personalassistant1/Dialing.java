package com.inception.riya.personalassistant1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

public class Dialing extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    TextView speech_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialing);

        speech_txt = findViewById(R.id.speech_text);

    }


    public void listen(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "I am listening !!");

        startActivityForResult(i, REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


            String txt = result.get(0);


            speech_txt.setText("Calling  "+txt);

            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:" + txt));
            startActivity(i);

        }
    }

    public void dial(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("Harmeet"));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void icon(View view) {
        finish();
    }

    public void txt(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);


    }
}


