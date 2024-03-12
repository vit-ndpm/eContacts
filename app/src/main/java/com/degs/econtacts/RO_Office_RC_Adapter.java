package com.degs.econtacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RO_Office_RC_Adapter extends RecyclerView.Adapter<RO_Office_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<RO_Office_Model> roOfficeModelArrayList;

    public RO_Office_RC_Adapter(Context context, ArrayList<RO_Office_Model> roOfficeModelArrayList) {
        this.context = context;
        this.roOfficeModelArrayList = roOfficeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.officer_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_eng_tv.setText(roOfficeModelArrayList.get(position).name_eng);
        holder.name_hi_tv.setText(roOfficeModelArrayList.get(position).name_hi);
        holder.mobile_tv.setText(roOfficeModelArrayList.get(position).mobile);
        holder.email_tv.setText(roOfficeModelArrayList.get(position).email);
        holder.call_img.setImageResource(R.drawable.baseline_call_24);
        holder.sms_img.setImageResource(R.drawable.baseline_sms_24);
        holder.email_img.setImageResource(R.drawable.baseline_email_24);
    }

    @Override
    public int getItemCount() {
        return roOfficeModelArrayList.size();
    }

    public void setFilteredList(ArrayList<RO_Office_Model> filteredList) {
        this.roOfficeModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_eng_tv, name_hi_tv, mobile_tv, email_tv;
        ImageView call_img, sms_img, email_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_eng_tv = itemView.findViewById(R.id.team_name_eng_tv);
            name_hi_tv = itemView.findViewById(R.id.team_name_hi_tv);
            mobile_tv = itemView.findViewById(R.id.mobile_tv);
            email_tv = itemView.findViewById(R.id.email_tv);
            call_img = itemView.findViewById(R.id.call_img);
            sms_img = itemView.findViewById(R.id.sms_img);
            email_img = itemView.findViewById(R.id.email_img);
        }
    }
}
