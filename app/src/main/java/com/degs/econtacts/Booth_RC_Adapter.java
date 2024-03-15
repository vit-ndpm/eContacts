package com.degs.econtacts;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Booth_RC_Adapter extends RecyclerView.Adapter<Booth_RC_Adapter.ViewHolder> {

    Context context;
    ArrayList<Booth_Model>boothModelArrayList;

    public Booth_RC_Adapter(Context context, ArrayList<Booth_Model> boothModelArrayList) {
        this.context = context;
        this.boothModelArrayList = boothModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.booth_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (boothModelArrayList.get(position).assembly_id) {
            case 1:
                holder.ac_no.setBackgroundResource(R.drawable.seoni_ac_border);
                holder.ac_no.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.seoni_ac_background);
                break;
            case 3:
                holder.ac_no.setBackgroundResource(R.drawable.nar_ac_border);
                holder.ac_no.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.nar_ac_background);
                break;
            case 4:
                holder.ac_no.setBackgroundResource(R.drawable.sohagpur_ac_border);
                holder.ac_no.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.sohagpur_ac_background);

                break;
            case 5:
                holder.ac_no.setBackgroundResource(R.drawable.pipariya_ac_border);
                holder.ac_no.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.pipariya_ac_background);

                break;
        }

       holder.booth_name_eng.setText(boothModelArrayList.get(position).booth_name_eng);
       holder.booth_name_hi.setText(boothModelArrayList.get(position).booth_name_hi);
       holder.booth_no.setText(boothModelArrayList.get(position).booth_no);




    }

    @Override
    public int getItemCount() {
        return 1;
//        return boothModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ac_no, sector_no, booth_no, booth_name_eng, booth_name_hi, blo_name, blo_mobile, otehr_officer;
        ImageView webcasting, cctv, videography, critical, senstive, vulnerable, call, data, ps_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ac_no = itemView.findViewById(R.id.ac_id_tv);
            sector_no = itemView.findViewById(R.id.sector_no_tv);
            booth_no = itemView.findViewById(R.id.booth_no_tv);
            booth_name_eng = itemView.findViewById(R.id.boot_name_tv);
            booth_name_hi = itemView.findViewById(R.id.booth_name_hi_tv);
            webcasting = itemView.findViewById(R.id.img_webcasting);
            cctv = itemView.findViewById(R.id.img_cctv);
            videography = itemView.findViewById(R.id.img_videography);
            critical = itemView.findViewById(R.id.img_critical);
            senstive = itemView.findViewById(R.id.img_senstive);
            vulnerable = itemView.findViewById(R.id.img_vlunerable);
            call = itemView.findViewById(R.id.img_call_network);
            data = itemView.findViewById(R.id.img_data_netowrk);
            blo_mobile = itemView.findViewById(R.id.blo_mobile);
            blo_name = itemView.findViewById(R.id.blo_name_tv);
            otehr_officer = itemView.findViewById(R.id.otehr_officers);
            ps_location = itemView.findViewById(R.id.imgLocation);
        }
    }
    public void setFilteredList(ArrayList<Booth_Model> filteredList) {
        this.boothModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
