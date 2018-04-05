package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Getweather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getweather);
    }

    public void Getweather(View view) {
        Intent i = new Intent(Getweather.this,Weather.class);
        startActivity(i);
    }
}
