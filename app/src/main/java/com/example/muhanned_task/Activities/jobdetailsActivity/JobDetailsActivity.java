package com.example.muhanned_task.Activities.jobdetailsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.muhanned_task.R;

public class JobDetailsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValue();
    }

    private void initValue() {
        String link;
        Bundle extras = getIntent().getExtras();

        link = extras.getString("Link");

        webView = findViewById(R.id.webView);
        webView.loadUrl(link);
    }
}