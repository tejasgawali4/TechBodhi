package cj46.tejas.com.techbodhi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 12/7/2016.
 */

    public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText firstname, lastname,Date, email, password, address, username, mobile ;
    private ImageButton dob;
    private TextView error;
    private RadioButton gender;
    private RadioGroup RadioGenGroup;
    private Button btnRegister;
    private int mYear, mMonth, mDay;
    private String g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeruser);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.Password);
        address = (EditText) findViewById(R.id.address);
        RadioGenGroup = (RadioGroup) findViewById(R.id.radioSex);
        mobile = (EditText) findViewById(R.id.mobile);
        Date = (EditText) findViewById(R.id.dob);
        error = (TextView) findViewById(R.id.register_error);

        dob = (ImageButton) findViewById(R.id.dateofbirth);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
        dob.setOnClickListener(this);
        RadioGenGroup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        if (v == btnRegister) {
            registerUser();
        }
        if (v == dob) {

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

                                Date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        if(v == RadioGenGroup)
        {
            int gen = RadioGenGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            gender = (RadioButton) findViewById(gen);

            g = gender.getText().toString().trim();
            System.out.println("ok 1" + g);
            System.out.println("ok 2" +  gender.getText());
           Toast.makeText(RegisterUser.this,gender.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {

        final String firstname1 = firstname.getText().toString().trim();
        final String lastname1 = lastname.getText().toString().trim();
        final String address1 = address.getText().toString().trim();
        final String dob1 = Date.getText().toString().trim();
        final String gender1  = g;
        final String mobile1 = mobile.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String username1 = username.getText().toString().trim();
        final String password1 = password.getText().toString().trim();

        System.out.println("firstname" +firstname1+ "lastname" +lastname1+ "address" +address1+ "dob" +dob1+ "gender" +gender1+ "mobile" +mobile1+ "email" +email1+ "username" +username1+ "password" +password1);


        class Register extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterUser.this, "Adding...", "Wait...", false, false);
            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                loading.dismiss();
                String response = s;
                System.out.println(response);
                try {
                    JSONObject parentObject = new JSONObject(response);
                    //And then read attributes like
                    String name = parentObject.getString("success");
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                    if (name.equals("success"))
                    {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        error.setText("Please Enter valid info");
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... v)
            {

                HashMap<String, String> UserData = new HashMap<>();
                UserData.put(Config.KEY_USER_FIRSTNAME, firstname1);
                UserData.put(Config.KEY_USER_LASTNAME, lastname1);
                UserData.put(Config.KEY_ADDRESS, address1);
                UserData.put(Config.KEY_DOB, dob1);
                UserData.put(Config.KEY_GENDER, "male");
                UserData.put(Config.KEY_MOBILE, mobile1);
                UserData.put(Config.KEY_EMAIL, email1);
                UserData.put(Config.KEY_USERNAME, username1);
                UserData.put(Config.KEY_PASSWORD, password1);


                System.out.println(UserData.keySet() + "\n" + UserData.values());
                HttpConnection register = new HttpConnection();
                System.out.println("before API call");

                String res = register.sendPostRequest(Config.URL_ADD_USER, UserData);
                System.out.println("after API call");
                return res;
            }
        }
            Register obj = new Register();
            obj.execute();
    }
}


