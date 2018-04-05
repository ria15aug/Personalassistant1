package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
    }

    public void save(View view) {
        Intent i = new Intent(Userprofile.this,Menu.class);
        startActivity(i);

    }

    public void icon(View view) {
        finish();
    }

}

