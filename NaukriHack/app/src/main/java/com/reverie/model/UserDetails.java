package com.reverie.model;

/**
 * Created by NayanJyoti on 02-09-2016.
 */

public class UserDetails {
    public String name;
    public String designation;
    public String location;
    public String experience;
    public String ctc;
    public String email;
    public String resumeHeadline;
    public String keySkill;
    public EmploymentDetails employmentDetailses;
    public ITSkils[] itSkilses;
    public EducationDetails[] educationDetailses;
    public WorkDetails workDetails;
    public PersonelDetails personelDetailses;
    public LanguageKnown[] languageKnowns;
    public static UserDetails userDetails = new UserDetails();
    private UserDetails(){
        name = "Monoranjan Bora";
        designation = "Software Developer";
        location = "Bengaluru / Bangalore, India";
        experience = "1 Year 8 Month";
        ctc = "5 Lacs 50 Thousands";
        email = "abcxyz@gmail.com";
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
}
