package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 12/5/2016.
 */

public class  AddPost extends AppCompatActivity {


    private EditText editTextHeading;
    private EditText editTextContent;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        editTextHeading = (EditText) findViewById(R.id.Heading);
        editTextContent = (EditText) findViewById(R.id.Content);


        buttonAdd = (Button) findViewById(R.id.btnAddPost);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPost();
            }
        });

    }

    private void AddPost() {

        final String heading = editTextHeading.getText().toString().trim();
        final String content = editTextContent.getText().toString().trim();

        class Add_Post extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddPost.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddPost.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_HEADING, heading);
                params.put(Config.KEY_CONTENT, content);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_ADD_POST, params);
                return res;
            }
        }

        Add_Post obj = new Add_Post();
        obj.execute();
    }
}
