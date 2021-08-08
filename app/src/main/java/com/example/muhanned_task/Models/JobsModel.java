package com.example.muhanned_task.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobsModel {
    private String companyLogo;

    @SerializedName("title")
    @Expose
    private String jobTitle;

    @SerializedName("company_name")
    @Expose
    private String companyName;

    @SerializedName("candidate_required_location")
    @Expose
    private String companyLocation;

    @SerializedName("publication_date")
    @Expose
    private String postDate;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("job-count")
    @Expose
    private int jobCount;

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
