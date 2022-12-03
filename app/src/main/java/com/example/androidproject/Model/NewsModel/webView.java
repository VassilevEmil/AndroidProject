package com.example.androidproject.Model.NewsModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.Entities.News.NewsModel;
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

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(link);
            webView.getSettings().setJavaScriptEnabled(true);


    }

}
