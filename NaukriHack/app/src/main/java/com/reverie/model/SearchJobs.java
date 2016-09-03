package com.reverie.model;

/**
 * Created by NayanJyoti on 03-09-2016.
 */
public class SearchJobs {
    public String jobName;
    public String jobOrg;
    public String jobLoc;
    public String jobKeySkills;
    private static String jobsData[][] = new String[10][5];
    static {
        jobsData[0][0] = "Software Engineer";
        jobsData[0][1] = "BangBit Technologies Private Limited";
        jobsData[0][2] = "1 - 3 yrs";
        jobsData[0][3] = "Bengaluru/Bangalore";
        jobsData[0][4] = "ASP.Net, MVC, Javascript, SQL Server, JQuery, ASP .Net, Microsoft Technologies, Software, Development";

        jobsData[1][0] = "Software Programmer / Software Developer";
        jobsData[1][1] = "BangBit Technologies Private Limited";
        jobsData[1][2] = "2-4 yrs";
        jobsData[1][3] = "Bengaluru/Bangalore , Mumbai , Chennai , Pune , Hyderabad / Secunderabad , Delhi/NCR(National Capital Region) , Ahmedabad , Kolkata , All India";
        jobsData[1][4] = "PHP, .NET, dot net, vb, Fresher, Java, ASP.Net, C#, SQL Server, HTML, J2Ee";

        jobsData[2][0] = "Software Developer/ Senior Software Developer - Java/ Restapi";
        jobsData[2][1] = "Horizon Business Services Pvt Ltd";
        jobsData[2][2] = "2-5 yrs";
        jobsData[2][3] = "Bengaluru, Hyderabad";
        jobsData[2][4] = "Java, RESTAPI, Spring MVC, MEAN Stack, Core Java programming, programming";

        jobsData[3][0] = "Job Opportunity for Software Developer- Autosar/ UDS for Bangalore";
        jobsData[3][1] = "PeopleStrong HR Services Pvt. Ltd.";
        jobsData[3][2] = "2-3 yrs";
        jobsData[3][3] = "Bengaluru";
        jobsData[3][4] = "healthcare, development, travel, consulting, managed services, outsourcing";

        jobsData[4][0] = "Embedded Software Developers";
        jobsData[4][1] = "Fore Brain Technologies Private Limited";
        jobsData[4][2] = "2-4 yrs";
        jobsData[4][3] = "Bengaluru, Noida, Pune";
        jobsData[4][4] = "Embedded Software, Embedded application, software  development";

        jobsData[5][0] = "Software Programmer / Software Developer";
        jobsData[5][1] = "Axis Jobs Private Limited";
        jobsData[5][2] = "0-4 yrs";
        jobsData[5][3] = "Bengaluru, Mumbai, Chennai, Pune, Hyderabad, Delhi NCR, Ahmedabad, Kolkata, All India";
        jobsData[5][4] = "PHP, .NET, dot net, vb, Fresher, Java, ASP.Net, C#, SQL Server, HTML, J2Ee";
    }
    public SearchJobs(){

    }
}
