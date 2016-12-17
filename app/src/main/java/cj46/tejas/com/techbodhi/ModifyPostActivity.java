package cj46.tejas.com.techbodhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Carl_johnson on 12/14/2016.
 */
public class ModifyPostActivity extends AppCompatActivity {

    private EditText editTextHeading;
    private EditText editTextContent;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        editTextHeading = (EditText) findViewById(R.id.Heading);
        editTextContent = (EditText) findViewById(R.id.Content);

        FetchPosts();
        buttonAdd = (Button) findViewById(R.id.btnAddPost);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPost();
            }
        });

    }
    public void ModifyPost()
    {

    }
    public void FetchPosts()
    {

    }


}
