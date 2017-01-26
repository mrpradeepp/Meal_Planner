package com.example.lenovo.sample_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class View_url extends AppCompatActivity {

    WebView urlview;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_url);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
       url= getIntent().getStringExtra("Url_data");
        urlview = (WebView) findViewById(R.id.view_url);
        urlview.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");

        urlview.getSettings().setJavaScriptEnabled(true);
        urlview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        urlview.getSettings().setSupportMultipleWindows(true);
        urlview.setWebViewClient(new WebViewClient());
        urlview.setWebChromeClient(new WebChromeClient());
        urlview.loadUrl(url);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
