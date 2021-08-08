package com.example.muhanned_task.Adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muhanned_task.Models.JobsModel;
import com.example.muhanned_task.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context activity;
    List<JobsModel> jobsModels;
    private OnItemClickListener mItemClickListener;
    private MyViewHolder selectedHolder;

    public HomeAdapter(Context activity, List<JobsModel> jobsModels) {
        this.activity = activity;
        this.jobsModels = jobsModels;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_page_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvJobtitle.setText(activity.getResources().getString(R.string.job_title)+jobsModels.get(position).getJobTitle());

        holder.imgJob.setImageResource(R.drawable.jobs_image);
        holder.tvCompanyname.setText(activity.getResources().getString(R.string.company)+jobsModels.get(position).getCompanyName());
        holder.tvJoblocation.setText(activity.getResources().getString(R.string.job_location)+jobsModels.get(position).getCompanyLocation());
        String strDate = jobsModels.get(position).getPostDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        String publishDate = new SimpleDateFormat("dd/MM/yyy").format(date);



        holder.tvPostdate.setText(activity.getResources().getString(R.string.publish_date)+publishDate+"");

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView imgJob;

        private TextView tvJobtitle , tvCompanyname , tvJoblocation , tvPostdate;

        public MyViewHolder(View view) {
            super(view);
            imgJob = view.findViewById(R.id.imgJob);
            tvJobtitle = view.findViewById(R.id.tvJobtitle);
            tvCompanyname = view.findViewById(R.id.tvCompanyname);
            tvJoblocation = view.findViewById(R.id.tvJoblocation);
            tvPostdate = view.findViewById(R.id.tvPostdate);
            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }
    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return jobsModels.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }



}

