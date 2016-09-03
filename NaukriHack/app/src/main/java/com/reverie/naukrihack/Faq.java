package com.reverie.naukrihack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.reverie.Listener.LocalizeListener;
import com.reverie.Network.LocalizeAsync;

import java.util.ArrayList;
import java.util.HashMap;

import static com.reverie.naukrihack.R.id.nav_language;

public class Faq extends AppCompatActivity {
    private HashMap<String, View> mapEngView = new HashMap<String, View>();
    private LocalizeListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        TextView faq_q1 = (TextView)findViewById(R.id.faq_q1);
        TextView faq_q2 = (TextView)findViewById(R.id.faq_q2);
        TextView faq_q3 = (TextView)findViewById(R.id.faq_q3);
        TextView faq_q4 = (TextView)findViewById(R.id.faq_q4);
        TextView faq_q5 = (TextView)findViewById(R.id.faq_q5);
        TextView faq_q6 = (TextView)findViewById(R.id.faq_q6);
        TextView faq_q7 = (TextView)findViewById(R.id.faq_q7);
        TextView faq_q8 = (TextView)findViewById(R.id.faq_q8);
        TextView faq_q9 = (TextView)findViewById(R.id.faq_q9);
        TextView faq_q10 = (TextView)findViewById(R.id.faq_q10);
        TextView faq_q11 = (TextView)findViewById(R.id.faq_q11);
        TextView faq_q12 = (TextView)findViewById(R.id.faq_q12);
        TextView faq_q13 = (TextView)findViewById(R.id.faq_q13);
        TextView faq_q14 = (TextView)findViewById(R.id.faq_q14);
        TextView faq_q15 = (TextView)findViewById(R.id.faq_q15);

        TextView faq_a1 = (TextView)findViewById(R.id.faq_a1);
        TextView faq_a2 = (TextView)findViewById(R.id.faq_a2);
        TextView faq_a3 = (TextView)findViewById(R.id.faq_a3);
        TextView faq_a4 = (TextView)findViewById(R.id.faq_a4);
        TextView faq_a5 = (TextView)findViewById(R.id.faq_a5);
        TextView faq_a6 = (TextView)findViewById(R.id.faq_a6);
        TextView faq_a7 = (TextView)findViewById(R.id.faq_a7);
        TextView faq_a8 = (TextView)findViewById(R.id.faq_a8);
        TextView faq_a9 = (TextView)findViewById(R.id.faq_a9);
        TextView faq_a10 = (TextView)findViewById(R.id.faq_a10);
        TextView faq_a11 = (TextView)findViewById(R.id.faq_a11);
        TextView faq_a12 = (TextView)findViewById(R.id.faq_a12);
        TextView faq_a13 = (TextView)findViewById(R.id.faq_a13);
        TextView faq_a14 = (TextView)findViewById(R.id.faq_a14);
        TextView faq_a15 = (TextView)findViewById(R.id.faq_a15);

        mapEngView.put(getResources().getString(R.string.faq_q1),faq_q1);
        mapEngView.put(getResources().getString(R.string.faq_q2),faq_q2);
        mapEngView.put(getResources().getString(R.string.faq_q3),faq_q3);
        mapEngView.put(getResources().getString(R.string.faq_q4),faq_q4);
        mapEngView.put(getResources().getString(R.string.faq_q5),faq_q5);
        mapEngView.put(getResources().getString(R.string.faq_q6),faq_q6);
        mapEngView.put(getResources().getString(R.string.faq_q7),faq_q7);
        mapEngView.put(getResources().getString(R.string.faq_q8),faq_q8);
        mapEngView.put(getResources().getString(R.string.faq_q9),faq_q9);
        mapEngView.put(getResources().getString(R.string.faq_q10),faq_q10);
        mapEngView.put(getResources().getString(R.string.faq_q11),faq_q11);
        mapEngView.put(getResources().getString(R.string.faq_q12),faq_q12);
        mapEngView.put(getResources().getString(R.string.faq_q13),faq_q13);
        mapEngView.put(getResources().getString(R.string.faq_q14),faq_q14);
        mapEngView.put(getResources().getString(R.string.faq_q15),faq_q15);

        mapEngView.put(getResources().getString(R.string.faq_a1),faq_a1);
        mapEngView.put(getResources().getString(R.string.faq_a2),faq_a2);
        mapEngView.put(getResources().getString(R.string.faq_a3),faq_a3);
        mapEngView.put(getResources().getString(R.string.faq_a4),faq_a4);
        mapEngView.put(getResources().getString(R.string.faq_a5),faq_a5);
        mapEngView.put(getResources().getString(R.string.faq_a6),faq_a6);
        mapEngView.put(getResources().getString(R.string.faq_a7),faq_a7);
        mapEngView.put(getResources().getString(R.string.faq_a8),faq_a8);
        mapEngView.put(getResources().getString(R.string.faq_a9),faq_a9);
        mapEngView.put(getResources().getString(R.string.faq_a10),faq_a10);
        mapEngView.put(getResources().getString(R.string.faq_a11),faq_a11);
        mapEngView.put(getResources().getString(R.string.faq_a12),faq_a12);
        mapEngView.put(getResources().getString(R.string.faq_a13),faq_a13);
        mapEngView.put(getResources().getString(R.string.faq_a14),faq_a14);
        mapEngView.put(getResources().getString(R.string.faq_a15),faq_a15);

        listener = new LocalizeListener() {
            @Override
            public void onResponse(HashMap<String, String> map) {
                if (map!=null) {
                    try {
                        View v;
                        for (String key : map.keySet()) {
                            v = mapEngView.get(key);
                            if (v instanceof TextView){
                                ((TextView) v).setText(map.get(key));
                            }

                        }
                    }catch (Exception e){

                    }
                }
            }
        };

        if (UserDetails.isHindi){
            ArrayList<String> inStrings = new ArrayList<String>();
            for (String key:mapEngView.keySet()){
                inStrings.add(key);
            }
            String[] inStringsArr = new String[inStrings.size()];
            inStringsArr = inStrings.toArray(inStringsArr);

            new LocalizeAsync(this,inStringsArr,"5VaVp5c0Y8rtFs74LRO03q6jAMfZKkJNuTC1","com.reverie.phonebook",3,"hindi","english",0,"abc",listener).execute();

        }
    }
}
