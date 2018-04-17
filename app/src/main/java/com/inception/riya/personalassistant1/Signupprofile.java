package com.inception.riya.personalassistant1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Signupprofile extends AppCompatActivity {
    EditText name ,address,  age,height,bloodgroup,eyesight;

    RadioGroup gender_radio_group ;

    RadioButton disability_radio_btn , male_radio_btn , female_radio_btn , others_radio_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupprofile);

        name = findViewById(R.id.name_et);
        address = findViewById(R.id.address_et);
        age = findViewById(R.id.age_et);
        height = findViewById(R.id.height_et);
        bloodgroup = findViewById(R.id.blood_et);
        eyesight = findViewById(R.id.eyesight_et);
        disability_radio_btn = findViewById(R.id.disability_radio);
        gender_radio_group = findViewById(R.id.gender_radio_group);

        male_radio_btn = findViewById(R.id.male);
        female_radio_btn = findViewById(R.id.female);
        others_radio_btn = findViewById(R.id.others);


        read_profile_data();
    }

    public void icon(View view) {
        finish();
    }



    public void read_profile_data()

    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();



        ValueEventListener event_listener = new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {



                profiledata recieved_data = dataSnapshot.getValue(profiledata.class);



                name.setText(recieved_data.name);
                address.setText(recieved_data.address);
                age.setText(String.valueOf(recieved_data.age));
                height.setText(String.valueOf(recieved_data.height));
                bloodgroup.setText(recieved_data.bloodgroup);
                eyesight.setText(recieved_data.eyesight);

                if(recieved_data.disability.equals("yes"))
                {
                    disability_radio_btn.setChecked(true);
                }



                if(recieved_data.gender.equals("male"))
                {
                    male_radio_btn.setChecked(true);
                }
                if(recieved_data.gender.equals("female"))
                {
                    female_radio_btn.setChecked(true);
                }
                if(recieved_data.gender.equals("others"))
                {
                    others_radio_btn.setChecked(true);
                }





                System.out.println( recieved_data.name );

                System.out.println( recieved_data.address );

                System.out.println( recieved_data.age );

                System.out.println( recieved_data.height );

                System.out.println( recieved_data.bloodgroup );

                System.out.println( recieved_data.eyesight );

                System.out.println(recieved_data.disability);

                System.out.println(recieved_data.gender);



            }



            @Override

            public void onCancelled(DatabaseError databaseError) {



            }

        };


        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail();

        database.getReference().child("user").child(email.replace("." ,"")).addValueEventListener(event_listener);



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



        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail();

        database.child("user").child(email.replace(".","")).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        });

    }



}
