package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Carl_johnson on 2/16/2017.
 */

public class UserPanelViewPostDetails extends AppCompatActivity
{
    private ProgressDialog pDialog, loading;
    String id,name;
    public Button btnApplyJob;
    public TextView pid , pcompanyName ,pNote , pDeadline ,
            pCompanyProfile, pCompanyCode ,pJobDiscription,
            pOtherSkills, pResponsibilty , pSkilledRequired ,
            pPercentageCriteria , pSalaryRange , pInterviewProcess ,
            pjoblocation , PTestlocation;

    SessionManger session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpanel_viewpostdetails);

        pDialog = new ProgressDialog(UserPanelViewPostDetails.this);

        loading = new ProgressDialog(UserPanelViewPostDetails.this);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("pid");

        //Toast.makeText(getApplicationContext(), "id : - " + id , Toast.LENGTH_SHORT).show();

        session = new SessionManger(getApplicationContext());

        pid = (TextView) findViewById(R.id.p_id);
        pcompanyName = (TextView) findViewById(R.id.companyName);
        pNote = (TextView) findViewById(R.id.Note);
        pDeadline = (TextView) findViewById(R.id.deadline);
        pCompanyProfile = (TextView) findViewById(R.id.companyProfile);
        pCompanyCode = (TextView) findViewById(R.id.CompanyCode);
        pJobDiscription = (TextView) findViewById(R.id.jobDiscription);
        pOtherSkills = (TextView) findViewById(R.id.OtherSkills);
        pResponsibilty = (TextView) findViewById(R.id.Responsibility);
        pSkilledRequired = (TextView) findViewById(R.id.SkillsRequired);
        pPercentageCriteria = (TextView) findViewById(R.id.Percentage);
        pSalaryRange = (TextView) findViewById(R.id.salaryRange);
        pInterviewProcess = (TextView) findViewById(R.id.InterviewProcess);
        pjoblocation = (TextView) findViewById(R.id.JobLoction);
        PTestlocation = (TextView) findViewById(R.id.testLocation);

        btnApplyJob = (Button) findViewById(R.id.btnViewPostDetails);

        new ViewPost().execute();

        btnApplyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new ApplyPost().execute();

                /*Intent ViewPost = new Intent(UserPanelViewPostDetails.this,UserPanelViewPost.class);
                startActivity(ViewPost);*/
            }
        });
    }

    protected class ViewPost extends AsyncTask<Void, Void, Void>
    {

        String companyId , companyName , companyNote , companyDeadline , companyProfile, companyCode , jobDescription , otherSkills ,
                responsibility , skillsRequired , percentageCriteria , salaryRange , interviewProcess , jobLocation , testLocation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(UserPanelViewPostDetails.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_VIEWPOSTS_BY_ID + id );

            // Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        companyId = jsonResponce.getString("p_id");
                        companyName = jsonResponce.getString("p_companyName");
                        companyNote = jsonResponce.getString("p_note");
                        companyDeadline = jsonResponce.getString("p_deadline");
                        companyProfile = jsonResponce.getString("p_companyProfile");
                        companyCode = jsonResponce.getString("p_companyCode");
                        jobDescription = jsonResponce.getString("p_jobDescription");
                        otherSkills = jsonResponce.getString("p_otherSkills");
                        responsibility = jsonResponce.getString("p_responsibility");
                        skillsRequired = jsonResponce.getString("p_skillsRequired");
                        percentageCriteria = jsonResponce.getString("p_percentageCriteria");
                        salaryRange = jsonResponce.getString("p_salaryRange");
                        interviewProcess = jsonResponce.getString("p_interviewProcess");
                        jobLocation = jsonResponce.getString("p_jobLocation");
                        testLocation = jsonResponce.getString("p_testLocation");


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


            pid.setText(companyId);
            pcompanyName.setText(companyName);
            pNote.setText(companyNote);
            pDeadline.setText(companyDeadline);
            pCompanyProfile.setText(companyProfile);
            pCompanyCode.setText(companyCode);
            pJobDiscription.setText(jobDescription);
            pOtherSkills.setText(otherSkills);
            pResponsibilty.setText(responsibility);
            pSkilledRequired.setText(skillsRequired);
            pPercentageCriteria.setText(percentageCriteria);
            pSalaryRange.setText(salaryRange);
            pInterviewProcess.setText(interviewProcess);
            pjoblocation.setText(jobLocation);
            PTestlocation.setText(testLocation);

        }

    }


    class ApplyPost extends AsyncTask<Object, Object, String>
    {

        HashMap<String, String> User1 = session.getUserDetails();

        String User_id = User1.get(SessionManger.KEY_ID);

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();

            }

            @Override
            protected void onCancelled(String aVoid) {
                super.onCancelled(aVoid);
            }

            @Override
            protected void onProgressUpdate(Object... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(Object... params)
            {
                HashMap<String, String> UserData = new HashMap<>();

                System.out.println("User session id " + User_id);
                UserData.put(Config.USER_ID, User_id);
                UserData.put(Config.KEY_POST_ID, id);

                HttpConnection sh = new HttpConnection();

                // Making a request to url and getting response
                String jsonStr = sh.sendPostRequest(Config.URL_APPLY_FOR_JOB, UserData);

                if (jsonStr != null) {
                    try
                    {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        name = jsonObj.getString("result");

                        System.out.println(" ID :- " + name );

                    }
                    catch (final JSONException e)
                    {
                        //Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable()
                        {
                                @Override
                                public void run()
                                {
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

        }

}
