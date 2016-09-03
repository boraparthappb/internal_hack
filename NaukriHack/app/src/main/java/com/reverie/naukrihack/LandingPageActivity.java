package com.reverie.naukrihack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reverie.Listener.LocalizeListener;
import com.reverie.Network.LocalizeAsync;

import java.util.ArrayList;
import java.util.HashMap;

public class LandingPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, View> mapEngView = new HashMap<String, View>();
    HashMap<String, MenuItem> mapEngMenuItem = new HashMap<String, MenuItem>();
    private LocalizeListener listener;
    private MenuItem nav_language;
    private LinearLayout header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView =  navigationView.getHeaderView(0);
        header = (LinearLayout)headerView.findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserDetails.class));
            }
        });


        // get menu from navigationView
        Menu menu = navigationView.getMenu();
        MenuItem login = menu.findItem(R.id.nav_login);
        MenuItem register = menu.findItem(R.id.nav_register);
        MenuItem searchJobs = menu.findItem(R.id.nav_search_job);
        MenuItem aboutUs = menu.findItem(R.id.nav_about);
        MenuItem fastForward = menu.findItem(R.id.nav_fast_forward);
        MenuItem faqs = menu.findItem(R.id.nav_faq);
        MenuItem feedback = menu.findItem(R.id.nav_feedback);
        MenuItem promote = menu.findItem(R.id.nav_promoteApp);
        nav_language = menu.findItem(R.id.nav_language);
        mapEngMenuItem.put("Login", login);
        mapEngMenuItem.put("Register", register);
        mapEngMenuItem.put("Search Jobs", searchJobs);
        mapEngMenuItem.put("About us", aboutUs);
        mapEngMenuItem.put("Fast Forward", fastForward);
        mapEngMenuItem.put("FAQs", faqs);
        mapEngMenuItem.put("Feedback", feedback);
        mapEngMenuItem.put("Promote this app", promote);

        TextView recuiterMsg = (TextView) findViewById(R.id.recuiterMsg);
        TextView jobs4u = (TextView) findViewById(R.id.jobs4u);
        TextView critical = (TextView) findViewById(R.id.critical_noti);
        TextView profileViews = (TextView) findViewById(R.id.profileViews);

        mapEngView.put("Recuiter\nmessages", recuiterMsg);
        mapEngView.put("Jobs for you", jobs4u);
        mapEngView.put("Critical\nactions", critical);
        mapEngView.put("Profile\nViews", profileViews);

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
                    try {
                        MenuItem v;
                        for (String key : map.keySet()) {
                            v = mapEngMenuItem.get(key);
                            if (v != null){
                                v.setTitle(map.get(key));
//                                ((TextView) v).setText(map.get(key));
                            }
                        }

                    }catch (Exception e){

                    }
                    nav_language.setTitle("English");
                    UserDetails.isHindi = true;
                }
            }
        };
}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            // Handle the camera action
        } else if (id == R.id.nav_register) {

        } else if (id == R.id.nav_search_job) {
            startActivity(new Intent(getApplicationContext(),SearchJobs.class));

        } else if (id == R.id.nav_about) {

        }
        else if (id== R.id.nav_language){
            callLocalize(item.getTitle());
        }
        else if (id== R.id.nav_faq){
            startActivity(new Intent(getApplicationContext(),Faq.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callLocalize(CharSequence title) {
        if (title.toString().contains("हिन्दी")){

            ArrayList<String> inStrings = new ArrayList<String>();
            for (String key:mapEngView.keySet()){
                inStrings.add(key);
            }
            String[] inStringsArr = new String[inStrings.size()];
            inStringsArr = inStrings.toArray(inStringsArr);

            //mapEngView.keySet();
//        new TransliterateAsync(this,inStrings,"F1DH0zauuURU8ib9xHRT992nyZBvIVoIZHYh","com.reverie.phonebook",3,sentlang(ReverieApplication.mSelectedLang),"english",0,"abc",listener).execute();
            new LocalizeAsync(this,inStringsArr,"5VaVp5c0Y8rtFs74LRO03q6jAMfZKkJNuTC1","com.reverie.phonebook",3,"hindi","english",0,"abc",listener).execute();
            for (String key:mapEngMenuItem.keySet()){
                inStrings.add(key);
            }
            inStringsArr = inStrings.toArray(inStringsArr);

            new LocalizeAsync(this,inStringsArr,"5VaVp5c0Y8rtFs74LRO03q6jAMfZKkJNuTC1","com.reverie.phonebook",3,"hindi","english",0,"abc",listener).execute();

        }else if (title.toString().contains("English")){
            try {
                View v;
                for (String key : mapEngView.keySet()) {
                    v = mapEngView.get(key);
                    if (v instanceof TextView){
                        ((TextView) v).setText(key);
                    }

                }
            }catch (Exception e){

            }
            try {
                MenuItem v;
                for (String key : mapEngMenuItem.keySet()) {
                    v = mapEngMenuItem.get(key);
                    if (v != null){
                        v.setTitle(key);
//                                ((TextView) v).setText(map.get(key));
                    }
                }

            }catch (Exception e){

            }
            nav_language.setTitle("हिन्दी");
            UserDetails.isHindi = false;
        }
    }


    public void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.recuiter_lay:
                Toast.makeText(this, "Recuiter\n" +
                        "messages", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recuiter_lay2:
                Toast.makeText(this, "Jobs for you", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recuiter_lay3:
                Toast.makeText(this, "Critical\n" +
                        "actions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileViewLL:
                Toast.makeText(this, "Profile\nVIews", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
