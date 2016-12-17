package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 12/7/2016.
 */

    public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText firstname, lastname, email, password, address, city, mobile;

    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeruser);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.Password);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        mobile = (EditText) findViewById(R.id.mobile);


        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            registerUser();
        }
    }

    private void registerUser() {

        final String firstname1 = firstname.getText().toString().trim();
        final String lastname1 = lastname.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        final String address1 = address.getText().toString().trim();
        final String city1 = city.getText().toString().trim();
        final String mobile1 = mobile.getText().toString().trim();


        class Register extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterUser.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterUser.this, s, Toast.LENGTH_LONG).show();

                if(s.equals("success"))
                {
                    Intent NavToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(NavToLogin);
                }
            }

            @Override
            protected String doInBackground(Void... v) {

                HashMap<String, String> UserData = new HashMap<>();
                UserData.put(Config.KEY_USER_FIRSTNAME, firstname1);
                UserData.put(Config.KEY_USER_LASTNAME, lastname1);
                UserData.put(Config.KEY_USERNAME, email1);
                UserData.put(Config.KEY_PASSWORD, password1);
                UserData.put(Config.KEY_ADDRESS, address1);
                UserData.put(Config.KEY_CITY, city1);
                UserData.put(Config.KEY_MOBILE, mobile1);
                UserData.put(Config.KEY_MOBILE, mobile1);

                HttpConnection register = new HttpConnection();
                String res = register.sendPostRequest(Config.URL_ADD_USER, UserData);
                return res;

            }
        }
            Register obj = new Register();
            obj.execute();
    }
}


