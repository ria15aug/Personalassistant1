package com.inception.riya.personalassistant1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reminders extends AppCompatActivity {

    EditText title;

    EditText date;

    EditText day;

    EditText starttime;

    EditText endtime;

    String start_date , start_time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        title = findViewById(R.id.title_et);
        date = findViewById(R.id.date_et);
        starttime = findViewById(R.id.startdate_et);

        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();



                TimePickerDialog time_dialog = new TimePickerDialog(Reminders.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        starttime.setText(String.valueOf(i)+":"+String.valueOf(i1));

                        start_time = String.valueOf(i)+":"+String.valueOf(i1);
                    }
                } , c.get(Calendar.HOUR) , c.get(Calendar.MINUTE) , true);

                time_dialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();


                DatePickerDialog datePickerDialog = new DatePickerDialog(Reminders.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


                        date.setText(String.valueOf(i)+"-"+String.valueOf(i1+1)+"-"+String.valueOf(i2));

                        start_date = String.valueOf(i)+"-"+String.valueOf(i1)+"-"+String.valueOf(i2);
                    }
                } , c.get(Calendar.YEAR) , c.get(Calendar.MONTH) , c.get(Calendar.DAY_OF_WEEK));

                datePickerDialog.show();
            }
        });

    }


    public void arrow(View view) {
        finish();
    }

    public void set_reminder(View view) {

      String title_s =    title.getText().toString();

      String[] selected_date = start_date.split("-");

      String[] selected_time = start_time.split(":");

        Calendar cal = Calendar.getInstance();

        cal.set(Integer.parseInt(selected_date[0]),Integer.parseInt( selected_date[1] ), Integer.parseInt(selected_date[2]),
                Integer.parseInt( selected_time[0]), Integer.parseInt(selected_time[1] ), 0);
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", false);
        intent.putExtra("rrule", "FREQ=DAILY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", title_s);
        startActivity(intent);
    }
}
