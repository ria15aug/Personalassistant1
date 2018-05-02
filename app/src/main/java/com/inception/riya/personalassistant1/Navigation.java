package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public class Navigation extends AppCompatActivity {
    EditText loc1;
    EditText loc2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
         loc1 = findViewById(R.id.location_1);
        loc2 = findViewById(R.id.location_2);
    }

    public void get_route(View view) {

        double latitude1 = 0, longitude1 = 0 , latitude2 = 0 , longitude2 = 0 ;

        String address1 = loc1.getText().toString();

        String address2 = loc2.getText().toString();

        Geocoder geocoder = new Geocoder(Navigation.this);
        List<Address> addresses;
        List<Address> addresses2;
        try {
            addresses = geocoder.getFromLocationName(address1, 1);

            if(addresses.size() > 0) {
                latitude1= addresses.get(0).getLatitude();
                longitude1= addresses.get(0).getLongitude();
            }

            addresses2 = geocoder.getFromLocationName(address2, 1);

            if(addresses2.size() > 0) {
                latitude2= addresses2.get(0).getLatitude();
                longitude2= addresses2.get(0).getLongitude();
            }

            String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+latitude1+","+longitude1+"&daddr="+latitude2+","+longitude2;
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(Intent.createChooser(intent, "Select an application"));


        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void arrow(View view) {finish();
    }

    public void show_traffic(View view) {


        Intent i = new Intent(Navigation.this , ShowTrafiicActivity.class);

        startActivity(i);
    }
}
