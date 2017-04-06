package cj46.tejas.com.techbodhi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import cj46.tejas.com.techbodhi.Utility.Config;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Carl_johnson on 3/30/2017.
 */

public class AdminEditUserProfile extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog pDialog;
    public String id,gender;
    private TextView uid,username;
    private EditText firstname,lastname,address,dob,contact,email,ssc,ssc_year,hsc,hsc_year,hsc_strem,diploma,diploma_year,diploma_stream,UG,UG_year,UG_stream,PG,PG_Year,PG_stream;
    private String uid1,firstname1,lastname1,address1,dob1,gender1,contact1,email1,username1,ssc_per,ssc_passingyear,hsc_per,hsc_passingyear,hsc_stream,diploma_per,diploma_stream1,diploma_passingyear,graduation_per,graduation_stream,graduation_passingyear,postGrad_per,postGrad_stream,post_passingyear;
    private Button btnEditUserProfile;
    private ImageButton Btndob;
    private int mYear, mMonth, mDay;
    private RadioButton rMale ,rFemale;
    private RadioGroup RadioGenGroup;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituser_profileinfo);

        pDialog = new ProgressDialog(AdminEditUserProfile.this);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("uid");

        uid = (TextView) findViewById(R.id.u_id);
        firstname = (EditText) findViewById(R.id.u_firstname);
        lastname = (EditText) findViewById(R.id.u_lastname);
        address = (EditText) findViewById(R.id.Address);
        dob = (EditText) findViewById(R.id.dateob);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.Email);
        username = (TextView) findViewById(R.id.username);
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

        Btndob = (ImageButton) findViewById(R.id.dateofbirth);

        rMale = (RadioButton) findViewById(R.id.radioMale);
        rFemale = (RadioButton) findViewById(R.id.radioFemale);

        btnEditUserProfile = (Button) findViewById(R.id.btnEditUserProfile);
        //Toast.makeText(getApplicationContext(), "id : - " + id , Toast.LENGTH_SHORT).show();

        btnEditUserProfile.setOnClickListener(this);
        Btndob.setOnClickListener(this);
        //RadioGenGroup.setOnClickListener(this);

        rMale.setOnClickListener(this);
        rFemale.setOnClickListener(this);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.u_firstname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.Vfirstname);
        awesomeValidation.addValidation(this, R.id.u_lastname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.Vlastname);
        awesomeValidation.addValidation(this, R.id.Address, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.Vaddress);
        //awesomeValidation.addValidation(this, R.id.dateob, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.Vdob);
        awesomeValidation.addValidation(this, R.id.contact, "^[2-9]{2}[0-9]{8}$", R.string.Vmobile);
        awesomeValidation.addValidation(this, R.id.Email, Patterns.EMAIL_ADDRESS, R.string.Vemail);
        //awesomeValidation.addValidation(this, R.id.username, "^[a-z0-9_-]{3,15}$", R.string.Vusername);
        awesomeValidation.addValidation(this, R.id.SSC_per, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VSSC_per);
        awesomeValidation.addValidation(this, R.id.SSC_Year,"^[0-9]{4}$", R.string.VSSC_Year);
        awesomeValidation.addValidation(this, R.id.HSC_per, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VHSC_per);
        awesomeValidation.addValidation(this, R.id.HSC_Year, "^[0-9]{4}$", R.string.VHSC_Year);
        awesomeValidation.addValidation(this, R.id.HSC, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VHSC);
        /*awesomeValidation.addValidation(this, R.id.DiplomaPercentage, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VDiplomaPercentage);
        awesomeValidation.addValidation(this, R.id.DiplomaYear, "^[2-9]{2}[0-9]{8}$", R.string.VDiplomaYear);
        awesomeValidation.addValidation(this, R.id.Diploma, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VDiploma);*/
        awesomeValidation.addValidation(this, R.id.UG_PER, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VUG_PER);
        awesomeValidation.addValidation(this, R.id.UG_YEAR, "^[0-9]{4}$", R.string.VUG_YEAR);
        awesomeValidation.addValidation(this, R.id.UG, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VUG);
        awesomeValidation.addValidation(this, R.id.PG_PER, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VPG_PER);
        awesomeValidation.addValidation(this, R.id.PG_YEAR, "^[0-9]{4}$", R.string.VPG_YEAR);
        awesomeValidation.addValidation(this, R.id.PG, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VPG);

        // awesomeValidation.addValidation(this, R.id.dob, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.Vdob);


        new UpdateUserProfile().execute();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View v)
    {
        if (v == btnEditUserProfile)
        {

            if (awesomeValidation.validate()) {
                updateInfo();
            }
        }
        //Toast.makeText(getApplicationContext(),"id" + id, LENGTH_LONG).show();

        if (v == Btndob)
        {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(v == rMale)
        {
            Toast.makeText(getApplicationContext(),"You have Selected Male",Toast.LENGTH_SHORT).show();
            gender = "Male";
            System.out.println("Male :- " + gender );

        }
        if (v == rFemale)
        {
            Toast.makeText(getApplicationContext(),"You have Selected Female",Toast.LENGTH_SHORT).show();
            gender = "Female";
            System.out.println("Female :- " + gender );
        }
    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class UpdateUserProfile extends AsyncTask<Void, Void, Void> {

        String Result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AdminEditUserProfile.this);
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
            if(gender1.equals("Male"))
            {
                gender = "Male";
                rMale.setChecked(true);
            }
            else
            {
                gender = "Female";
                rFemale.setChecked(true);
            }
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
        final String firstname2, lastname2, address2, dob2, gender2, contact2, email2, username2, ssc_per2,
                ssc_passingyear2, hsc_per2, hsc_passingyear2, hsc_stream2, diploma_per2, diploma_stream2, diploma_passingyear2,
                graduation_per2, graduation_stream2, graduation_passingyear2, postGrad_per2, postGrad_stream2,
                post_passingyear2;

        firstname2 = firstname.getText().toString().trim();
        lastname2 = lastname.getText().toString().trim();
        address2 = address.getText().toString().trim();
        dob2 = String.valueOf(dob.getText());
        gender2 = gender;
        contact2 = contact.getText().toString().trim();
        email2 = email.getText().toString().trim();
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

        class UpdateUserinfo extends AsyncTask<Void, Void, String>
        {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(EditUserProfile.this, "Adding...", "Wait...", false, false);;
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> userinfo = new HashMap<>();
                userinfo.put("uid", id);
                userinfo.put(Config.KEY_USER_FIRSTNAME, firstname2);
                userinfo.put(Config.KEY_USER_LASTNAME, lastname2);
                userinfo.put(Config.KEY_ADDRESS, address2);
                userinfo.put(Config.KEY_DOB, dob2);
                userinfo.put(Config.KEY_GENDER, gender2);
                userinfo.put(Config.KEY_MOBILE, contact2);
                userinfo.put(Config.KEY_EMAIL, email2);
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
                final String jsonStr = rh.sendPostRequest(Config.URL_UPDATE_USERINFO, userinfo);


                System.out.println("Result :-" + jsonStr.toString());

                if (!jsonStr.equals("Success"))
                {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(),"Updated Successfully....", Toast.LENGTH_SHORT).show();
                            Intent e = new Intent(AdminEditUserProfile.this, AdminPanelViewUserDetails.class);
                            e.putExtra("uid",id);
                            startActivity(e);
                        }
                    });
                }
                else
                {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(), "Please try Again....", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return jsonStr;
            }

            @Override
            protected void onPostExecute(String aVoid)
            {
                super.onPostExecute(aVoid);

            }

        }

        UpdateUserinfo obj = new UpdateUserinfo();
        obj.execute();
    }
}