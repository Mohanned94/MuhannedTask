package com.example.muhanned_task.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JobsWrapper {

    @SerializedName("jobs")
    @Expose
    private ArrayList<JobsModel> jobsModelArrayList = new ArrayList<>();

    public ArrayList<JobsModel> getJobsModelArrayList() {
        return jobsModelArrayList;
    }

    public void setJobsModelArrayList(ArrayList<JobsModel> jobsModelArrayList) {
        this.jobsModelArrayList = jobsModelArrayList;
    }
}
