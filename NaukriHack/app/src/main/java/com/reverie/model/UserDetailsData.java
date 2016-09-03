package com.reverie.model;

import java.util.ArrayList;

/**
 * Created by NayanJyoti on 02-09-2016.
 */

public class UserDetailsData {
    public String name;
    public String designation;
    public String location;
    public String experience;
    public String ctc;
    public String email;
    public String phone;
    public String resumeHeadline;
    public String keySkill;
    public EmploymentDetails employmentDetailses;
    public ITSkils[] itSkilses;
    public EducationDetails[] educationDetailses;
    public WorkDetails workDetails;
    public PersonelDetails personelDetailses;
    public LanguageKnown[] languageKnowns;
    public static UserDetailsData userDetails = new UserDetailsData();

    private UserDetailsData(){
        name = "Monoranjan Bora";
        designation = "Software Developer";
        location = "Bengaluru / Bangalore, India";
        experience = "1 Year 8 Month";
        ctc = "5 Lacs 50 Thousands";
        email = "abcxyz@gmail.com";
        phone = "9876543210";
        resumeHeadline ="Android and Core Java Developer with 1.5 year experience.";
        keySkill = "Android, Core Java,JSON, JSP, SQLite, C++, DBMS, C";
        employmentDetailses = new EmploymentDetails();
        itSkilses = new ITSkils[2];
        ITSkils skils = new ITSkils("Java","2 years 0 Months","2016");
        itSkilses[0] = skils;
        skils = new ITSkils("Android","1 years 0 Months","2015");
        itSkilses[1] = skils;
        educationDetailses = new EducationDetails[2];
        EducationDetails edu =  new EducationDetails("BCA","Cotton College",2012);
        educationDetailses[0] = edu;
        edu =  new EducationDetails("MCA","Tezpur University",2015);
        educationDetailses[1] = edu;
        workDetails = new WorkDetails();
        personelDetailses = new PersonelDetails();
        languageKnowns = new LanguageKnown[3];
        LanguageKnown languageKnown = new LanguageKnown("Hindi",false,false,true);
        languageKnowns[0] = languageKnown;
        languageKnown = new LanguageKnown("English",true,true,true);
        languageKnowns[1] = languageKnown;
        languageKnown = new LanguageKnown("Englis",true,true,true);
        languageKnowns[2] = languageKnown;
    }

    public ArrayList<String> getUserDetailsStrings() {
        ArrayList<String> userDetailsStrings = new ArrayList<>();
        userDetailsStrings.add("My Profile");
        userDetailsStrings.add(name);
        userDetailsStrings.add(designation);
        userDetailsStrings.add(location);
        userDetailsStrings.add(experience);
        userDetailsStrings.add(ctc);
        userDetailsStrings.add("Contact Details");
        userDetailsStrings.add(email);
        userDetailsStrings.add(phone);
        userDetailsStrings.add("Resume Headline");
        userDetailsStrings.add(resumeHeadline);
        userDetailsStrings.add("Key Skills");
        userDetailsStrings.add(keySkill);
        userDetailsStrings.add("Employment Details");
        userDetailsStrings.add(employmentDetailses.designation);
        userDetailsStrings.add(employmentDetailses.company);
        userDetailsStrings.add(employmentDetailses.workedFromTO);
        userDetailsStrings.add(employmentDetailses.role);
        userDetailsStrings.add("IT Skills");
        userDetailsStrings.add(itSkilses[0].skillName);
        userDetailsStrings.add(itSkilses[0].skillExp);
        userDetailsStrings.add(itSkilses[0].lastUsed);
        userDetailsStrings.add(itSkilses[1].skillName);
        userDetailsStrings.add(itSkilses[1].skillExp);
        userDetailsStrings.add(itSkilses[1].lastUsed);
        userDetailsStrings.add("Education");
        userDetailsStrings.add(educationDetailses[0].course);
        userDetailsStrings.add(educationDetailses[0].institute);
        userDetailsStrings.add(String.valueOf(educationDetailses[0].year));
        userDetailsStrings.add(educationDetailses[1].course);
        userDetailsStrings.add(educationDetailses[1].institute);
        userDetailsStrings.add(String.valueOf(educationDetailses[1].year));
        userDetailsStrings.add("Work Details");
        userDetailsStrings.add("Role");
        userDetailsStrings.add(workDetails.role);
        userDetailsStrings.add("Functional Area");
        userDetailsStrings.add(workDetails.functionalArea);
        userDetailsStrings.add("Industry");
        userDetailsStrings.add(workDetails.industry);
        userDetailsStrings.add("Job Preference");
        userDetailsStrings.add(workDetails.jobPreferences);
        userDetailsStrings.add("Preferred Location");
        userDetailsStrings.add(workDetails.prefferedLocation);
        userDetailsStrings.add("Available to Join");
        userDetailsStrings.add(workDetails.availableToJoin);
        userDetailsStrings.add("Work Authorization");
        userDetailsStrings.add(workDetails.workAuthorization);
        userDetailsStrings.add("Personal Details");
        userDetailsStrings.add("Date of Birth");
        userDetailsStrings.add(personelDetailses.dateOfBirth);
        userDetailsStrings.add("Gender");
        userDetailsStrings.add(personelDetailses.gender);
        userDetailsStrings.add("Hometown");
        userDetailsStrings.add(personelDetailses.homeTown);
        userDetailsStrings.add("Pincode");
        userDetailsStrings.add(String.valueOf(personelDetailses.pin));
        userDetailsStrings.add("Marital Status");
        userDetailsStrings.add(personelDetailses.maritalStatus);
        userDetailsStrings.add("Permanent Address");
        userDetailsStrings.add(personelDetailses.permanentAddress);
        return userDetailsStrings;
    }
}
