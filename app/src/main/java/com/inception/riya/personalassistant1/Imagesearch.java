package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Imagesearch extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    TextView speech_txt;
    private EditText image_name_et ;

    private ImageView search_image_btn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagesearch);
        speech_txt = findViewById(R.id.speech_text);
        image_name_et = findViewById(R.id.image_name_et);
        search_image_btn = findViewById(R.id.image_search_btn);
    }

    public void arrow(View view) {
        finish();
        image_name_et = findViewById(R.id.image_name_et);
        search_image_btn = findViewById(R.id.image_search_btn);


    }
        public void listen (View view){
            search_image_btn.setVisibility(View.INVISIBLE);
            image_name_et.setVisibility(View.INVISIBLE);
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "I am listening !!");

            startActivityForResult(i, REQUEST_CODE);



        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
                TextView speech_txt = findViewById(R.id.speech_text);
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String txt = result.get(0);
                speech_txt.setText(txt);
                String name_to_search = image_name_et.getText().toString();

                try {
                    String image_query = URLEncoder.encode(name_to_search , "UTF-8");

                    Uri uri = Uri.parse("https ://www.google.com/search?q="+image_query);

                    Intent intent = new Intent(Intent.ACTION_VIEW , uri);

                    startActivity(intent);


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }

    public void txt(View view) {
        search_image_btn.setVisibility(View.VISIBLE);
        image_name_et.setVisibility(View.VISIBLE);


    }

    public void gallery(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + "/sdcard/test.jpg"), "image/*");
        startActivity(intent);
    }

    public void google(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com/")));
    }

    public void search_image(View view) {

            String name_to_search = image_name_et.getText().toString();

        try {
            String image_query = URLEncoder.encode(name_to_search , "UTF-8");

            Uri uri = Uri.parse("https ://www.google.com/search?q="+image_query);

            Intent intent = new Intent(Intent.ACTION_VIEW , uri);

            startActivity(intent);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}

