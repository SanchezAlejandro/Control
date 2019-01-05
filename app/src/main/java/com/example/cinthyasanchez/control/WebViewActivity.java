package com.example.cinthyasanchez.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        myView = findViewById(R.id.webView);
        myView.getSettings().setJavaScriptEnabled(true);
        myView.loadUrl("http://192.168.137.217/26/on");//holis crack WH
        finish();
    }
}
