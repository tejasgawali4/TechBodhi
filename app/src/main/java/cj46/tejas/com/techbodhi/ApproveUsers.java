package cj46.tejas.com.techbodhi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


import cj46.tejas.com.techbodhi.Adapters.ApproveUserAdapter;
import cj46.tejas.com.techbodhi.Utility.Config;

public class ApproveUsers extends Activity {

    ListView t;
    ProgressDialog pDialog;
    public ApproveUsers CustomListView = null;
    public ArrayList<HashMap<String,String>> CustomListViewValuesArr;
    ApproveUserAdapter adapter;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.approveusers);
        CustomListView = this;
        pDialog = new ProgressDialog(ApproveUsers.this);
        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        System.out.println("In MainActivity ... Before Setting List Data....");
        CustomListViewValuesArr = new ArrayList<>();

        System.out.println("In MainActivity ... After Setting List Data....");
        res = getResources();
        t = (ListView) findViewById(R.id.ApproveUsers);  // List defined in XML ( See Below )
        System.out.println("In onCreate...ApproveUSer class()...");
        new Main().execute();
        System.out.println("In onCreate...After Main Execute()...");
        /**************** Create Custom Adapter *********/


    }

    public void onItemClick(int mPosition)
    {
        System.out.println("In MainActivity...onItemClick()...");
/*        Toast.makeText(getApplicationContext(),"This is Main Activity OnItemCLick", LENGTH_LONG).show();*/
        System.out.println("In MainActivity...onItemClick()...");

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    /******
     * Function to set data in ArrayList
     *************/


    private class Main extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();
            if(sh==null)
            {
                System.out.println("In setListData ... Checking HttpConnectioon....");
            }else
            {
                System.out.println("In setListData in ELSE... Checking HttpConnectioon....");

            }

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_APPROVAL_USER);

           //System.out.println("json :- " +jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String uid = jsonResponce.getString("u_id");
                        String firstname = jsonResponce.getString("u_fisrtname");
                        String lastname = jsonResponce.getString("u_lastname");
                        String u_address = jsonResponce.getString("u_address");
                        String u_dob = jsonResponce.getString("u_dob");
                        String u_gender = jsonResponce.getString("u_gender");
                        String u_contact = jsonResponce.getString("u_contact");
                        String u_email = jsonResponce.getString("u_email");
                        String u_username = jsonResponce.getString("u_username");


                       System.out.println("In MainActivity ... Setting List Data....uid ->" + uid + " u_firstname-->" +firstname+" u_lastname-->"+lastname);


                    /*  btnApprove.setTag(uid);
                        btnReject.setTag(uid);*/

/*                        final ListModel sched = new ListModel();

                        // adding each child node to HashMap key => value
                        sched.setUid("uid" + uid);
                        sched.setFirstname("firstname" + firstname);
                        sched.setLastname("lastname" + lastname);*/
                        HashMap<String, String> User = new HashMap<>();

                        // adding each child node to HashMap key => value
                        User.put("u_id",uid);
                        User.put("u_firstname", firstname);
                        User.put("u_lastname", lastname);
                        User.put("u_address", u_address);
                        User.put("u_dob", u_dob);
                        User.put("u_gender", u_gender);
                        User.put("u_contact", u_contact);
                        User.put("u_email", u_email);
                        User.put("u_username", u_username);

                        //  System.out.println("In MainActivity ... Setting List Data...." + i);


                        // adding contact to contact list
                        CustomListViewValuesArr.add(User);
                    }
                } catch (final JSONException e) {
                    //     Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Json parsing error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            //System.out.println("In MainActivity ... Before MyListAdaper....");
            adapter = new ApproveUserAdapter(CustomListView, CustomListViewValuesArr, res);
            //System.out.println("In MainActivity ... After MyListAdaper....");

            t.setAdapter(adapter);

        }
    }
    /*****************
     * This function used by adapter
     ****************/
}



