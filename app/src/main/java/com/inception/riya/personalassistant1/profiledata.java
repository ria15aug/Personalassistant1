package com.inception.riya.personalassistant1;

import android.provider.ContactsContract;

/**
 * Created by riya on 3/20/2018.
 */

public class profiledata {

    public String name,address,bloodgroup,eyesight,gender , disability,other_disability ;
    public int age,height;

    profiledata()
    {

    }
    profiledata(String name, String address, int height, int age, String bloodgroup, String eyesight, String gender , String disability,String other_disability ){
        this.name = name;
        this.address = address;
        this.age = age;
        this.height = height;
        this.bloodgroup = bloodgroup;
        this.eyesight = eyesight;
        this.gender = gender;
        this.disability = disability ;
        this.other_disability = other_disability ;

    }
}
