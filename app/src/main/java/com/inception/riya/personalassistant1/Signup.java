package com.inception.riya.personalassistant1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void next(View view) {

        EditText email_et = findViewById(R.id.email_id);

        EditText password_et = findViewById(R.id.password_id);

        final String email = email_et.getText().toString();


        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {



        }

        else {

            Toast.makeText(Signup.this , "invalid email syntax" , Toast.LENGTH_SHORT).show();

            return;

        }





        String password = password_et.getText().toString();



        if(password.length() >= 8 && password.length() < 33 )

        {



        }

        else {

            Toast.makeText(Signup.this , "password must be between 8 to 32 characters" , Toast.LENGTH_SHORT).show();

            return;

        }



        final ProgressDialog progress_bar = new ProgressDialog(Signup.this);





        progress_bar.setTitle("Please Wait");



        progress_bar.setMessage("Creating account...");



        progress_bar.show();



        FirebaseAuth f_auth = FirebaseAuth.getInstance();







        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {





                progress_bar.hide();



                if( task.isSuccessful() )

                {

                    Toast.makeText(Signup.this , "done" , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Signup.this,Userprofile.class);
                    i.putExtra("email", email);
                    startActivity(i);


                }

                else {

                    Toast.makeText(Signup.this , "error try again" , Toast.LENGTH_SHORT).show();



                }



            }

        };









        f_auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(listener);

    }

}