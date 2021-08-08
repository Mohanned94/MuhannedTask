package com.example.muhanned_task.Managers;

import android.content.Context;

import com.example.muhanned_task.Models.JobsWrapper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class BusinessManager {
    public void getJobs(String jobTitle, String jobLocation, final ApiCallHandler callResponse) {
        final String url = "";
        Map<String, String> body = new HashMap<>();
        body.put("search", jobTitle + "");
        body.put("location", jobLocation + "");

        new ConnectionManager().getHttps(url, body, new ApiCallHandler() {

            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                Gson gson = new Gson();
                String json = responseObject.toString();
                JobsWrapper parseObject = gson.fromJson(json, JobsWrapper.class);
                callResponse.onSuccess(parseObject, responseMessage);
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);

            }


        });
    }
}
