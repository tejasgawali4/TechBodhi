package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Carl_johnson on 2/23/2017.
 */

public class AdminPanelViewUserDetails extends AppCompatActivity {


    private ProgressDialog pDialog;
    ListView UserListView;
    public AdminPanelViewUserDetails CustomViewUser = null;
    public ArrayList<HashMap<String,String>> CustomListUserViewArray;
    AdminPanelViewUserAdapter adapter;
    Resources res;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpanel_viewuserdetail);

        CustomViewUser = this;

        pDialog = new ProgressDialog(AdminPanelViewUserDetails.this);

        CustomListUserViewArray = new ArrayList<>();

        res = getResources();

        UserListView= (ListView) findViewById(R.id.view_full_userDetails);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("uid");

        Toast.makeText(getApplicationContext(), "id : - " + id , Toast.LENGTH_SHORT).show();

        new AdminPanelViewUserDetails.ViewUserDetails().execute();
    }

    public void onItemClick(int mPostion)
    {
        // Toast.makeText(getApplicationContext(),"mPostion" +mPostion, LENGTH_LONG).show();
    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class ViewUserDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AdminPanelViewUserDetails.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_USER_BYID + id );

            // Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String uid =jsonResponce.getString("u_id");
                        String firstname = jsonResponce.getString("u_fisrtname");
                        String lastname = jsonResponce.getString("u_lastname");
                        String address = jsonResponce.getString("u_address");
                        String dob = jsonResponce.getString("u_dob");
                        String gender = jsonResponce.getString("u_gender");
                        String contact = jsonResponce.getString("u_contact");
                        String email = jsonResponce.getString("u_email");
                        String username = jsonResponce.getString("u_username");
                        String ssc_per = jsonResponce.getString("u_ssc_per");
                        String ssc_passingyear = jsonResponce.getString("u_ssc_passingyear");
                        String hsc_per = jsonResponce.getString("u_hsc_per");
                        String hsc_passingyear = jsonResponce.getString("u_hsc_passingyear");
                        String hsc_stream = jsonResponce.getString("u_hsc_stream");
                        String diploma_per = jsonResponce.getString("u_diploma_per");
                        String diploma_stream =jsonResponce.getString("u_diploma_stream");
                        String diploma_passingyear = jsonResponce.getString("u_diploma_passingyear");
                        String graduation_per = jsonResponce.getString("u_graduation_per");
                        String graduation_stream = jsonResponce.getString("u_graduation_stream");
                        String graduation_passingyear = jsonResponce.getString("u_graduation_passingyear");
                        String postGrad_per = jsonResponce.getString("u_postGrad_per");
                        String postGrad_stream = jsonResponce.getString("u_postGrad_stream");
                        String post_passingyear = jsonResponce.getString("u_post_passingyear");


                        // tmp hash map for single contact
                        HashMap<String, String> User = new HashMap<>();

                        // adding each child node to HashMap key => value
                        User.put(Config.USER_ID,uid);
                        User.put(Config.KEY_USER_FIRSTNAME, firstname);
                        User.put(Config.KEY_USER_LASTNAME,lastname);
                        User.put(Config.KEY_ADDRESS,address);
                        User.put(Config.KEY_DOB,dob);
                        User.put(Config.KEY_GENDER,gender);
                        User.put(Config.KEY_MOBILE,contact);
                        User.put(Config.KEY_EMAIL,email);
                        User.put(Config.KEY_USERNAME,username);
                        User.put(Config.KEY_SSC_PER,ssc_per);
                        User.put(Config.KEY_SSC_PASSING_YEAR,ssc_passingyear);
                        User.put(Config.KEY_HSC_PER,hsc_per);
                        User.put(Config.KEY_HSC_PASSING_YEAR,hsc_passingyear);
                        User.put(Config.KEY_HSC_STREAM,hsc_stream);
                        User.put(Config.KEY_DIPLOMA_PER,diploma_per);
                        User.put(Config.KEY_DIPLOMA_STREAM,diploma_stream);
                        User.put(Config.KEY_DIPLOMA_PASSING_YEAR,diploma_passingyear);
                        User.put(Config.KEY_GRADUATION_PER,graduation_per);
                        User.put(Config.KEY_GRADUATION_STREAM,graduation_stream);
                        User.put(Config.KEY_GRADUATION_PASSING_YEAR,graduation_passingyear);
                        User.put(Config.KEY_POST_GRAD_PER,postGrad_per);
                        User.put(Config.KEY_POST_GRAD_STREAM,postGrad_stream);
                        User.put(Config.KEY_POST_GRAD_PASSING_YEAR,post_passingyear);

                        /*Post.put(Config.KEY_CONTENT, content);*/

                        // adding contact to contact list
                        CustomListUserViewArray.add(User);
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

            adapter = new AdminPanelViewUserAdapter(CustomViewUser, CustomListUserViewArray, res);

            UserListView.setAdapter(adapter);


        }
    }
}
