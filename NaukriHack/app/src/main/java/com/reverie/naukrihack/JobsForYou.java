package com.reverie.naukrihack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class JobsForYou extends AppCompatActivity {

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

    }


    private class JobSearchAdapter extends RecyclerView.Adapter<JobSearchAdapter.JobViewHolder>{
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

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
