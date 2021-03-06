package cj46.tejas.com.techbodhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 2/23/2017.
 */

public class AdminPanelViewUserAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    public Resources res;
    public String User_id;
    private Activity activity;
    private ArrayList<HashMap<String ,String>> data;

    public AdminPanelViewUserAdapter(Activity a, ArrayList d, Resources resLoc)
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
        AdminPanelViewUserAdapter.ViewHolder holder;

        if(convertView == null)
        {
            vi = inflater.inflate(R.layout.adminpanel_viewuser_list_detail,null);

            holder = new AdminPanelViewUserAdapter.ViewHolder();
            holder.uid = (TextView) vi.findViewById(R.id.u_id);
            holder.firstname = (TextView) vi.findViewById(R.id.u_firstname);
            holder.lastname = (TextView) vi.findViewById(R.id.u_lastname);
            holder.address = (TextView) vi.findViewById(R.id.Address);
            holder.dob = (TextView) vi.findViewById(R.id.dateob);
            holder.gender = (TextView) vi.findViewById(R.id.gender);
            holder.contact = (TextView) vi.findViewById(R.id.contact);
            holder.email = (TextView) vi.findViewById(R.id.Email);
            holder.username = (TextView) vi.findViewById(R.id.username);
            holder.ssc = (TextView) vi.findViewById(R.id.SSC_per);
            holder.ssc_year = (TextView) vi.findViewById(R.id.SSC_Year);
            holder.hsc = (TextView) vi.findViewById(R.id.HSC_per);
            holder.hsc_year = (TextView) vi.findViewById(R.id.HSC_Year);
            holder.hsc_strem = (TextView) vi.findViewById(R.id.HSC);
            holder.diploma = (TextView) vi.findViewById(R.id.DiplomaPercentage);
            holder.diploma_year = (TextView) vi.findViewById(R.id.DiplomaYear);
            holder.diploma_stream = (TextView) vi.findViewById(R.id.Diploma);
            holder.UG = (TextView) vi.findViewById(R.id.UG_PER);
            holder.UG_year = (TextView) vi.findViewById(R.id.UG_YEAR);
            holder.UG_stream = (TextView) vi.findViewById(R.id.UG);
            holder.PG = (TextView) vi.findViewById(R.id.PG_PER);
            holder.PG_Year = (TextView) vi.findViewById(R.id.PG_YEAR);
            holder.PG_stream = (TextView) vi.findViewById(R.id.PG);


            holder.btnViewUserDetails = (Button) vi.findViewById(R.id.btnViewUserDetails);

            vi.setTag(holder);
        }
        else
        {
            holder = (AdminPanelViewUserAdapter.ViewHolder) vi.getTag();

            if(data.size()<=0)
            {
                holder.uid.setText("No data");
            }
            else
            {
                final HashMap tempValues = data.get(position);

                holder.uid.setText(tempValues.get("u_id").toString());
                holder.firstname.setText(tempValues.get("u_fisrtname").toString());
                holder.lastname.setText(tempValues.get("u_lastname").toString());
                holder.address.setText(tempValues.get("u_address").toString());
                holder.dob.setText(tempValues.get("u_dob").toString());
                holder.gender.setText(tempValues.get("u_gender").toString());
                holder.contact.setText(tempValues.get("u_contact").toString());
                holder.email.setText(tempValues.get("u_email").toString());
                holder.username.setText(tempValues.get("u_username").toString());
                holder.ssc.setText(tempValues.get("u_ssc_per").toString());
                holder.ssc_year.setText(tempValues.get("u_ssc_passingyear").toString());
                holder.hsc.setText(tempValues.get("u_hsc_per").toString());
                holder.hsc_year.setText(tempValues.get("u_hsc_passingyear").toString());
                holder.hsc_strem.setText(tempValues.get("u_hsc_stream").toString());
                holder.diploma.setText(tempValues.get("u_diploma_per").toString());
                holder.diploma_stream.setText(tempValues.get("u_diploma_stream").toString());
                holder.diploma_year.setText(tempValues.get("u_diploma_passingyear").toString());
                holder.UG.setText(tempValues.get("u_graduation_per").toString());
                holder.UG_stream.setText(tempValues.get("u_graduation_stream").toString());
                holder.UG_year.setText(tempValues.get("u_graduation_passingyear").toString());
                holder.PG.setText(tempValues.get("u_postGrad_per").toString());
                holder.PG_stream.setText(tempValues.get("u_postGrad_stream").toString());
                holder.PG_Year.setText(tempValues.get("u_post_passingyear").toString());


                holder.btnViewUserDetails = (Button) vi.findViewById(R.id.btnViewUserDetails);

                holder.btnViewUserDetails.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        String id =(String) tempValues.get("u_id");
                        User_id = id;
                        EditUser();
                    }
                });
                vi.setTag(holder);

                vi.setOnClickListener(new AdminPanelViewUserAdapter.OnItemClickListener(position));
            }
        }
        return vi;
    }

    public void EditUser()
    {
        Intent i = new Intent(activity.getApplicationContext(), AdminPanelEditUser.class );
        i.putExtra("uid",User_id);
        activity.startActivity(i);
    }


    public static class ViewHolder
    {

        public TextView uid,firstname,lastname,address,dob,gender,contact,email,username,ssc,ssc_year,hsc,hsc_year,hsc_strem,diploma,diploma_year,diploma_stream,UG_year,UG,PG,UG_stream,PG_Year,PG_stream;
        Button btnViewUserDetails;

    }

    private class OnItemClickListener implements View.OnClickListener
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

            AdminPanelViewUserDetails obj=(AdminPanelViewUserDetails) activity;

            obj.onItemClick(mPostion);


        }
    }
}
