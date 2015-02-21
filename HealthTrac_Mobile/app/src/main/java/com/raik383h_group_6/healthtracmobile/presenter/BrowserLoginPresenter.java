package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapterFactory;
import com.raik383h_group_6.healthtracmobile.view.BrowserLoginActivity;

public abstract class BrowserLoginPresenter extends BasePresenter<BrowserLoginActivity> {

    private IOAuthServiceAdapter oAuthService;
    private Token requestToken;
    public static String DUMMY_CALLBACK = "http://www.example.com/oauth_callback";

    protected abstract String getVerifierName();

    protected abstract IOAuthServiceAdapter buildOAuthServiceAdapter(IOAuthServiceAdapterFactory factory);

    @Inject
    public BrowserLoginPresenter(IOAuthServiceAdapterFactory factory) {
        oAuthService = buildOAuthServiceAdapter(factory);

    }

    private void saveToken(Uri uri) {
        final String verifier = uri.getQueryParameter(getVerifierName());

        (new AsyncTask<Void, Void, Token>() {
            @Override
            protected Token doInBackground(Void... params) {
                return oAuthService.getAccessToken(requestToken, verifier);
            }

            @Override
            protected void onPostExecute(Token accessToken) {
                //saveTokenAndFinish(accessToken);
            }
        }).execute();
    }

    private void beginAuthorization() {
        (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try { //OAuth 2.0 doesn't use request tokens...
                    requestToken = oAuthService.getRequestToken();
                    return oAuthService.getAuthorizationUrl(requestToken);
                } catch (UnsupportedOperationException e) {
                    return oAuthService.getAuthorizationUrl(null);
                }
            }

            @Override
            protected void onPostExecute(String url) {
                //webView.loadUrl(url);
            }
        }).execute();
    }

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
