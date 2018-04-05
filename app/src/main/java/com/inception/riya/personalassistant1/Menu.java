package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void menu(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.click);
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void profile(View view) {
        Intent i = new Intent(Menu.this,Userprofile.class);
        startActivity(i);

    }

    public void home(View view) {

    }

    public void weather(View view){
            Intent i = new Intent(Menu.this,Getweather.class);
            startActivity(i);


    }

    public void dialing(View view) {
        Intent i = new Intent(Menu.this,Dialing.class);
        startActivity(i);
    }

    public void imagesearch(View view) {
        Intent i = new Intent(Menu.this,Imagesearch.class);
        startActivity(i);
    }

    public void messaging(View view) {
        Intent i = new Intent(Menu.this,Messaging.class);
        startActivity(i);
    }

    public void bluetooth(View view) {
        Intent i = new Intent(Menu.this,Bluetooth.class);
        startActivity(i);
    }

    public void map(View view) {
        Intent i = new Intent(Menu.this,Map.class);
        startActivity(i);
    }

    public void navigation(View view) {
        Intent i = new Intent(Menu.this,Navigation.class);
        startActivity(i);
    }

    public void reminder(View view) {
        Intent i = new Intent(Menu.this,Reminders.class);
        startActivity(i);
    }

    public void settings(View view) {
        Intent i = new Intent(Menu.this,Settings1.class);
        startActivity(i);
    }
}
