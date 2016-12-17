package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPosts extends AppCompatActivity {

    private String TAG = ViewPosts.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView postListView;
    private Button btnViewPostDeatails;

    ArrayList<HashMap<String, String>> viewpostarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpost);

        viewpostarray = new ArrayList<>();

        postListView = (ListView) findViewById(R.id.viewpost);
        btnViewPostDeatails = (Button) findViewById(R.id.btnViewPostDetails);


        new ViewPost().execute();
    }

    public void ViewPostDetails(View v)
    {

    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class ViewPost extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ViewPosts.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_POSTS);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String heading = jsonResponce.getString("p_heading");
                        String content = jsonResponce.getString("p_content");


                        System.out.println(heading);
                        System.out.println(content);

                        // tmp hash map for single contact
                        HashMap<String, String> Post = new HashMap<>();

                        // adding each child node to HashMap key => value
                        Post.put(Config.KEY_HEADING, heading);
/*                        Post.put(Config.KEY_CONTENT, content);*/

                        // adding contact to contact list
                        viewpostarray.add(Post);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(

                    ViewPosts.this, viewpostarray, R.layout.view_post_list,
                    new String[]{"Heading"}, new int[]{R.id.Heading});

            postListView.setAdapter(adapter);

        }
    }
}
