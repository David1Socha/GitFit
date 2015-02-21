package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raik383h_group_6.healthtracmobile.view.LoginWebViewActivity;

public class LoginWebViewPresenter extends BasePresenter<LoginWebViewActivity> {
    public static String DUMMY_CALLBACK = "http://www.example.com/oauth_callback";
    private class LoginWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(DUMMY_CALLBACK))) { // Don't open callback url
                //view.stop / hide
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter("VERIFIER NAME TODO CHANGEME") == null) { //Check if we're getting called back because of OAuth cancellation
                    //view.finishInShame();
                } else {
                    //view.saveToken(uri);
                }
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }
    }
}
