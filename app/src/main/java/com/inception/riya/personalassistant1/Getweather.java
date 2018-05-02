package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;

public class Getweather extends AppCompatActivity {
    private final int REQUEST_CODE = 200;
    EditText location;

    private FusedLocationProviderClient mFusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getweather);
        location = findViewById(R.id.loc);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



    }


    public void speech(View view) {
        SharedPreferences sp = getSharedPreferences("settings_info", MODE_PRIVATE);

        if (sp.getBoolean("voice_alert", false)) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "I am listening !!");

        startActivityForResult(i, REQUEST_CODE);
    }
     else {

        Toast.makeText(Getweather.this , "voice command are inactive " , Toast.LENGTH_SHORT).show();
    }
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String txt = result.get(0);
            location.setText(txt);

        }

    }


    public void Getweather(View view) {
        Intent i = new Intent(Getweather.this, Weather.class);
        i.putExtra("city", location.getText().toString());
        startActivity(i);

    }

    public void get_current_location(View view) {


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        System.out.println("location called **********************");

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location loc) {
                        // Got last known location. In some rare situations this can be null.
                        if (loc != null) {
                            // Logic to handle location object
                            System.out.println("location found **********************");

                            Geocoder geocoder = new Geocoder(Getweather.this);

                            try {
                              String locality =  geocoder.getFromLocation(loc.getLatitude() , loc.getLongitude() , 1).get(0).getLocality();

                              location.setText(locality);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


    }






}
