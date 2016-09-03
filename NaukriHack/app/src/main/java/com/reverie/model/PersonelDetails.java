package com.reverie.model;

/**
 * Created by NayanJyoti on 02-09-2016.
 */
public class PersonelDetails {
    public String dateOfBirth;
    public String gender;
    public String homeTown;
    public int pin;
    public String maritalStatus;
    public String permanentAddress;
    public PersonelDetails(){
        dateOfBirth = "Jun 05, 1990";
        gender = "Male";
        homeTown = "Guwahati";
        pin = 789456;
        maritalStatus = "Single";
        permanentAddress = "Guwahati, Assam";
    }
}
