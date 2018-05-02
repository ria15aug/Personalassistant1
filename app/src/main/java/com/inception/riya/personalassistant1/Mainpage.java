package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Mainpage extends AppCompatActivity {
ImageView name;
Animation uptodown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        name= findViewById(R.id.name_logo);
        uptodown= AnimationUtils.loadAnimation(Mainpage.this, R.anim.uptodown);
        name.setAnimation(uptodown);
        Handler h = new Handler();



        h.postDelayed(new Runnable() {

            @Override

            public void run() {




                    Intent i = new Intent(Mainpage.this, MainActivity.class);



                    startActivity(i);



                    finish();



            }

        }, 5000);


    }
}
