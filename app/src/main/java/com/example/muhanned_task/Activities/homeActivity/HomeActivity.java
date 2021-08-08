package com.example.muhanned_task.Activities.homeActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muhanned_task.Activities.jobdetailsActivity.JobDetailsActivity;
import com.example.muhanned_task.Adapters.HomeAdapter;
import com.example.muhanned_task.Models.JobsModel;
import com.example.muhanned_task.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnItemClickListener {

    RecyclerView rvJobsfinder;
    HomeAdapter homeAdapter;
    private List<JobsModel> jobsModels = new ArrayList<>();

    List<String> postions = new ArrayList<>();
    List<String> jobsLocation = new ArrayList<>();

    private HomeActivityViewModel homeActivityViewModel;
    ProgressDialog progressDialog;
    AutoCompleteTextView etBypostion;
    AutoCompleteTextView etBylocation;
    Button bnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initValue();
        progressDialog.show();
        homeActivityViewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);
        homeActivityViewModel.getData("","").observe(this, new Observer<List<JobsModel>>() {
            @Override
            public void onChanged(List<JobsModel> activeModels) {
                if (activeModels.size() < 0) {
                    progressDialog.dismiss();

                }
                else {
                    jobsModels= activeModels;
                    filter(activeModels);
                    jobLocationfilter(activeModels);

                }
                homeAdapter = new HomeAdapter(HomeActivity.this,activeModels);
                rvJobsfinder.setAdapter(homeAdapter);
                homeAdapter.setItemClickListener(HomeActivity.this);

                progressDialog.dismiss();

            }
        });
    }

    private void jobLocationfilter(List<JobsModel> activeModels) {
        for (int index = 0 ; index<activeModels.size();index++)
        {
            jobsLocation.add(activeModels.get(index).getCompanyLocation());
        }

        List<String> newList = new ArrayList<String>(new HashSet<String>(jobsLocation));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,newList);
        //Getting the instance of AutoCompleteTextView
        etBylocation.setThreshold(1);//will start working from first character
        etBylocation.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        etBylocation.setTextColor(Color.BLACK);

    }

    private void filter(List<JobsModel> jobsModels) {
        for (int index = 0 ; index<jobsModels.size();index++)
        {
            postions.add(jobsModels.get(index).getJobTitle());
        }

        List<String> newList = new ArrayList<String>(new HashSet<String>(postions));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,newList);
        //Getting the instance of AutoCompleteTextView
        etBypostion.setThreshold(1);//will start working from first character
        etBypostion.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        etBypostion.setTextColor(Color.BLACK);


    }

    private void initValue() {
        rvJobsfinder = findViewById(R.id.rvJobsfinder);
        etBypostion = findViewById(R.id.etBypostion);
        etBylocation = findViewById(R.id.etBylocation);
        bnSearch = findViewById(R.id.bnSearch);
        LinearLayoutManager lytManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvJobsfinder.setLayoutManager(lytManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        bnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                homeActivityViewModel.getData(etBypostion.getText().toString()+"",
                        etBylocation.getText().toString()+"")
                        .observe(HomeActivity.this, new Observer<List<JobsModel>>() {
                    @Override
                    public void onChanged(List<JobsModel> activeModels) {
                        if (activeModels.size() < 0) {
                            progressDialog.dismiss();

                        }
                        else {
                            filter(activeModels);
                            jobLocationfilter(activeModels);

                        }
                        homeAdapter = new HomeAdapter(HomeActivity.this,activeModels);
                        rvJobsfinder.setAdapter(homeAdapter);
                        homeAdapter.setItemClickListener(HomeActivity.this);

                        progressDialog.dismiss();

                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(HomeActivity.this, JobDetailsActivity.class);
        i.putExtra("Link", jobsModels.get(position).getUrl()+"");
        startActivity(i);


    }
}
