package com.reverie.naukrihack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.reverie.model.UserDetailsData;

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
import java.util.Set;

public class UserDetails extends AppCompatActivity {

    private HashMap<String, TextView> stringViewMap;
    public static HashMap<String, String> stringEngHindMap;
    private ArrayList<String> userDetailsStrings;
    public static boolean isHindi = false;
    private TextView pageTitle, userName, userDesignation, userLocation, userExp, userContactDetails, userMail,userPhone,
            resumeHeadline, resumeHeadlineValue, keySkills, keySkillsValue, empDetails, empDetailsDesignation,
            empDetailsFromTo, empDetailsRole, ITSkills, ITSkillsName1, ITSkillsName2, ITSkillsExp1, ITSkillsExp2,
            ITSkillsLastUsed1, ITSkillsLastUsed2, education, educationCourse1, educationCourse2, educationOrg1, educationOrg2,
            educationCompleteYear1, educationCompleteYear2, workDetails, workDetailsRole, workDetailsRoleValue,
            workDetailsFunArea, workDetailsFunAreaValue, workDetailsIndustry, workDetailsIndustryValue,
            workDetailsJobPref, workDetailsJobPrefValue, workDetailsPrefLoc, workDetailsPrefLocValue,
            workDetailsAvailToJoin, workDetailsAvailToJoinValue,workworkDetailsWorkAuth,workworkDetailsWorkAuthValue, personalDetails, personalDetailsDOB,
            personalDetailsDOBValue,personalDetailsGender, personalDetailsGenderValue, personalDetailsHometown, personalDetailsHometownValue,
            personalDetailsPin, personalDetailsPinValue, personalDetailsMaritalStatus, personalDetailsMaritalStatusValue,
            personalDetailsAddress, personalDetailsAddressValue, ctc;
    private TextView empDetailsOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ImageButton back = (ImageButton)findViewById(R.id.user_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        stringViewMap = new HashMap<>();
        stringEngHindMap = new HashMap<>();
        initViews();
        setTextInEnglish();
        if(isHindi)
            new LocalizationAsync(UserDetails.this,userDetailsStrings,"english","hindi").execute();
    }

    public void localizePage(){
        if(isHindi){
            setTextInLocalLang();
        }
        else {
            setTextInEnglish();
        }
    }

    private void setTextInEnglish() {
        Set<String> keySet = stringViewMap.keySet();
        for (String key: keySet){
            (stringViewMap.get(key)).setText(key);
        }
    }

    private void setTextInLocalLang() {
        Set<String> keySet = stringViewMap.keySet();
        for (String key: keySet){
            (stringViewMap.get(key)).setText(stringEngHindMap.get(key));
            if(key.contains("Software")){
                Log.d("Tag",""+key);
            }
        }
    }

    private void initViews() {
        userDetailsStrings = UserDetailsData.userDetails.getUserDetailsStrings();
        pageTitle = (TextView)findViewById(R.id.user_page_title);
        stringViewMap.put(userDetailsStrings.get(0),pageTitle);
        userName = (TextView)findViewById(R.id.user_name);
        stringViewMap.put(userDetailsStrings.get(1),userName);
        userDesignation = (TextView)findViewById(R.id.user_designation);
        stringViewMap.put(userDetailsStrings.get(2),userDesignation);
        userLocation = (TextView)findViewById(R.id.user_loc);
        stringViewMap.put(userDetailsStrings.get(3),userLocation);
        userExp = (TextView)findViewById(R.id.user_exp);
        stringViewMap.put(userDetailsStrings.get(4),userExp);
        ctc = (TextView)findViewById(R.id.user_ctc);
        stringViewMap.put(userDetailsStrings.get(5),ctc);
        userContactDetails = (TextView)findViewById(R.id.user_contactdetails);
        stringViewMap.put(userDetailsStrings.get(6),userContactDetails);
        userMail = (TextView)findViewById(R.id.user_email);
        stringViewMap.put(userDetailsStrings.get(7),userMail);
        userPhone = (TextView)findViewById(R.id.user_phone);
        stringViewMap.put(userDetailsStrings.get(8),userPhone);

        resumeHeadline = (TextView)findViewById(R.id.user_resumeheadline);
        stringViewMap.put(userDetailsStrings.get(9),resumeHeadline);
        resumeHeadlineValue = (TextView)findViewById(R.id.user_resumeheadlinedata);
        stringViewMap.put(userDetailsStrings.get(10),resumeHeadlineValue);

        keySkills = (TextView)findViewById(R.id.user_keySkil);
        stringViewMap.put(userDetailsStrings.get(11),keySkills);
        keySkillsValue = (TextView)findViewById(R.id.user_keySkillData);
        stringViewMap.put(userDetailsStrings.get(12),keySkillsValue);

        empDetails = (TextView)findViewById(R.id.user_employmentDetails);
        stringViewMap.put(userDetailsStrings.get(13),empDetails);
        empDetailsDesignation = (TextView)findViewById(R.id.user_employmentDesignation);
        stringViewMap.put(userDetailsStrings.get(14),empDetailsDesignation);
        empDetailsOrg = (TextView) findViewById(R.id.user_employmentCompany);
        stringViewMap.put(userDetailsStrings.get(15), empDetailsOrg);
        empDetailsFromTo = (TextView)findViewById(R.id.user_employmentExp);
        stringViewMap.put(userDetailsStrings.get(16),empDetailsFromTo);
        empDetailsRole = (TextView)findViewById(R.id.user_employmentRole);
        stringViewMap.put(userDetailsStrings.get(17),empDetailsRole);

        ITSkills = (TextView)findViewById(R.id.user_ITSkill);
        stringViewMap.put(userDetailsStrings.get(18),ITSkills);
        ITSkillsName1 = (TextView)findViewById(R.id.user_ITSkillName);
        stringViewMap.put(userDetailsStrings.get(19),ITSkillsName1);
        ITSkillsExp1 = (TextView)findViewById(R.id.user_ITSkillExp);
        stringViewMap.put(userDetailsStrings.get(20),ITSkillsExp1);
        ITSkillsLastUsed1 = (TextView)findViewById(R.id.user_ITSkillLastUsed);
        stringViewMap.put(userDetailsStrings.get(21),ITSkillsLastUsed1);
        ITSkillsName2 = (TextView)findViewById(R.id.user_ITSkillName1);
        stringViewMap.put(userDetailsStrings.get(22),ITSkillsName2);
        ITSkillsExp2 = (TextView)findViewById(R.id.user_ITSkillExp1);
        stringViewMap.put(userDetailsStrings.get(23),ITSkillsExp2);
        ITSkillsLastUsed2 = (TextView)findViewById(R.id.user_ITSkillLastUsed1);
        stringViewMap.put(userDetailsStrings.get(24),ITSkillsLastUsed2);

        education = (TextView)findViewById(R.id.user_EducationDetails);
        stringViewMap.put(userDetailsStrings.get(25),education);
        educationCourse1 = (TextView)findViewById(R.id.user_EducationCourse);
        stringViewMap.put(userDetailsStrings.get(26),educationCourse1);
        educationOrg1 = (TextView)findViewById(R.id.user_EducationOrg);
        stringViewMap.put(userDetailsStrings.get(27),educationOrg1);
        educationCompleteYear1 = (TextView)findViewById(R.id.user_EducationYear);
        stringViewMap.put(userDetailsStrings.get(28),educationCompleteYear1);

        educationCourse2 = (TextView)findViewById(R.id.user_EducationCourse1);
        stringViewMap.put(userDetailsStrings.get(29),educationCourse2);
        educationOrg2 = (TextView)findViewById(R.id.user_EducationOrg1);
        stringViewMap.put(userDetailsStrings.get(30),educationOrg2);
        educationCompleteYear2 = (TextView)findViewById(R.id.user_EducationYear1);
        stringViewMap.put(userDetailsStrings.get(31),educationCompleteYear2);

        workDetails = (TextView)findViewById(R.id.user_workDetails);
        stringViewMap.put(userDetailsStrings.get(32),workDetails);
        workDetailsRole = (TextView)findViewById(R.id.user_workDetailsRole);
        stringViewMap.put(userDetailsStrings.get(33),workDetailsRole);
        workDetailsRoleValue = (TextView)findViewById(R.id.user_workDetailsRoleName);
        stringViewMap.put(userDetailsStrings.get(34),workDetailsRoleValue);
        workDetailsFunArea = (TextView)findViewById(R.id.user_workDetailsFunctionalArea);
        stringViewMap.put(userDetailsStrings.get(35),workDetailsFunArea);
        workDetailsFunAreaValue = (TextView)findViewById(R.id.user_workDetailsFunctionalAreaName);
        stringViewMap.put(userDetailsStrings.get(36),workDetailsFunAreaValue);
        workDetailsIndustry = (TextView)findViewById(R.id.user_workDetailsIndustry);
        stringViewMap.put(userDetailsStrings.get(37),workDetailsIndustry);
        workDetailsIndustryValue = (TextView)findViewById(R.id.user_workDetailsIndustryName);
        stringViewMap.put(userDetailsStrings.get(38),workDetailsIndustryValue);
        workDetailsJobPref = (TextView)findViewById(R.id.user_workDetailsJobPreference);
        stringViewMap.put(userDetailsStrings.get(39),workDetailsJobPref);
        workDetailsJobPrefValue = (TextView)findViewById(R.id.user_workDetailsJobPreferenceName);
        stringViewMap.put(userDetailsStrings.get(40),workDetailsJobPrefValue);
        workDetailsPrefLoc = (TextView)findViewById(R.id.user_workDetailsPrefferedLocation);
        stringViewMap.put(userDetailsStrings.get(41),workDetailsPrefLoc);
        workDetailsPrefLocValue = (TextView)findViewById(R.id.user_workDetailsPrefferedLocationName);
        stringViewMap.put(userDetailsStrings.get(42),workDetailsPrefLocValue);
        workDetailsAvailToJoin = (TextView)findViewById(R.id.user_workDetailsAvailToJoin);
        stringViewMap.put(userDetailsStrings.get(43),workDetailsAvailToJoin);
        workDetailsAvailToJoinValue = (TextView)findViewById(R.id.user_workDetailsAvailToJoinName);
        stringViewMap.put(userDetailsStrings.get(44),workDetailsAvailToJoinValue);
        workworkDetailsWorkAuth = (TextView)findViewById(R.id.user_workDetailsWorkAuth);
        stringViewMap.put(userDetailsStrings.get(45),workworkDetailsWorkAuth);
        workworkDetailsWorkAuthValue = (TextView)findViewById(R.id.user_workDetailsWorkAuthName);
        stringViewMap.put(userDetailsStrings.get(46),workworkDetailsWorkAuthValue);

        personalDetails = (TextView)findViewById(R.id.user_personalDetails);
        stringViewMap.put(userDetailsStrings.get(47),personalDetails);
        personalDetailsDOB = (TextView)findViewById(R.id.user_personalDetailsDOB);
        stringViewMap.put(userDetailsStrings.get(48),personalDetailsDOB);
        personalDetailsDOBValue = (TextView)findViewById(R.id.user_personalDetailsDOBValue);
        stringViewMap.put(userDetailsStrings.get(49),personalDetailsDOBValue);
        personalDetailsGender = (TextView)findViewById(R.id.user_personalDetailsGender);
        stringViewMap.put(userDetailsStrings.get(50),personalDetailsGender);
        personalDetailsGenderValue = (TextView)findViewById(R.id.user_personalDetailsGenderValue);
        stringViewMap.put(userDetailsStrings.get(51),personalDetailsGenderValue);
        personalDetailsHometown = (TextView)findViewById(R.id.user_personalDetailsHomeTown);
        stringViewMap.put(userDetailsStrings.get(52),personalDetailsHometown);
        personalDetailsHometownValue = (TextView)findViewById(R.id.user_personalDetailsHomeTownValue);
        stringViewMap.put(userDetailsStrings.get(53),personalDetailsHometownValue);
        personalDetailsPin = (TextView)findViewById(R.id.user_personalDetailsPin);
        stringViewMap.put(userDetailsStrings.get(54),personalDetailsPin);
        personalDetailsPinValue = (TextView)findViewById(R.id.user_personalDetailsPinValue);
        stringViewMap.put(userDetailsStrings.get(55),personalDetailsPinValue);
        personalDetailsMaritalStatus = (TextView)findViewById(R.id.user_personalDetailsMartialStatus);
        stringViewMap.put(userDetailsStrings.get(56),personalDetailsMaritalStatus);
        personalDetailsMaritalStatusValue = (TextView)findViewById(R.id.user_personalDetailsMartialStatusValue);
        stringViewMap.put(userDetailsStrings.get(57),personalDetailsMaritalStatusValue);

        personalDetailsAddress = (TextView)findViewById(R.id.user_personalDetailsAdd);
        stringViewMap.put(userDetailsStrings.get(58),personalDetailsAddress);
        personalDetailsAddressValue = (TextView)findViewById(R.id.user_personalDetailsAddValue);
        stringViewMap.put(userDetailsStrings.get(59),personalDetailsAddressValue);
    }
    class LocalizationAsync extends AsyncTask<Void, Void, String> {
        private ProgressDialog pgb;
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
            String localizationUrl = "https://api-revup.reverieinc.com/apiman-gateway/NaukriHack/localization/1.0?";
            String api_key = "DagJl4II5vLATNUU7BKFLSiMMnE7kQ3wOn6L",
                    app_id = "com.naukri.hack";
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
                    UserDetails.isHindi = false;
                    return null;
                }
            }
            catch (Exception e) {
                UserDetails.isHindi = false;
                Log.e("Exception","="+e);
                return null;
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("Output","="+s);
            if(!UserDetails.stringEngHindMap.isEmpty()){
                UserDetails.stringEngHindMap.clear();
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
                        UserDetails.stringEngHindMap.put(ob.getString("inString"), ob.getString("outString"));
                    }
                    UserDetails.isHindi = true;
                } catch (JSONException e) {
                    UserDetails.isHindi = false;
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(mContext,"Unable to Localize",Toast.LENGTH_SHORT).show();
                UserDetails.isHindi = false;
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
}
