package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 12/5/2016.
 */
public class ApproveUsers extends AppCompatActivity implements View.OnClickListener {

    ArrayList<HashMap<String, String>> viewuserarray;
    private String TAG = ViewPosts.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView ApproveListView;
    private ImageButton btnApprove,btnReject;
    private ImageView profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approveusers);

        viewuserarray = new ArrayList<>();

        ApproveListView = (ListView) findViewById(R.id.ApproveUsers);


        new ApproveUser().execute();

    }

    @Override
    public void onClick(View v) {

        if(btnApprove==v)
        {
            Approve();
            Toast.makeText(ApproveUsers.this,"approve",Toast.LENGTH_SHORT).show();
        }
        if(btnReject==v)
        {
            Reject();
            Toast.makeText(ApproveUsers.this,"Rejected..",Toast.LENGTH_SHORT).show();
        }
    }

    public void Approve()
    {

        final String u_id = "5";
        final String status = "1";

        class ApproveU extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ApproveUsers.this, "Checking...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(ApproveUsers.this, s, Toast.LENGTH_LONG).show();
                if(s.equals("success"))
                {
                    //Navigate to Activity
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.USER_ID, u_id);
                params.put(Config.KEY_USER_STATUS, status);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_APPROVE, params);
                return res;
            }
        }

        ApproveU aprv = new ApproveU();
        aprv.execute();
    }

    public void Reject()
    {
        Toast.makeText(getApplicationContext(),"Rejected...",Toast.LENGTH_LONG).show();
    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class ApproveUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ApproveUsers.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_APPROVAL_USER);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        profileView = (ImageView) findViewById(R.id.profileuser);
                        String uid = jsonResponce.getString("u_id");
                        String firstname = jsonResponce.getString("u_firstname");
                        String lastname = jsonResponce.getString("u_lastname");
                        btnApprove= (ImageButton) findViewById(R.id.btnApproveUser);
                        btnReject= (ImageButton) findViewById(R.id.btnRejectUser);


/*                        btnApprove.setTag(uid);
                        btnReject.setTag(uid);*/

                        // tmp hash map for single contact
                        HashMap<String, String> User = new HashMap<>();

                        // adding each child node to HashMap key => value
                        User.put(Config.USER_ID,uid);
                        User.put(Config.KEY_USER_FIRSTNAME, firstname);
                        User.put(Config.KEY_USER_LASTNAME, lastname);


                        // adding contact to contact list
                        viewuserarray.add(User);
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

                    ApproveUsers.this, viewuserarray, R.layout.view_userapprovals,
                    new String[]{"firstname", "lastname"}, new int[]{R.id.u_firstname, R.id.u_lastname});

            ApproveListView.setAdapter(adapter);
        }
    }
}
