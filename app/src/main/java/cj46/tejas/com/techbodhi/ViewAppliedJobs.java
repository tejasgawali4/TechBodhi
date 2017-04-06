package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cj46.tejas.com.techbodhi.Utility.Config;
import cj46.tejas.com.techbodhi.Adapters.ViewAppliedJobsAdapter;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Carl_johnson on 3/24/2017.
 */

public class ViewAppliedJobs extends AppCompatActivity
{
    private ProgressDialog pDialog;
    ListView AppledJobListView;
    public ViewAppliedJobs CustomViewAppliedJob = null;
    public ArrayList<HashMap<String,String>> CustomListAppliedJobViewArray;
    Resources res;
    String u_id;

    SessionManger session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewappliedjobs);

        session = new SessionManger(getApplicationContext());

        HashMap<String, String> User1 = session.getUserDetails();

        u_id = User1.get(SessionManger.KEY_ID);

        CustomViewAppliedJob = this;

        pDialog = new ProgressDialog(ViewAppliedJobs.this);

        CustomListAppliedJobViewArray = new ArrayList<>();

        res = getResources();

        AppledJobListView= (ListView) findViewById(R.id.viewAppliedJob);

        new ViewApplied().execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ViewAppliedJobs.this,UserLoginActivity.class);
        startActivity(i);

    }

    public class ViewApplied extends AsyncTask<Void, Void, Void> {

        String firstname,company;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ViewAppliedJobs.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpConnection sh = new HttpConnection();

            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_APPLIED_JOBS + u_id );

            System.out.println("User Id :- " + u_id);

            System.out.println("json" + jsonStr);

            if (jsonStr != null)
            {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        firstname =jsonResponce.getString("u_fisrtname");
                        company = jsonResponce.getString("p_companyName");

                        System.out.println("Firstname :- " + firstname + "Company :-" +company);

                        HashMap<String, String> Jobs = new HashMap<>();

                        Jobs.put(Config.KEY_USER_FIRSTNAME,firstname);
                        Jobs.put(Config.KEY_COMPANYNAME,company);

                        /*Post.put(Config.KEY_CONTENT, content);*/

                        // adding contact to contact list
                        CustomListAppliedJobViewArray.add(Jobs);

                        //System.out.println("firstname :- " + firstname + "company :- " + company);
                    }
                } catch (final JSONException e) {
                    // Log.e(TAG, "Json parsing error: " + e.getMessage());
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
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (pDialog.isShowing())
                pDialog.dismiss();

            String[] from={"u_fisrtname","p_companyName"};//string array
            int[] to={R.id.username1,R.id.company1};//int array of views id's
            SimpleAdapter simpleAdapter=new ViewAppliedJobsAdapter(CustomViewAppliedJob,CustomListAppliedJobViewArray,R.layout.view_applied_job_listitem,from,to);//Create object and set the parameters for simpleAdapter
            AppledJobListView.setAdapter(simpleAdapter);//sets the adapter for listView

        }

    }
}
