package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button Login, Register;
    EditText edtUsername, edtPassword;
    TextView ErrorLogin;

    SessionManger session;

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

        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v == Login) {
            NavigateToMain();
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



        class Login extends AsyncTask<Void, Void, String>
        {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
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

                                String uid=jsonResponce.getString("u_id");
                                String username = jsonResponce.getString("u_username");
                                String firstname = jsonResponce.getString("u_fisrtname");
                                String status = jsonResponce.getString("status");

                                System.out.println(uid);
                                System.out.println(username);
                                System.out.println(firstname);
                                System.out.println(status);

                                session.createLoginSession(uid,username,firstname,status);

                                if (status.equals("1"))
                                {
                                    System.out.println("User");
                                    Intent loginUser = new Intent(getApplicationContext(), UserLoginActivity.class);
                                    startActivity(loginUser);
                                    //Toast.makeText(getApplicationContext(),"User",Toast.LENGTH_SHORT).show();
                                }
                                else if (status.equals("2"))
                                {
                                    System.out.println("admin");
                                    Intent loginAdmin = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(loginAdmin);
                                    //Toast.makeText(getApplicationContext(),"Admin",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    
                                }
                            }
                    }
                    catch (final JSONException e)
                    {
                        //Log.e(TAG, "Json parsing error: " + e.getMessage());
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
}



/*
    private void NavigateToMain() {
        edtUsername.getText().toString().trim();
        edtPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_CHECK_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        String response=response1;
                        try {
                            JSONObject parentObject = new JSONObject(response);
                            //And then read attributes like
                            String name = parentObject.getString("success");
                            Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                            if(name.equals("admin"))
                            {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                   */
                                /* Intent intent = new Intent(this, MainActivity.class);
                                    intent.putExtra(Config.KEY_USERNAME, username);*//*

                            }
                            else if(name.equals("user"))
                            {

                                Intent i = new Intent(getApplicationContext(), UserLoginActivity.class);
                                startActivity(i);
                              */
                                /*      Intent intent = new Intent(this, UserLoginActivity.class);
                                    intent.putExtra(Config.KEY_USERNAME, username);*//*

                            }
                            else
                            {
                                ErrorLogin.setText("Invalid User");
                                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(Config.KEY_USERNAME,edtUsername);
                map.put(Config.KEY_PASSWORD,edtPassword);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/
