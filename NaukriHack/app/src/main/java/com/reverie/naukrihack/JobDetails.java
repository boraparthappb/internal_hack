package com.reverie.naukrihack;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reverie.model.JobDetailsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class JobDetails extends AppCompatActivity {

    private HashMap<String, String> stringEngHindMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        ImageView close = (ImageView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button apply = (Button)findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(UserDetails.isHindi){
            stringEngHindMap = new HashMap<>();
            new LocalizationAsync(this, JobDetailsData.jobDetailsStrings,"english","hindi").execute();
        }
    }class LocalizationAsync extends AsyncTask<Void, Void, String> {
        private HttpURLConnection urlConnection;
        private Activity mContext;
        private String targetLang;
        private String sourceLang;
        ArrayList<String> inputString;

        public LocalizationAsync(Activity context, ArrayList<String> inputString, String sourceLang, String targetLang) {
            this.mContext = context;
            this.sourceLang = sourceLang;
            this.targetLang = targetLang;
            this.inputString = inputString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * @param //type Transliteration, localization or search
         * @return Text in your language
         */


        @Override
        protected String doInBackground(Void... args) {
            //TODO check network connection here
            String localizationUrl = "http://beta.auth.revup.reverieinc.com/apiman-gateway/NayanCalendarTechnology/localization/1.0?";
            String api_key = "gmbhXL3UV1VLPnt1CTfeVKJpLN8Bt8N9d5fQ",
                    app_id = "calender.rev.com.activity";
            StringBuilder urlAppend = new StringBuilder();
            urlAppend.append("target_lang=");
            urlAppend.append(targetLang.toLowerCase());
            urlAppend.append("&source_lang=");
            urlAppend.append(sourceLang.toLowerCase());
            try {
                localizationUrl += urlAppend.toString();
                Log.e("localizationUrl", "=" + localizationUrl);
                Log.e("localizationStringSent", "=" + inputString.size());
                URL url = new URL(localizationUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("REV-APP-ID", app_id);
                urlConnection.setRequestProperty("REV-API-KEY", api_key);

                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
//                urlConnection.setChunkedStreamingMode(0);
                urlConnection.setConnectTimeout(20000);
                urlConnection.setReadTimeout(20000);

                JSONObject jsonParam = getJsonObj();
                Log.e("jsonParam", "" + jsonParam);


                OutputStream os = urlConnection.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.close();


                int statusCode = urlConnection.getResponseCode();
                String statusMsg = urlConnection.getResponseMessage();
                Log.e("Response_code","="+statusCode);
                Log.e("statusMsg","="+statusMsg);

                if (statusCode == 200 && statusMsg.equalsIgnoreCase("OK")) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.e("Localization", builder.toString());
                    return builder.toString();
                } else{
                    return null;
                }
            }
            catch (Exception e) {
                Log.e("Exception","="+e);
                return null;
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("Output","="+s);
            if(stringEngHindMap.isEmpty()){
                stringEngHindMap.clear();
            }
            if (s != null) {
                JSONArray arr;
                try {
                    JSONObject jobj = new JSONObject(s);
                    arr = jobj.getJSONArray("responseList");

                    int len = arr.length();
                    Log.e("localizationStringSent", "=" + len);
                    for (int i = 0; i < len; i++) {
                        JSONObject ob = arr.getJSONObject(i);
                        stringEngHindMap.put(ob.getString("inString"), ob.getString("outString"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            else {
                Toast.makeText(mContext,"Unable to Localize",Toast.LENGTH_SHORT).show();
                return;
            }
            localizePage();
        }

        private JSONObject getJsonObj() {
            JSONObject jObj = new JSONObject();
            JSONArray jsonArr = new JSONArray();

            for (String s: inputString) {
                jsonArr.put(s);
            }
            try {
                jObj.put("data", jsonArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jObj;
        }
    }

    private void localizePage() {
        for (String key:stringEngHindMap.keySet()){
            System.out.println(key+" "+stringEngHindMap.get(key));
        }

        TextView title = (TextView)findViewById(R.id.jobDetailsTitle);
        title.setText(stringEngHindMap.get("Job Details"));
        TextView jobtitle = (TextView)findViewById(R.id.jobDetailsjobTitle);
        jobtitle.setText(stringEngHindMap.get("Software Professional- Java/ J2ee"));
        TextView jobOrg = (TextView)findViewById(R.id.jobDetailsOrg);
        jobOrg.setText(stringEngHindMap.get("Global Hunt India Pvt. Ltd."));
        TextView jobExp = (TextView)findViewById(R.id.jobDetailsExp);
        jobExp.setText(stringEngHindMap.get("3 - 8 Years"));
        TextView jobDet = (TextView) findViewById(R.id.jobDetailsLoc);
        jobDet.setText(stringEngHindMap.get("Bengaluru/Bangalore, Pune, Noida"));
        jobDet = (TextView) findViewById(R.id.jobDetailsPostedOn);
        jobDet.setText(stringEngHindMap.get("Posted on 05 Sep, 2016"));
        jobDet = (TextView) findViewById(R.id.jobDetailsDesc);
        jobDet.setText(stringEngHindMap.get("Job Description"));
        jobDet = (TextView) findViewById(R.id.jobDetailsDescriptionValue);
        jobDet.setText(stringEngHindMap.get("Experience MUST in Core Java, Web services, JMS technology"));
        jobDet = (TextView) findViewById(R.id.jobDetailsIndustry);
        jobDet.setText(stringEngHindMap.get("Industry"));
        jobDet = (TextView) findViewById(R.id.jobDetailsIndustryValue);
        jobDet.setText(stringEngHindMap.get("IT-Software / Software Services"));
        jobDet = (TextView) findViewById(R.id.jobDetailsFuncArea);
        jobDet.setText(stringEngHindMap.get("Functional Area"));
        jobDet = (TextView) findViewById(R.id.jobDetailsFuncAreaValue);
        jobDet.setText(stringEngHindMap.get("IT Software- Application Programming, Maintenance"));
        jobDet = (TextView) findViewById(R.id.jobDetailsRole);
        jobDet.setText(stringEngHindMap.get("Role"));
        jobDet = (TextView) findViewById(R.id.jobDetailsRoleValue);
        jobDet.setText(stringEngHindMap.get("Software Developer"));
        jobDet = (TextView) findViewById(R.id.jobDetailskeySkill);
        jobDet.setText(stringEngHindMap.get("Key Skills"));
        jobDet = (TextView) findViewById(R.id.jobDetailskeySkillValue);
        jobDet.setText(stringEngHindMap.get("Java Web Services, JMS, J2Ee, Core Java"));
        jobDet = (TextView) findViewById(R.id.jobDetailsRoleCat);
        jobDet.setText(stringEngHindMap.get("Role Category"));
        jobDet = (TextView) findViewById(R.id.jobDetailsRoleCatValue);
        jobDet.setText(stringEngHindMap.get("Programming and Design"));
        jobDet = (TextView) findViewById(R.id.jobDetailsDesCandPro);
        jobDet.setText(stringEngHindMap.get("Desired Candidate profile"));
        jobDet = (TextView) findViewById(R.id.jobDetailsCandEdu);
        jobDet.setText(stringEngHindMap.get("UG: B.Tech/B.E. - Any Specialization, BCA - Computers, B.Sc - Any Specialization, Computers, PG:M.Tech - Any Specialization, MCA - Computers, Doctorate:Any Doctorate - Any Specialization, Doctorate Not Required"));
        jobDet = (TextView) findViewById(R.id.jobDetailsCandExp);
        jobDet.setText(stringEngHindMap.get("Excellent knowledge of Relational Databases, SQL and ORM technologies (JPA2, Hibernate)\n" +
                "Experience in the Spring Framework\n" +
                "Experience in developing web applications using at least one popular web framework (JSF, jQuery, Spring MVC)\n" +
                "Experience with test-driven development"));
        jobDet = (TextView) findViewById(R.id.jobDetailsEmployerDetails);
        jobDet.setText(stringEngHindMap.get("Employer Details"));
        jobDet = (TextView) findViewById(R.id.jobDetailsEmployerDetailsCompName);
        jobDet.setText(stringEngHindMap.get("Company Name"));
        jobDet = (TextView) findViewById(R.id.jobDetailsEmployerDetailsCompNameValue);
        jobDet.setText(stringEngHindMap.get("Global Hunt India Pvt. Ltd."));
        jobDet = (TextView) findViewById(R.id.jobDetailsEmployerDetailsCompWeb);
        jobDet.setText(stringEngHindMap.get("Company Websites"));
        jobDet = (TextView) findViewById(R.id.jobDetailsEmployerDetailsCompWebValue);
        jobDet.setText(stringEngHindMap.get("Not Mentioned"));
        jobDet = (TextView) findViewById(R.id.jobDetailsConDetails);
        jobDet.setText(stringEngHindMap.get("View Contact Details"));
        jobDet = (TextView) findViewById(R.id.jobDetailsSendjobs);
        jobDet.setText(stringEngHindMap.get("Send me Jobs Like This"));
        jobDet = (TextView) findViewById(R.id.jobDetailsSimilarJob);
        jobDet.setText(stringEngHindMap.get("Similar Jobs"));
        Button apply = (Button) findViewById(R.id.apply);
        apply.setText(stringEngHindMap.get("Apply"));
    }
}
