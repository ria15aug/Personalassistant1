package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Settings1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings1);
    }

    public void settings1(View view) {
        Intent i = new Intent(Settings1.this,Settings.class);
        startActivity(i);
    }

    public void arrow(View view) {
        finish();
    }
}
