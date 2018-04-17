package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Userprofile extends AppCompatActivity {

    EditText name ,address,  age,height,bloodgroup,eyesight;

    RadioGroup gender_radio_group ;

    RadioButton disability_radio_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
       // read_profile_data();

     name = findViewById(R.id.name_et);
     address = findViewById(R.id.address_et);
     age = findViewById(R.id.age_et);
     height = findViewById(R.id.height_et);
     bloodgroup = findViewById(R.id.blood_et);
     eyesight = findViewById(R.id.eyesight_et);

     disability_radio_btn = findViewById(R.id.disability_radio);

     gender_radio_group = findViewById(R.id.gender_radio_group);
    }




    public void save(View view) {


       String name_s =  name.getText().toString();
       String address_s = address.getText().toString();
        String age_s = age.getText().toString();
        String height_s = height.getText().toString();
        String bloodgroup_s = bloodgroup.getText().toString();
        String eyesight_s = eyesight.getText().toString();


        RadioButton selected_radio_button = findViewById(gender_radio_group.getCheckedRadioButtonId());

        String gender =selected_radio_button.getText().toString();

        String disabilty ;

        if(disability_radio_btn.isChecked())
        {
            disabilty = "yes";
        }

        else {
            disabilty = "no";
        }

        profiledata data = new profiledata(name_s , address_s, Integer.parseInt(age_s) ,Integer.parseInt(height_s),bloodgroup_s,eyesight_s , gender , disabilty);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();



        String email = getIntent().getStringExtra("email");

        email = email.replace(".","");

        database.child("user").child(email).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Intent i = new Intent(Userprofile.this , MainActivity.class);

                    startActivity(i);
                }
            }
        });

    }






    public void icon(View view) {


    }
}



