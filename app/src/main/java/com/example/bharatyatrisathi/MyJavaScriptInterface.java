package com.example.bharatyatrisathi;

import android.webkit.JavascriptInterface;

public class MyJavaScriptInterface {
    private Minigames mActivity;

    public MyJavaScriptInterface(Minigames activity) {
        mActivity = activity;
    }

    @JavascriptInterface
    public void onGameInteraction(String interactionData) {
        // Handle the game interaction data here in the Minigames activity
        mActivity.handleGameInteraction(interactionData);
    }
}
