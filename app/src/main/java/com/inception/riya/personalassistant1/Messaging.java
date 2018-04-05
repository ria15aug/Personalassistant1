package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Messaging extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    TextView speech_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        speech_txt = findViewById(R.id.speech_text);
    }

    public void speak(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        if(speech_txt.getText().toString().equals("")) {
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Message you want to send !!");
        }

        else {
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "To whom you want to send !!");

        }

        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String txt = result.get(0);

            if(speech_txt.getText().toString().equals(""))
            {
                speech_txt.setText(txt);

            }
            else {
                String number = result.get(0);
                Uri uri = Uri.parse("smsto:"+number);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", speech_txt.getText().toString());
                startActivity(it);

            }



        }
    }


    @Override
    protected void onResume() {
        super.onResume();

       // speech_txt.setText("gdd");

    }

    public void arrow(View view) {
        finish();
    }

    public void txt(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("sms:"));
        startActivity(i);
    }
}
