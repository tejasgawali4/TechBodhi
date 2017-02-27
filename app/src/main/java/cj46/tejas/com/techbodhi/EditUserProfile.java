package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Carl_johnson on 2/24/2017.
 */

public class EditUserProfile extends AppCompatActivity implements View.OnClickListener
{
    private ProgressDialog pDialog;
    SessionManger session;
    public String id;
    private TextView uid;
    private EditText firstname,lastname,address,dob,gender,contact,email,username,ssc,ssc_year,hsc,hsc_year,hsc_strem,diploma,diploma_year,diploma_stream,UG,UG_year,UG_stream,PG,PG_Year,PG_stream;
    private String uid1,firstname1,lastname1,address1,dob1,gender1,contact1,email1,username1,ssc_per,ssc_passingyear,hsc_per,hsc_passingyear,hsc_stream,diploma_per,diploma_stream1,diploma_passingyear,graduation_per,graduation_stream,graduation_passingyear,postGrad_per,postGrad_stream,post_passingyear;
    private Button btnEditUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituser_profileinfo);

        session = new SessionManger(getApplicationContext());

        pDialog = new ProgressDialog(EditUserProfile.this);

        HashMap<String, String> User1 = session.getUserDetails();

        id = User1.get(SessionManger.KEY_ID);

        uid = (TextView) findViewById(R.id.u_id);
        firstname = (EditText) findViewById(R.id.u_firstname);
        lastname = (EditText) findViewById(R.id.u_lastname);
        address = (EditText) findViewById(R.id.Address);
        dob = (EditText) findViewById(R.id.dateob);
        gender = (EditText) findViewById(R.id.gender);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.Email);
        username = (EditText) findViewById(R.id.username);
        ssc = (EditText) findViewById(R.id.SSC_per);
        ssc_year = (EditText) findViewById(R.id.SSC_Year);
        hsc = (EditText) findViewById(R.id.HSC_per);
        hsc_year = (EditText) findViewById(R.id.HSC_Year);
        hsc_strem = (EditText) findViewById(R.id.HSC);
        diploma = (EditText) findViewById(R.id.DiplomaPercentage);
        diploma_year = (EditText) findViewById(R.id.DiplomaYear);
        diploma_stream = (EditText) findViewById(R.id.Diploma);
        UG = (EditText) findViewById(R.id.UG_PER);
        UG_year = (EditText) findViewById(R.id.UG_YEAR);
        UG_stream = (EditText) findViewById(R.id.UG);
        PG = (EditText) findViewById(R.id.PG_PER);
        PG_Year = (EditText) findViewById(R.id.PG_YEAR);
        PG_stream = (EditText) findViewById(R.id.PG);

        btnEditUserProfile = (Button) findViewById(R.id.btnEditUserProfile);
        //Toast.makeText(getApplicationContext(), "id : - " + id , Toast.LENGTH_SHORT).show();

        btnEditUserProfile.setOnClickListener(this);

        new UpdateUserProfile().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    public void onClick(View v)
    {
        //Toast.makeText(getApplicationContext(),"id" + id, LENGTH_LONG).show();
        updateInfo();
    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class UpdateUserProfile extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(EditUserProfile.this);
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

                        uid1 =jsonResponce.getString("u_id");
                        firstname1 = jsonResponce.getString("u_fisrtname");
                        lastname1 = jsonResponce.getString("u_lastname");
                        address1 = jsonResponce.getString("u_address");
                        dob1 = jsonResponce.getString("u_dob");
                        gender1 = jsonResponce.getString("u_gender");
                        contact1 = jsonResponce.getString("u_contact");
                        email1 = jsonResponce.getString("u_email");
                        username1 = jsonResponce.getString("u_username");
                        ssc_per = jsonResponce.getString("u_ssc_per");
                        ssc_passingyear = jsonResponce.getString("u_ssc_passingyear");
                        hsc_per = jsonResponce.getString("u_hsc_per");
                        hsc_passingyear = jsonResponce.getString("u_hsc_passingyear");
                        hsc_stream = jsonResponce.getString("u_hsc_stream");
                        diploma_per = jsonResponce.getString("u_diploma_per");
                        diploma_stream1 =jsonResponce.getString("u_diploma_stream");
                        diploma_passingyear = jsonResponce.getString("u_diploma_passingyear");
                        graduation_per = jsonResponce.getString("u_graduation_per");
                        graduation_stream = jsonResponce.getString("u_graduation_stream");
                        graduation_passingyear = jsonResponce.getString("u_graduation_passingyear");
                        postGrad_per = jsonResponce.getString("u_postGrad_per");
                        postGrad_stream = jsonResponce.getString("u_postGrad_stream");
                        post_passingyear = jsonResponce.getString("u_post_passingyear");



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

            uid.setText(uid1);
            firstname.setText(firstname1);
            lastname.setText(lastname1);
            address.setText(address1);
            dob.setText(dob1);
            gender.setText(gender1);
            contact.setText(contact1);
            email.setText(email1);
            username.setText(username1);
            ssc.setText(ssc_per);
            ssc_year.setText(ssc_passingyear);
            hsc.setText(hsc_per);
            hsc_year.setText(hsc_passingyear);
            hsc_strem.setText(hsc_stream);
            diploma.setText(diploma_per);
            diploma_year.setText(diploma_passingyear);
            diploma_stream.setText(diploma_stream1);
            UG.setText(graduation_per);
            UG_year.setText(graduation_passingyear);
            UG_stream.setText(graduation_stream);
            PG.setText(postGrad_per);
            PG_Year.setText(post_passingyear);
            PG_stream.setText(postGrad_stream);


        }
    }

    void updateInfo()
    {
        final String firstname2,lastname2,address2,dob2,gender2,contact2,email2,username2,ssc_per2,
                ssc_passingyear2,hsc_per2,hsc_passingyear2,hsc_stream2,diploma_per2,diploma_stream2,diploma_passingyear2,
                graduation_per2,graduation_stream2,graduation_passingyear2,postGrad_per2,postGrad_stream2,
                post_passingyear2;

        firstname2 = firstname.getText().toString().trim();
        lastname2 = lastname.getText().toString().trim();
        address2 = address.getText().toString().trim();
        dob2 = dob.getText().toString().trim();
        gender2 = gender.getText().toString().trim();
        contact2 = contact.getText().toString().trim();
        email2 = email.getText().toString().trim();
        username2 = username.getText().toString().trim();
        ssc_per2 = ssc.getText().toString().trim();
        ssc_passingyear2 = ssc_year.getText().toString().trim();
        hsc_per2 = hsc.getText().toString().trim();
        hsc_passingyear2 = hsc_year.getText().toString().trim();
        hsc_stream2 = hsc_strem.getText().toString().trim();
        diploma_per2 = diploma.getText().toString().trim();
        diploma_passingyear2 = diploma_year.getText().toString().trim();
        diploma_stream2 = diploma_stream.getText().toString().trim();
        graduation_per2 = UG.getText().toString().trim();
        graduation_passingyear2 = UG_year.getText().toString().trim();
        graduation_stream2 = UG_stream.getText().toString().trim();
        postGrad_per2 = PG.getText().toString().trim();
        post_passingyear2 = PG_Year.getText().toString().trim();
        postGrad_stream2 = PG_stream.getText().toString().trim();

        class UpdateUserinfo extends AsyncTask
        {
            ProgressDialog loading;

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                loading.dismiss();
                finish();
                startActivity(getIntent());
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditUserProfile.this, "Adding...", "Wait...", false, false);;

            }

            @Override
            protected Object doInBackground(Object[] params) {

                HashMap<String, String> userinfo = new HashMap<>();
                userinfo.put("uid",id);
                userinfo.put(Config.KEY_USER_FIRSTNAME, firstname2);
                userinfo.put(Config.KEY_USER_LASTNAME, lastname2);
                userinfo.put(Config.KEY_ADDRESS, address2);
                userinfo.put(Config.KEY_DOB, dob2);
                userinfo.put(Config.KEY_GENDER, gender2);
                userinfo.put(Config.KEY_MOBILE, contact2);
                userinfo.put(Config.KEY_EMAIL, email2);
                userinfo.put(Config.KEY_USERNAME, username2);
                userinfo.put(Config.KEY_SSC_PER, ssc_per2);
                userinfo.put(Config.KEY_SSC_PASSING_YEAR, ssc_passingyear2);
                userinfo.put(Config.KEY_HSC_PER, hsc_per2);
                userinfo.put(Config.KEY_HSC_PASSING_YEAR, hsc_passingyear2);
                userinfo.put(Config.KEY_HSC_STREAM, hsc_stream2);
                userinfo.put(Config.KEY_DIPLOMA_PER, diploma_per2);
                userinfo.put(Config.KEY_DIPLOMA_STREAM, diploma_stream2);
                userinfo.put(Config.KEY_DIPLOMA_PASSING_YEAR, diploma_passingyear2);
                userinfo.put(Config.KEY_GRADUATION_PER, graduation_per2);
                userinfo.put(Config.KEY_GRADUATION_STREAM, graduation_stream2);
                userinfo.put(Config.KEY_GRADUATION_PASSING_YEAR, graduation_passingyear2);
                userinfo.put(Config.KEY_POST_GRAD_PER, postGrad_per2);
                userinfo.put(Config.KEY_POST_GRAD_STREAM, postGrad_stream2);
                userinfo.put(Config.KEY_POST_GRAD_PASSING_YEAR, post_passingyear2);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_UPDATE_USERINFO, userinfo);
                return res;
            }

        }

        UpdateUserinfo obj = new UpdateUserinfo();
        obj.execute();

    }

}
