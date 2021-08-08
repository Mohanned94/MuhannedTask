package com.example.muhanned_task.Managers;


public interface ApiCallHandler {
    void onSuccess(Object responseObject, String responseMessage);


    void onFailure(String errorResponse);
}
