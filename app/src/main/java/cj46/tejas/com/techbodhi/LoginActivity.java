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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cj46.tejas.com.techbodhi.Utility.Config;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button Login, Register;
    EditText edtUsername, edtPassword;
    TextView ErrorLogin;
    String uid,firstname1,status, username1;

    SessionManger session;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnRegister);
        edtUsername = (EditText) findViewById(R.id.loginEmail);
        edtPassword = (EditText) findViewById(R.id.loginPassword);
        ErrorLogin = (TextView) findViewById(R.id.login_error);

        session = new SessionManger(getApplicationContext());

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.loginEmail, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.error_field_required);
        awesomeValidation.addValidation(this, R.id.loginPassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.error_field_required);

        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onClick(View v)
    {
        if (v == Login)
        {
            if (awesomeValidation.validate())
            {
                //Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                NavigateToMain();
                //process the data further
            }
        }
        if (v == Register) {
            NavigateToRegister();
        }
    }


    private void NavigateToRegister()
    {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    private void NavigateToMain()
    {

        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        if(username != null && !password.isEmpty())
        {
            class Login extends AsyncTask<Void, Void, String>
            {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(String s)
                {
                    super.onPostExecute(s);
                }

                @Override
                protected String doInBackground(Void... v) {

                    HashMap<String, String> UserData = new HashMap<>();

                    UserData.put(Config.KEY_USERNAME, username);
                    UserData.put(Config.KEY_PASSWORD, password);

                    HttpConnection sh = new HttpConnection();

                    // Making a request to url and getting response
                    String jsonStr = sh.sendPostRequest(Config.URL_CHECK_USER,UserData);

                    if (jsonStr != null)
                    {
                        try {
                            JSONObject jsonObj = new JSONObject(jsonStr);

                            // Getting JSON Array node
                            JSONArray result = jsonObj.getJSONArray("result");

                            // looping through All Contacts
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject jsonResponce = result.getJSONObject(i);

                                uid=jsonResponce.getString("u_id");
                                username1 = jsonResponce.getString("u_username");
                                firstname1 = jsonResponce.getString("u_fisrtname");
                                status = jsonResponce.getString("status");

                                System.out.println(uid +"\n"+ username1 +"\n"+ firstname1 +"\n"+ status);

                                session.createLoginSession(uid,username1,firstname1,status);

                                if (status.equals("1"))
                                {
                                    System.out.println("User");
                                    //Toast.makeText(getApplicationContext(),"Welcome"+firstname1,Toast.LENGTH_SHORT).show();
                                    Intent loginUser = new Intent(getApplicationContext(), UserLoginActivity.class);
                                    startActivity(loginUser);

                                    runOnUiThread(new Runnable()
                                    {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),"Welcome "+firstname1,Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                                else if (status.equals("2"))
                                {
                                    System.out.println("admin");
                                    //Toast.makeText(getApplicationContext(),"Welcome"+firstname1,Toast.LENGTH_SHORT).show();
                                    Intent loginAdmin = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(loginAdmin);
                                    runOnUiThread(new Runnable()
                                    {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),"Welcome "+firstname1,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else if (status.equals(null))
                                {
                                    runOnUiThread(new Runnable()
                                    {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),"Invalid User Please Contact Adminstrator.",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }
                        }
                        catch (final JSONException e)
                        {
                            //Log.e(TAG, "Json parsing error: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                              /*      Toast.makeText(getApplicationContext(),
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();*/
                              ErrorLogin.setText("Please Enter valid info....");
                                }
                            });
                        }

                    }
                    else
                    {
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

                    return jsonStr;
                }
            }

            Login obj = new Login();
            obj.execute();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Please Enter Valid info..",Toast.LENGTH_SHORT).show();
            ErrorLogin.setText("Please Enter Username/password....");
        }


    }
}

