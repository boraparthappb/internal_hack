package com.reverie.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.reverie.Listener.LocalizeListener;

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
import java.util.HashMap;

public class LocalizeAsync extends AsyncTask<String, Integer, String> {
    private URL url;
    private LocalizeListener localizeListener;
    private HttpURLConnection urlConnection;
    private Context mContext;
    private String[] inputStrings;
    private String api_key, app_id, targetLang, sourceLang, uuid;
    private int domain, status;

    public LocalizeAsync(Context context, String[] inputStrings, String api_key, String app_id, int domain, String targetLang, String sourceLang, int status, String uuid, LocalizeListener listener) {
        this.mContext = context;
        this.inputStrings = inputStrings;
        this.api_key = api_key;
        this.app_id = app_id;
        this.targetLang = targetLang;
        this.sourceLang = sourceLang;
        this.uuid = uuid;
        this.domain = domain;
        this.status = status;
        this.localizeListener = listener;
    }



    /**
     * @param type Transliteration, localization or search
     * @return Text in your language
     */

    @Override
    protected String doInBackground(String[] type) {
        if (true) {//TODO check network connection here
//            String localizationUrl = "http://api.revup.reverieinc.com/v2/localization?";
            String localizationUrl = "http://beta.api.revup.reverieinc.com/v2/localization?";
//            String localizationUrl = "http://beta.api.revup.reverieinc.com/v2/localization?target_lang=Assamese&source_lang=english&domain=3";

            StringBuilder urlAppend = new StringBuilder();
            urlAppend.append("target_lang=");
            urlAppend.append(targetLang.toLowerCase());
            urlAppend.append("&source_lang=");
            urlAppend.append(sourceLang.toLowerCase());
            urlAppend.append("&domain=");
            urlAppend.append(domain);

            try {
                localizationUrl += urlAppend.toString();
                Log.e("localizationUrl", "=" + localizationUrl);
                url = new URL(localizationUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("REV-APP-ID", app_id);
                urlConnection.setRequestProperty("REV-API-KEY", api_key);


                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
//                urlConnection.setChunkedStreamingMode(0);
                urlConnection.setConnectTimeout(7000);
                urlConnection.setReadTimeout(7000);

                JSONObject jsonParam = getJsonObj(inputStrings);
//                    Log.e("jsonParam", "" + jsonParam);


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
                    return builder.toString();
                } else
                    return null;
//		    		}
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception","="+e);
                return null;
            } finally {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
        Log.e("Output","="+s);
        HashMap<String, String> myMap = new HashMap<String, String>();
        if (s != null) {
            JSONArray arr;

            try {
                JSONObject jobj = new JSONObject(s);
                Log.e("jobj", "=" + jobj);
                arr = jobj.getJSONArray("responseList");

                int len = arr.length();
                for (int i = 0; i < len; i++) {
                    JSONObject ob = arr.getJSONObject(i);
                    //String in = ob.getString("inString");
                    //String res = ob.getString("response");
                    myMap.put(ob.getString("inString"), ob.getString("outString"));
//                    hitStatus = ob.getInt("api_status");
                }
            } catch (JSONException e) {
                e.printStackTrace();
//                        Log.e("OnPost", "" + e);
            }
//            if (cache_fetch != null && cache_fetch.size() > 0) {
//                Set<String> keySet = cache_fetch.keySet();
//                String keys[] = new String[keySet.size()];
//                Iterator<String> iterator = keySet.iterator();
//                int counter = 0;
//                while (iterator.hasNext()) {
//                    keys[counter++] = iterator.next();
//                }
//
//                for (String key : keys) {
//                    //String str = cache_fetch.get(key);
//                    myMap.put(key, cache_fetch.get(key));
//                }
//            }
        }
//        finalMap.putAll(myMap);
        if (localizeListener != null)
            localizeListener.onResponse(myMap);
    }

    private JSONObject getJsonObj(String[] inputStrings) {
        JSONObject jObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        for (String inputString : inputStrings) {
            jsonArr.put(inputString);
        }

        try {
            jObj.put("data", jsonArr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            Log.e("JSON Send", "" + jObj);
        return jObj;
    }

}
