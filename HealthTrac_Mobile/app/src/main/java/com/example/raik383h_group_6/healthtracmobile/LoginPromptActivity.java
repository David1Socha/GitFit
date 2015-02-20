package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.raik383h_group_6.healthtracmobile.models.User;
import com.example.raik383h_group_6.healthtracmobile.teams.TeamPageFragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class LoginPromptActivity extends Activity {

    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;
    private String accessToken, accessSecret, provider;
    private final String loginURL = "http://gitfit.azurewebsites.net/api/Account/Login";
 //   public User user;
    public User user = new User(123,"test","lol","idk","stuff", User.Sex.Female,12,12,"420","USERNAME","2020");
    public LoginPromptActivity activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();
            switch (requestCode) { //TODO finish this activity and return oauth codes
                case FB_LOGIN_REQ: accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                    accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                    provider = getString(R.string.PROVIDER_FACEBOOK);
                    break;
                case TW_LOGIN_REQ: accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                    accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                    provider = getString(R.string.PROVIDER_TWITTER);
                    break;
                default: break;
            }
            Log.d("accessToken", accessToken);
            Log.d("accessSecret", accessSecret);
            Log.d("provider", provider);


            new GetUser().execute();
      //      while(getUser.getStatus() != AsyncTask.Status.FINISHED) {

//            }

        }
    }

    public void loginTwitter(View v) {
        Intent intent = new Intent(this, TwitterLoginWebViewActivity.class);
        startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void loginFacebook(View v) {
        Intent intent = new Intent(this, FacebookLoginWebViewActivity.class);
        startActivityForResult(intent, FB_LOGIN_REQ);
    }

    class GetUser extends AsyncTask<Void, Void, User> {

        String userCredentials = "";

        @Override
        protected User doInBackground(Void... params) {
            InputStream stream = null;
            User user = null;
            try {

                HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(loginURL);

                JSONObject loginInfo = new JSONObject();
                loginInfo.accumulate("Provider", provider);
                loginInfo.accumulate("Secret", accessSecret);
                loginInfo.accumulate("Token", accessToken);

                userCredentials = loginInfo.toString();

                StringEntity entity = new StringEntity(userCredentials);

                post.setEntity(entity);

                post.setHeader("Content-Type", "application/json");

                HttpResponse response = client.execute(post);

                stream = response.getEntity().getContent();

                if (stream != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    String line = "";
                    String result = "";
                    while ((line = reader.readLine()) != null) {
                        result += line + "\n";
                    }
                    stream.close();

                    JSONObject userInfo = new JSONObject(result);

                    long id = userInfo.getLong("id");
                    String firstName = userInfo.getString("FirstName");
                    String lastName = userInfo.getString("LastName");
                    String preferredName = userInfo.getString("PreferredName");
                    String email = userInfo.getString("email");
                    User.Sex sex = User.Sex.valueOf(userInfo.getString("Sex"));
                    int height = userInfo.getInt("Height");
                    int weight = userInfo.getInt("Width");
                    String date = userInfo.getString("Date");
                    String userName = userInfo.getString("String");
                    String dateCreated = userInfo.getString("DateCreated");

                    user = new User(id, firstName, lastName, preferredName, email, sex, height, weight, date, userName, dateCreated);


                    System.out.println(result);
                    System.out.println(userInfo.get("userName"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            return user;
        }

        @Override
        protected void onPostExecute(User user1) {
            Intent i = new Intent();
            Bundle b = new Bundle();
            b.putParcelable("User", user);
            i.putExtras(b);
            i.setClass(activity, NavigationActivity.class);
            startActivity(i);
        }
    }
}

