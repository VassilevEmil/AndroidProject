package com.example.androidproject.Model.NewsModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.Entities.NewsModel;
import com.example.androidproject.R;

import java.util.List;

public class webView extends AppCompatActivity {

    WebView webView;
    List<NewsModel> modelList;
    NewsModel test;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webview);


        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        System.out.println("eeeeeeeeeeeeeeee" + link);

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(link);
            webView.getSettings().setJavaScriptEnabled(true);


    }

}
