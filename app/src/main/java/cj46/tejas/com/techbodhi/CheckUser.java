package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carl_johnson on 12/6/2016.
 */
public class CheckUser extends AppCompatActivity {
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetUserDetails();
    }

    public void GetUserDetails() {
        String id = Config.TAG_USER_ID;//editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "User id Problem Re-Login", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = Config.URL_VIEW_USER_BYID + id;//editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckUser.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String id = "";
        String firstname = "";
        String lastname = "";
        String username ="";
        String password = "";
        String bithdate = "";
        String address = "";
        String city = "";
        String mobile = "";
        String status ="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject UserData = result.getJSONObject(0);

            id = UserData.getString(Config.USER_ID);
            firstname = UserData.getString(Config.KEY_USER_FIRSTNAME);
            lastname = UserData.getString(Config.KEY_USER_LASTNAME);
            username = UserData.getString(Config.KEY_USERNAME);
            password = UserData.getString(Config.KEY_PASSWORD);
            bithdate = UserData.getString(Config.KEY_DOB);
            address = UserData.getString(Config.KEY_ADDRESS);
            city = UserData.getString(Config.KEY_CITY);
            mobile = UserData.getString(Config.KEY_MOBILE);
            status = UserData.getString(Config.KEY_USER_STATUS);

            System.out.println("id :" +id+ "firstname:" +firstname+ "lastname:"+lastname+ "username:"+username+ "password:"+password+
                    "bithdate:" +bithdate+ "address:" +address+"city:" +city+ "mobile:" +mobile+ "status:" +status);

            loading.dismiss();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
