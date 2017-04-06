package cj46.tejas.com.techbodhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import cj46.tejas.com.techbodhi.Utility.Config;

public class MainActivity extends MasterBaseActivity {
    WebView webView;
    Context ctx;
    String activityName;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.setLayout(R.layout.activity_main);
        super.setDrawerID(R.id.main_layout);
        super.onCreate(savedInstanceState);
        ctx = this.getBaseContext();
        activityName = "MainActivity";
        setContextFromActivity(ctx, activityName);


        //Initializing textview
        t1 = (TextView) findViewById(R.id.Txtview1);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");

        //Showing the current logged in email to textview
        t1.setText("Current User: " + username);

    }

//        setContentView(R.layout.activity_main);

/*
        webView = (WebView) findViewById(R.id.webView);
        // force web view to open inside application
        webView.setWebViewClient(new MyWebViewClient());
        openURL();*/

/*    private void openURL() {
        webView.loadUrl("http://www.netersontech.com/aboutus.php");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/
}
