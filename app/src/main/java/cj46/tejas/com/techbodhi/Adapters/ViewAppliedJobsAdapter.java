package cj46.tejas.com.techbodhi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cj46.tejas.com.techbodhi.R;
import cj46.tejas.com.techbodhi.ViewAppliedJobs;

/**
 * Created by Carl_johnson on 3/24/2017.
 */

public class ViewAppliedJobsAdapter extends SimpleAdapter {


    LayoutInflater inflater;
    Context context;
    ArrayList<HashMap<String, String>> arrayList;
    String CollegeId;

    public ViewAppliedJobsAdapter(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to)
    {
        super(context, data, resource, from, to);
        this.context = context;
        this.arrayList = data;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.view_applied_job_listitem, null);
            holder = new ViewHolder();

            holder.username=(TextView) convertView.findViewById(R.id.username1);
            holder.comapanyName=(TextView) convertView.findViewById(R.id.company1);

            convertView.setTag(holder);

        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.username.setText(arrayList.get(position).get("u_fisrtname"));
        holder.comapanyName.setText(arrayList.get(position).get("p_companyName"));

        return convertView;
    }

    static class ViewHolder
    {
        public TextView username,comapanyName;

    }
}
