package cj46.tejas.com.techbodhi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;
import java.util.HashMap;

import cj46.tejas.com.techbodhi.Utility.Config;

/**
 * Created by Carl_johnson on 12/5/2016.
 */

public class  AddPost extends AppCompatActivity implements View.OnClickListener {


    private EditText CompanyName, Note , Deadline , CompanyProfile , CompanyCode , JobDiscription , otherSkills , responsibility ,
            SkilledRequired  , PercentageCriteria , salaryRange , InterviewProcess , JobLocation , testLocation ;
    private Button buttonAdd;
    private ImageButton ImageButtonDeadLine1;
    private int mYear, mMonth, mDay;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        CompanyName = (EditText) findViewById(R.id.companyName);
        Note = (EditText) findViewById(R.id.Note);
        Deadline = (EditText) findViewById(R.id.Deadline);
        CompanyProfile = (EditText) findViewById(R.id.CompanyProfile);
        CompanyCode = (EditText) findViewById(R.id.CompanyCode);
        JobDiscription = (EditText) findViewById(R.id.jobDiscription);
        otherSkills = (EditText) findViewById(R.id.OtherSkills);
        responsibility = (EditText) findViewById(R.id.Responsibility);
        SkilledRequired = (EditText) findViewById(R.id.SkillsRequire);
        PercentageCriteria = (EditText) findViewById(R.id.Percentage);
        salaryRange = (EditText) findViewById(R.id.salaryRange);
        InterviewProcess = (EditText) findViewById(R.id.InterviewProcess);
        JobLocation = (EditText) findViewById(R.id.JobLoction);
        testLocation = (EditText) findViewById(R.id.testLocation);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.companyName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VcompanyName);
        awesomeValidation.addValidation(this, R.id.Note, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VNote);
        awesomeValidation.addValidation(this, R.id.CompanyProfile, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VCompanyProfile);
        awesomeValidation.addValidation(this, R.id.CompanyCode, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VCompanyCode);
        awesomeValidation.addValidation(this, R.id.jobDiscription, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VjobDiscription);
        awesomeValidation.addValidation(this, R.id.OtherSkills, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VOtherSkills);
        awesomeValidation.addValidation(this, R.id.Responsibility, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VResponsibility);
        awesomeValidation.addValidation(this, R.id.SkillsRequire, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VSkillsRequire);
        awesomeValidation.addValidation(this, R.id.Percentage, "^\\d{0,2}(\\.\\d{1,4})? *%?$", R.string.VPercentage);
        awesomeValidation.addValidation(this, R.id.salaryRange, "(^\\d*\\.?\\d*[1-9]+\\d*$)|(^[1-9]+\\d*\\.\\d*$)", R.string.VsalaryRange);
        awesomeValidation.addValidation(this, R.id.InterviewProcess, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VInterviewProcess);
        awesomeValidation.addValidation(this, R.id.JobLoction, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VJobLoction);
        awesomeValidation.addValidation(this, R.id.testLocation, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.VtestLocation);

        ImageButtonDeadLine1 = (ImageButton) findViewById(R.id.ImageButtonDeadLine);
        buttonAdd = (Button) findViewById(R.id.btnAddPost);

        buttonAdd.setOnClickListener(this);
        ImageButtonDeadLine1.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
}

    @Override
    public void onBackPressed()
    {
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
    public void onClick(View v) {
        if (v == buttonAdd)
        {

            if (awesomeValidation.validate())
            {
                AddPost();
            }
        }

        if (v == ImageButtonDeadLine1)
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

                            Deadline.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
    private void AddPost() {

        final String CompanyName1 = CompanyName.getText().toString().trim();
        final String Note1 = Note.getText().toString().trim();
        final String Deadline1 = Deadline.getText().toString().trim();
        final String CompanyProfile1 = CompanyProfile.getText().toString().trim();
        final String CompanyCode1 = CompanyCode.getText().toString().trim();
        final String JobDiscription1 = JobDiscription.getText().toString().trim();
        final String otherSkills1 = otherSkills.getText().toString().trim();
        final String responsibility1 = responsibility.getText().toString().trim();
        final String SkilledRequired1 = SkilledRequired.getText().toString().trim();
        final String PercentageCriteria1 = PercentageCriteria.getText().toString().trim();
        final String salaryRange1 = salaryRange.getText().toString().trim();
        final String InterviewProcess1 = InterviewProcess.getText().toString().trim();
        final String JobLocation1 = JobLocation.getText().toString().trim();
        final String testLocation1 = testLocation.getText().toString().trim();


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

                if(!s.equals("success"))
                {
                    Toast.makeText(AddPost.this, "job post added sucessfully...!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),AddPost.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(AddPost.this, "Try Again...!", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_COMPANYNAME, CompanyName1);
                params.put(Config.KEY_NOTE, Note1);
                params.put(Config.KEY_DEADLINE, Deadline1);
                params.put(Config.KEY_COMPANYPROFILE, CompanyProfile1);
                params.put(Config.KEY_COMPANYCODE, CompanyCode1);
                params.put(Config.KEY_JOBDISCRIPTION, JobDiscription1);
                params.put(Config.KEY_OTHERSKILLS, otherSkills1);
                params.put(Config.KEY_RESPONSIBILITY, responsibility1);
                params.put(Config.KEY_SKILLREQUIRES, SkilledRequired1);
                params.put(Config.KEY_PERCENTAGE, PercentageCriteria1);
                params.put(Config.KEY_SALARYRANGE, salaryRange1);
                params.put(Config.KEY_INTERVIEWPROCESS, InterviewProcess1);
                params.put(Config.KEY_JOBLOCATION, JobLocation1);
                params.put(Config.KEY_TESTLOCATION, testLocation1);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_ADD_POST, params);
                return res;
            }
        }

        Add_Post obj = new Add_Post();
        obj.execute();
    }

}
