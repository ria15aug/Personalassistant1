package com.inception.riya.personalassistant1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch call_alerts , voice_alerts ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        call_alerts = findViewById(R.id.call_alerts);

        voice_alerts = findViewById(R.id.voice_alerts);

        SharedPreferences sp = getSharedPreferences("settings_info" , MODE_PRIVATE);

        if(sp.getBoolean("voice_alert" , false))
        {
            voice_alerts.setChecked(true);
        }

        if(sp.getBoolean("call_alert" , false))
        {
            call_alerts.setChecked(true);
        }

        call_alerts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    SharedPreferences.Editor sp = getSharedPreferences("settings_info" , MODE_PRIVATE).edit();

                    sp.putBoolean("call_alert" ,true).commit();
                }

                else {
                    SharedPreferences.Editor sp = getSharedPreferences("settings_info" , MODE_PRIVATE).edit();

                    sp.putBoolean("call_alert" ,false).commit();
                }
            }
        });

        voice_alerts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    SharedPreferences.Editor sp = getSharedPreferences("settings_info" , MODE_PRIVATE).edit();

                    sp.putBoolean("voice_alert" ,true).commit();
                }

                else {
                    SharedPreferences.Editor sp = getSharedPreferences("settings_info" , MODE_PRIVATE).edit();

                    sp.putBoolean("voice_alert" ,false).commit();
                }
            }
        });

    }

    public void arrow(View view) {
        finish();
    }
}
