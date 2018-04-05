package com.inception.riya.personalassistant1;

import android.provider.ContactsContract;

/**
 * Created by riya on 3/20/2018.
 */

public class profiledata {
    public String name,address, gender;
    public int age;
    profiledata()
    {

    }
    profiledata(String name, String address, String gender, int age){
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;

    }
}
