package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

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
        Intent i = new Intent(Menu.this,Signupprofile.class);
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

    public void music(View view) {
        Intent i = new Intent(Menu.this,music.class);
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
        Intent i = new Intent(Menu.this,Settings.class);
        startActivity(i);
    }

    public void logout(View view) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signOut();

        Intent i = new Intent(Menu.this , MainActivity.class);

        startActivity(i);

        finish();
    }

    public void set_reminder(View view) {

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", false);
        intent.putExtra("rrule", "FREQ=DAILY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
    }
}
