package com.example.bharatyatrisathi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Minigames extends AppCompatActivity {
    WebView webView;
    ProgressBar pgbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigames);

        webView = findViewById(R.id.WebView);
        pgbar = findViewById(R.id.progressbar);

        // Enable JavaScript in the WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Add the JavaScript interface
        MyJavaScriptInterface jsInterface = new MyJavaScriptInterface(this);
        webView.addJavascriptInterface(jsInterface, "Android");

        webView.loadUrl("https://poki.com/en/minigames");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pgbar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pgbar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
    }

    // Method to handle game interactions
    public void handleGameInteraction(String interactionData) {
        // Handle the game interaction data received from JavaScript
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
