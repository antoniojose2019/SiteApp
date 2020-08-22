package com.ajdevmobile.siteapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URI;


public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://tiagoaguiar.co";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarWebView();

    }


    private void configurarWebView(){

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        webView = findViewById(R.id.webView_app_site);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                progressBar.setVisibility(View.VISIBLE);

            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.INVISIBLE);

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //return super.shouldOverrideUrlLoading(view, request);

                if(request.getUrl().getHost().equals("tiagoaguiar.co")) {
                    return false;
                }


                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                return true;
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return super.shouldOverrideUrlLoading(view, url);

                if(Uri.parse(url).getHost().equals("tiagoaguiar.co")) {
                    return false;
                }


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;

            }

        });


        webView.loadUrl(url);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
             webView.goBack();
             return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}