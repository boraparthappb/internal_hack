package com.reverie.naukrihack;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reverie.model.SearchJobs;

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

public class JobsForYou extends AppCompatActivity {
    private SearchJobs[] searchJobsList;
    private HashMap<String, String> stringEngHindMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_for_you);
        ImageView cancel = (ImageView)findViewById(R.id.cancelJobSearch);
        stringEngHindMap = new HashMap<>();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.jobListRV);
        JobSearchAdapter hAdapter = new JobSearchAdapter(getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hAdapter);
        hAdapter.notifyDataSetChanged();
        if(UserDetails.isHindi){
            ArrayList<String> inString = new ArrayList<>();
            for(int i=0;i<6/*SearchJobs.jobsData.length*/;i++){
                for (int j = 0;j<SearchJobs.jobsData[i].length;j++){
                    inString.add(SearchJobs.jobsData[i][j]);
                }
            }
            new LocalizationAsync(JobsForYou.this,inString,"english","hindi").execute();
        }
    }

    public void localizePage(){
        if(UserDetails.isHindi){
            TextView jobTitle = (TextView)findViewById(R.id.jobTitle);
            jobTitle.setText(stringEngHindMap.get("Jobs for You"));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.jobListRV);
        JobSearchAdapter hAdapter = new JobSearchAdapter(getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hAdapter);
        hAdapter.notifyDataSetChanged();
    }

    public class JobSearchAdapter extends RecyclerView.Adapter<JobSearchAdapter.JobViewHolder>{
        public JobSearchAdapter(Context applicationContext) {
            
        }

        public class JobViewHolder extends RecyclerView.ViewHolder {
            private TextView title, org, loc,exp, keySkill;
            public JobViewHolder(View v){
                super(v);
                title = (TextView)v.findViewById(R.id.jobTitle);
                org = (TextView)v.findViewById(R.id.jobOrg);
                loc = (TextView)v.findViewById(R.id.jobLoc);
                exp = (TextView)v.findViewById(R.id.jobExp);
                keySkill = (TextView)v.findViewById(R.id.jobSkill);
            }
        }


        @Override
        public JobSearchAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.jobsearch_cell, parent, false);
            return new JobViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(JobSearchAdapter.JobViewHolder holder, int position) {
            ScaleAnimation animation = new ScaleAnimation(0.7f,1f,0.7f,1f,Animation.RELATIVE_TO_PARENT,0.5f, Animation.RELATIVE_TO_PARENT,0.5f);
            animation.setDuration(100);
            if(UserDetails.isHindi){
                holder.title.setText(stringEngHindMap.get(SearchJobs.jobsData[position][0]));
                holder.org.setText(stringEngHindMap.get(SearchJobs.jobsData[position][1]));
                holder.exp.setText(stringEngHindMap.get(SearchJobs.jobsData[position][2]));
                holder.loc.setText(stringEngHindMap.get(SearchJobs.jobsData[position][3]));
                holder.keySkill.setText(stringEngHindMap.get(SearchJobs.jobsData[position][4]));
            }
            else {
                holder.title.setText(SearchJobs.jobsData[position][0]);
                holder.org.setText(SearchJobs.jobsData[position][1]);
                holder.exp.setText(SearchJobs.jobsData[position][2]);
                holder.loc.setText(SearchJobs.jobsData[position][3]);
                holder.keySkill.setText(SearchJobs.jobsData[position][4]);
            }

        }
        @Override
        public int getItemCount() {
            return 6;
        }
    }

    class LocalizationAsync extends AsyncTask<Void, Void, String> {
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
