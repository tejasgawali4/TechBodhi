package cj46.tejas.com.techbodhi;

/**
 * Created by Carl_johnson on 2/22/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class ViewUserAdapter extends BaseAdapter
{

    private static LayoutInflater inflater = null;
    public Resources res;
    public String User_id;
    private Activity activity;
    private ArrayList<HashMap<String ,String>> data;

    public ViewUserAdapter(Activity a, ArrayList d, Resources resLoc)
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
            vi = inflater.inflate(R.layout.view_user_list,null);

            holder = new ViewHolder();
            holder.uid = (TextView) vi.findViewById(R.id.u_id);
            holder.firstname = (TextView) vi.findViewById(R.id.u_firstname);
            holder.lastname = (TextView) vi.findViewById(R.id.u_lastname);
            holder.address = (TextView) vi.findViewById(R.id.Address);
            holder.dob = (TextView) vi.findViewById(R.id.dateob);
            holder.gender = (TextView) vi.findViewById(R.id.gender);
            holder.contact = (TextView) vi.findViewById(R.id.contact);
            holder.email = (TextView) vi.findViewById(R.id.Email);
            holder.username = (TextView) vi.findViewById(R.id.username);


            holder.btnViewUserDetails = (Button) vi.findViewById(R.id.btnViewUserDetails);

            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();

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

                holder.btnViewUserDetails = (Button) vi.findViewById(R.id.btnViewUserDetails);

                holder.btnViewUserDetails.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        String id =(String) tempValues.get("u_id");
                        User_id = id;
                        ViewUser();
                    }
                });
                vi.setTag(holder);

                vi.setOnClickListener(new OnItemClickListener(position));
            }
        }
        return vi;
    }

    public void ViewUser()
    {
       Intent i = new Intent(activity.getApplicationContext(), AdminPanelViewUserDetails.class );
        i.putExtra("uid",User_id);
        activity.startActivity(i);
    }


    public static class ViewHolder
    {

        public TextView uid,firstname,lastname,address,dob,gender,contact,email,username;
        Button btnViewUserDetails;

    }

    public class OnItemClickListener implements View.OnClickListener
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
