package cj46.tejas.com.techbodhi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.HashMap;

import cj46.tejas.com.techbodhi.R;
import cj46.tejas.com.techbodhi.ViewPostDetails;
import cj46.tejas.com.techbodhi.ViewPosts;

/**
 * Created by Carl_johnson on 2/2/2017.
 */

public class ViewPostAdapter extends BaseAdapter
{

    private static LayoutInflater inflater = null;
    public Resources res;
    public String post_id;
    private Activity activity;
    private ArrayList<HashMap<String ,String>> data;

    public ViewPostAdapter(Activity a, ArrayList d, Resources resLoc)
    {
        activity = a;
        data = d;
        res = resLoc;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount()
    {
        if(data.size()<= 0)
        return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView == null)
        {
            vi = inflater.inflate(R.layout.view_post_list,null);

            holder = new ViewHolder();
            holder.company_id = (TextView) vi.findViewById(R.id.p_id);
            holder.company_name = (TextView) vi.findViewById(R.id.companyName);
            holder.note = (TextView) vi.findViewById(R.id.Note);
            holder.deadline = (TextView) vi.findViewById(R.id.deadline);
            holder.companyProfile = (TextView) vi.findViewById(R.id.companyProfile);
            holder.companyCode = (TextView) vi.findViewById(R.id.CompanyCode);
            holder.JobDiscription = (TextView) vi.findViewById(R.id.jobDiscription);
            holder.OtherSkills = (TextView) vi.findViewById(R.id.OtherSkills);
            holder.Responsiblity = (TextView) vi.findViewById(R.id.Responsibility);
            holder.SkillsRequired = (TextView) vi.findViewById(R.id.SkillsRequired);
            holder.percentage = (TextView) vi.findViewById(R.id.Percentage);
            holder.SalaryRange = (TextView) vi.findViewById(R.id.salaryRange);
            holder.InterviewProcess = (TextView) vi.findViewById(R.id.InterviewProcess);
            holder.JobLocation = (TextView) vi.findViewById(R.id.JobLoction);
            holder.TestLocation = (TextView) vi.findViewById(R.id.testLocation);


            holder.btnViewPostDetails = (Button) vi.findViewById(R.id.btnViewPostDetails);

            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();

            if(data.size()<=0)
            {
                holder.company_id.setText("No data");
            }
            else
            {

            }

            final HashMap tempValues = data.get(position);

            holder.company_id.setText(tempValues.get("p_id").toString());
            holder.company_name.setText(tempValues.get("p_companyName").toString());
            holder.note.setText(tempValues.get("p_note").toString());
            holder.deadline.setText(tempValues.get("p_deadline").toString());
            holder.companyProfile.setText(tempValues.get("p_companyProfile").toString());
            holder.companyCode.setText(tempValues.get("p_companyCode").toString());
            holder.JobDiscription.setText(tempValues.get("p_jobDescription").toString());
            holder.OtherSkills.setText(tempValues.get("p_otherSkills").toString());
            holder.Responsiblity.setText(tempValues.get("p_responsibility").toString());
            holder.SkillsRequired.setText(tempValues.get("p_skillsRequired").toString());
            holder.percentage.setText(tempValues.get("p_percentageCriteria").toString());
            holder.SalaryRange.setText(tempValues.get("p_salaryRange").toString());
            holder.InterviewProcess.setText(tempValues.get("p_interviewProcess").toString());
            holder.JobLocation.setText(tempValues.get("p_jobLocation").toString());
            holder.TestLocation.setText(tempValues.get("p_testLocation").toString());
            holder.btnViewPostDetails = (Button) vi.findViewById(R.id.btnViewPostDetails);

            holder.btnViewPostDetails.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    String id =(String) tempValues.get("p_id");
                    post_id = id;
                    EditUser();
                }
            });

            vi.setTag(holder);

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    public void EditUser()
    {

        Intent i = new Intent(activity.getApplicationContext(), ViewPostDetails.class );
        i.putExtra("pid",post_id);
        activity.startActivity(i);

    }


    public static class ViewHolder
    {

        public TextView company_id ,company_name, note , deadline, companyProfile , companyCode ,JobDiscription , OtherSkills, Responsiblity ,SkillsRequired , percentage ,SalaryRange ,
        InterviewProcess ,JobLocation , TestLocation;
        Button btnViewPostDetails;

    }

    private class OnItemClickListener implements OnClickListener
    {
        private int mPostion;

        OnItemClickListener(int position)
        {
            mPostion = position;
            System.out.println("id"+mPostion);
        }

        @Override
        public void onClick(View v)
        {

            ViewPosts obj=(ViewPosts) activity;

            obj.onItemClick(mPostion);


        }
    }

}
