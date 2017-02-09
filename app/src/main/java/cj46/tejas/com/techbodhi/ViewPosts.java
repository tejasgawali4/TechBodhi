package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

public class ViewPosts extends AppCompatActivity
{
    private ProgressDialog pDialog;
    ListView postListView;
    public ViewPosts CustomViewPost = null;
    public ArrayList<HashMap<String,String>> CustomListPostViewArray;
    ViewPostAdapter adapter;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpost);

        CustomViewPost = this;

        pDialog = new ProgressDialog(ViewPosts.this);

        CustomListPostViewArray = new ArrayList<>();

        res = getResources();

        postListView= (ListView) findViewById(R.id.viewpost);

        new ViewPost().execute();
    }

    public void onItemClick(int mPostion)
    {
     // Toast.makeText(getApplicationContext(),"mPostion" +mPostion, LENGTH_LONG).show();
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

           // Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String companyId =jsonResponce.getString("p_id");
                        String comapanyName = jsonResponce.getString("p_companyName");
                        String companyProfile = jsonResponce.getString("p_companyProfile");



                        System.out.println(comapanyName);
                        System.out.println(companyProfile);

                        // tmp hash map for single contact
                        HashMap<String, String> Post = new HashMap<>();

                        // adding each child node to HashMap key => value
                        Post.put(Config.KEY_POST_ID,companyId);
                        Post.put(Config.KEY_COMPANYNAME, comapanyName);
                        Post.put(Config.KEY_COMPANYPROFILE,companyProfile);

                        /*Post.put(Config.KEY_CONTENT, content);*/

                        // adding contact to contact list
                        CustomListPostViewArray.add(Post);
                    }
                } catch (final JSONException e) {
                   // Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!", LENGTH_LONG).show();
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

            adapter = new ViewPostAdapter(CustomViewPost, CustomListPostViewArray, res);

            postListView.setAdapter(adapter);


        }
    }
}
