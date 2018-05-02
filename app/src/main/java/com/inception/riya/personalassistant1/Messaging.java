package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        SharedPreferences sp = getSharedPreferences("settings_info", MODE_PRIVATE);

        if (sp.getBoolean("voice_alert", false)) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        if(speech_txt.getText().toString().equals("")) {
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Message you want to send !!");
        }

        else {
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "To whom you want to send !!");

        }

        startActivityForResult(i, REQUEST_CODE);
    }
        else {

            Toast.makeText(Messaging.this , "voice command are inactive " , Toast.LENGTH_SHORT).show();
        }
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



    public void msg(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("sms:A"));
        startActivity(i);

    }


    public void msg_on_whatsapp(View view) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = " ";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }
        }


