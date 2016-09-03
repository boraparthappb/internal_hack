package com.reverie.naukrihack;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.reverie.model.SearchJobs;

public class JobsForYou extends AppCompatActivity {
    private SearchJobs[] searchJobsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_for_you);
        ImageView cancel = (ImageView)findViewById(R.id.cancelJobSearch);
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

    }


    public class JobSearchAdapter extends RecyclerView.Adapter<JobSearchAdapter.JobViewHolder>{
        public JobSearchAdapter(Context applicationContext) {
            
        }

        public class JobViewHolder extends RecyclerView.ViewHolder {
            public TextView title, org, exp, keySkill;
            public JobViewHolder(View v){
                super(v);
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

        }
        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
