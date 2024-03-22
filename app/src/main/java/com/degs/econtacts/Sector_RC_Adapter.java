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

public class Sector_RC_Adapter extends RecyclerView.Adapter<Sector_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Sector_Model> sectorModelArrayList;

    public Sector_RC_Adapter(Context context, ArrayList<Sector_Model> sectorModelArrayList) {
        this.context = context;
        this.sectorModelArrayList = sectorModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sector_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sector_name_hi_tv.setText(sectorModelArrayList.get(position).name_hi);
        holder.sector_name_eng_tv.setText(sectorModelArrayList.get(position).name_eng);
        holder.left_label_tv.setText("Assembly");
        holder.assembly_tv.setText(String.valueOf(sectorModelArrayList.get(position).assembly_name_eng));
        holder.right_label_tv.setText("Sector");
        holder.sector_no_tv.setText(String.valueOf(sectorModelArrayList.get(position).sector_no));

        holder.officer_name_tv.setText(sectorModelArrayList.get(position).sectorOfficersList.get(0).name_eng);
        holder.police_name_tv.setText(sectorModelArrayList.get(position).sectorOfficersList.get(1).name_eng);
        holder.officer_mobile_tv.setText(sectorModelArrayList.get(position).sectorOfficersList.get(0).mobile);
        holder.police_mobile_tv.setText(sectorModelArrayList.get(position).sectorOfficersList.get(1).mobile);

        holder.call_img_officer.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_police.setImageResource(R.drawable.baseline_call_24);
        holder.sms_img_officer.setImageResource(R.drawable.baseline_sms_24);
        holder.sms_img_police.setImageResource(R.drawable.baseline_sms_24);
        holder.whatsapp_img_officer.setImageResource(R.drawable.whatsapp);
        holder.whatsapp_img_police.setImageResource(R.drawable.whatsapp);
        holder.share_img_officer.setImageResource(R.drawable.share);
        holder.share_img_police.setImageResource(R.drawable.share);

    }

    @Override
    public int getItemCount() {
        return sectorModelArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sector_name_eng_tv, sector_name_hi_tv, assembly_tv, officer_name_tv, officer_mobile_tv, police_name_tv, police_mobile_tv, left_label_tv, right_label_tv, sector_no_tv;
        ImageView call_img_officer, call_img_police, sms_img_police, sms_img_officer, share_img_officer, share_img_police, whatsapp_img_officer, whatsapp_img_police;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sector_name_eng_tv = itemView.findViewById(R.id.officer_name_eng_tv);
            sector_name_hi_tv = itemView.findViewById(R.id.officer_name_eng_tv);
            left_label_tv = itemView.findViewById(R.id.left_label_tv);
            right_label_tv = itemView.findViewById(R.id.right_lable_tv);
            assembly_tv = itemView.findViewById(R.id.assembly_tv);
            sector_no_tv = itemView.findViewById(R.id.shift_tv);
            officer_name_tv = itemView.findViewById(R.id.officer_name_tv);
            officer_mobile_tv = itemView.findViewById(R.id.officer_mobile_tv);
            police_name_tv = itemView.findViewById(R.id.police_office_name_tv);
            police_mobile_tv = itemView.findViewById(R.id.police_mobile_tv);


            call_img_officer = itemView.findViewById(R.id.call_img_officer);
            call_img_police = itemView.findViewById(R.id.call_img_police);

            sms_img_officer = itemView.findViewById(R.id.sms_img_officer);
            sms_img_police = itemView.findViewById(R.id.sms_img_police);

            share_img_officer = itemView.findViewById(R.id.share_img_officer);
            share_img_police = itemView.findViewById(R.id.share_img_police);

            whatsapp_img_officer = itemView.findViewById(R.id.whatsapp_img_officer);
            whatsapp_img_police = itemView.findViewById(R.id.whatsapp_img_police);
        }
    }
    public void setFilteredList(ArrayList<Sector_Model> filteredList) {
        this.sectorModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
