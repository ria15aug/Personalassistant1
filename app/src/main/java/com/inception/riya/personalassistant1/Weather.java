package com.inception.riya.personalassistant1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Weather extends AppCompatActivity {
    TextView sunrise_et;
    TextView wind_et;
    TextView temp_et;
    TextView sunset_et;
    TextView humidity_et;
    TextView pressure_et;
    TextView weather_et;
    TextView nineteen_et;
    TextView place_et;
    ImageView cloud_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        sunrise_et = findViewById(R.id.sunrise_et);
         wind_et = findViewById(R.id.wind_et);
        temp_et = findViewById(R.id.temp_et);
        sunset_et = findViewById(R.id.sunset_et);
         humidity_et = findViewById(R.id.humidity_et);
        pressure_et = findViewById(R.id.pressure_et);
         weather_et = findViewById(R.id.weather_et);
         nineteen_et = findViewById(R.id.nineteen_et);
        place_et= findViewById(R.id.place_et);
        cloud_et = findViewById(R.id.cloud_et);


        place_et.setText(getIntent().getStringExtra("city"));

        get_weather();

    }

    public void get_weather() {

        String city_name = getIntent().getStringExtra("city");

        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city_name+"&APPID=8e4d35f79341370b8e448fa3f5d0433e";

         //String url = "http://api.openweathermap.org/data/2.5/weather?lat=31.6&lon=74.87&APPID=8e4d35f79341370b8e448fa3f5d0433e";



        JSONObject jsonObject = new JSONObject();
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray j_array =  response.getJSONArray("weather");
                    JSONObject j_object = j_array.getJSONObject(0);
                    String weather =  j_object.getString("main");

                    weather_et.setText(weather);

                    if(weather.equals("Cloudy"))
                    {
                        cloud_et.setImageDrawable(getResources().getDrawable(R.drawable.cloudyw));
                    }

                    if(weather.equals("Rain"))
                    {
                        cloud_et.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    }

                    if(weather.equals("sunny"))
                    {
                        cloud_et.setImageDrawable(getResources().getDrawable(R.drawable.sunny));

                    }

                    JSONObject temp_json_object = response.getJSONObject("main");
                    Double temp = temp_json_object.getDouble("temp");
                    temp = temp - 275;

                    String s = String.format("%.2f", temp);
                    temp_et.setText(s+" C");
                    nineteen_et.setText(s);
                    int humidity =  temp_json_object.getInt("humidity");

                    humidity_et.setText(String.valueOf(humidity)+ " %");
                    int pressure =temp_json_object.getInt("pressure");

                    pressure_et.setText(String.valueOf(pressure)+" mb");
                    JSONObject wind_object =  response.getJSONObject("wind");
                    Double wind = wind_object.getDouble("speed");

                    wind_et.setText(String.valueOf(wind)+"km/h");

                    long sunrise =  response.getJSONObject("sys").getLong("sunrise");


                    Date date = new java.util.Date(sunrise*1000L);
                    // the format of your date
                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm aa");
                     // give a timezone reference for formatting (see comment at the bottom)
                    TimeZone utc = TimeZone.getTimeZone("etc/UTC");
                    sdf.setTimeZone(utc);
                    String formattedDate = sdf.format(date);


                    sunrise_et.setText(formattedDate);

                    long sunset = response.getJSONObject("sys").getLong("sunset");

                    Date date2 = new java.util.Date(sunset*1000L);
                    // the format of your date
                    SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("hh:mm aa");
                    // give a timezone reference for formatting (see comment at the bottom)
                    TimeZone utc2 = TimeZone.getTimeZone("etc/UTC");
                    sdf.setTimeZone(utc2);
                    String formattedDate2 = sdf2.format(date2);

                    sunset_et.setText(formattedDate2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,jsonObject,listener,errorListener);
        RequestQueue queue = Volley.newRequestQueue(Weather.this);
        queue.add(jsonObjectRequest);



    }

    public void arrow(View view) {
        finish();
    }

    public void change(View view) {finish();
    }
}