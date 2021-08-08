package com.example.muhanned_task.Activities.homeActivity;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.muhanned_task.Managers.ApiCallHandler;
import com.example.muhanned_task.Managers.BusinessManager;
import com.example.muhanned_task.Models.JobsModel;
import com.example.muhanned_task.Models.JobsWrapper;

import java.util.List;

public class HomeActivityViewModel extends ViewModel {
    private MutableLiveData<List<JobsModel>> jobsList;

    String jobTitle="";
    String jobLocation="";

    public LiveData<List<JobsModel>> getData( String jobTitle , String jobLocation) {
        this.jobTitle=jobTitle;
        this.jobLocation=jobLocation;
            jobsList = new MutableLiveData<>();
            loadData();
        return jobsList;
    }

    private void loadData() {
        new BusinessManager().getJobs( jobTitle+"", jobLocation+"", new ApiCallHandler() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                JobsWrapper jobsWrapper = (JobsWrapper) responseObject;

                    jobsList.setValue(jobsWrapper.getJobsModelArrayList());

            }

            @Override
            public void onFailure(String errorResponse) {
                System.out.println("error"+errorResponse);

            }
        });

    }


}
