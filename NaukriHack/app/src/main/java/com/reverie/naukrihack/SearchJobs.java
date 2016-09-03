package com.reverie.naukrihack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reverie.Listener.LocalizeListener;
import com.reverie.Network.LocalizeAsync;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchJobs extends AppCompatActivity {
    private HashMap<String, View> mapEngView = new HashMap<String, View>();
    private LocalizeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_jobs);

        EditText key = (EditText) findViewById(R.id.edtSkill);
        EditText location = (EditText) findViewById(R.id.edtLoc);
        EditText exp = (EditText) findViewById(R.id.edtExper);
        EditText expSal = (EditText) findViewById(R.id.edtSalary);

        TextView keyTV = (TextView) findViewById(R.id.key);
        TextView locationTV = (TextView) findViewById(R.id.locTv);
        TextView expTV = (TextView) findViewById(R.id.expTV);
        TextView expSalTV = (TextView) findViewById(R.id.salaryTV);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),JobsForYou.class));
            }
        });

        mapEngView.put("Keywords", keyTV);
        mapEngView.put("Location", locationTV);
        mapEngView.put("Experience (in Years)", expTV);
        mapEngView.put("Expected Salary", expSal);
        mapEngView.put("Enter Skill, Designation, Role", key);
        mapEngView.put("Select Location", location);
        mapEngView.put("E.g: 3", exp);
        mapEngView.put("Minimum Salary(Lakhs/Annum)", expSalTV);
        mapEngView.put("Search Jobs", btnSearch);

        listener = new LocalizeListener() {
            @Override
            public void onResponse(HashMap<String, String> map) {
                if (map != null) {
                    try {
                        View v;
                        for (String key : map.keySet()) {
                            v = mapEngView.get(key);
                            if (v instanceof EditText) {
                                ((EditText) v).setHint(map.get(key));
                            } else if (v instanceof Button) {
                                ((Button) v).setText(map.get(key));
                            } else if (v instanceof TextView) {
                                ((TextView) v).setText(map.get(key));
                            }

                        }
                    } catch (Exception e) {

                    }
                }
            }
        };

        if (UserDetails.isHindi) {
            ArrayList<String> inStrings = new ArrayList<String>();
            for (String key1 : mapEngView.keySet()) {
                inStrings.add(key1);
            }
            String[] inStringsArr = new String[inStrings.size()];
            inStringsArr = inStrings.toArray(inStringsArr);

            new LocalizeAsync(this, inStringsArr, "5VaVp5c0Y8rtFs74LRO03q6jAMfZKkJNuTC1", "com.reverie.phonebook", 3, "hindi", "english", 0, "abc", listener).execute();
        }


    }
}
