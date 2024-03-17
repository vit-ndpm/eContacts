package com.degs.econtacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Booth_RC_Adapter extends RecyclerView.Adapter<Booth_RC_Adapter.ViewHolder> {

    Context context;
    ArrayList<Booth_Model> boothModelArrayList;

    public Booth_RC_Adapter(Context context, ArrayList<Booth_Model> boothModelArrayList) {
        this.context = context;
        this.boothModelArrayList = boothModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booth_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (boothModelArrayList.get(position).ac_code) {
            case 136:
                holder.assembly_id.setBackgroundResource(R.drawable.seoni_ac_border);
                holder.assembly_id.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.seoni_ac_background);
                break;
            case 137:
                holder.assembly_id.setBackgroundResource(R.drawable.nar_ac_border);
                holder.assembly_id.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.nar_ac_background);
                break;
            case 138:
                holder.assembly_id.setBackgroundResource(R.drawable.sohagpur_ac_border);
                holder.assembly_id.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.sohagpur_ac_background);

                break;
            case 139:
                holder.assembly_id.setBackgroundResource(R.drawable.pipariya_ac_border);
                holder.assembly_id.setTextColor(Color.parseColor("#FFFFFF"));
                holder.itemView.setBackgroundResource(R.drawable.pipariya_ac_background);

                break;
        }
        String booth_number = Integer.toString(boothModelArrayList.get(position).pollingStationCode);
        holder.booth_no.setText(booth_number);
        holder.booth_name_eng.setText(boothModelArrayList.get(position).boothName);
        holder.booth_name_hi.setText(boothModelArrayList.get(position).boothName);

        holder.assembly_id.setText(String.valueOf(boothModelArrayList.get(position).ac_code));
        holder.blo_name.setText(boothModelArrayList.get(position).bLOName);
        holder.blo_mobile.setText(boothModelArrayList.get(position).bLOMobileNumber);
//        holder.sector_no.setText(boothModelArrayList.get(position).sectorNumber)


        //set various Indicators
        if (boothModelArrayList.get(position).webcasting == 1) {
            holder.webcasting.setImageResource(R.drawable.baseline_check_circle_24);
        } else {
            holder.webcasting.setImageResource(R.drawable.baseline_dangerous_24);
        }
        if (boothModelArrayList.get(position).cctv == 1) {
            holder.cctv.setImageResource(R.drawable.baseline_check_circle_24);
        } else {
            holder.cctv.setImageResource(R.drawable.baseline_dangerous_24);
        }
        if (boothModelArrayList.get(position).videography == 1) {
            holder.videography.setImageResource(R.drawable.videography_yes);
        } else {
            holder.videography.setImageResource(R.drawable.videography_no);
        }
        if (boothModelArrayList.get(position).critical == 1) {
            holder.critical.setImageResource(R.drawable.baseline_check_circle_24);
        } else {
            holder.critical.setImageResource(R.drawable.baseline_dangerous_24);
        }
        if (boothModelArrayList.get(position).vulnarable == 1) {
            holder.vulnerable.setImageResource(R.drawable.baseline_check_circle_24);
        } else {
            holder.vulnerable.setImageResource(R.drawable.baseline_dangerous_24);
        }
        if (boothModelArrayList.get(position).call_network == 1) {
            holder.call_network.setImageResource(R.drawable.call_netowrk_yes);
        } else {
            holder.call_network.setImageResource(R.drawable.call_netowrk_no);
        }
        if (boothModelArrayList.get(position).data_network == 1) {
            holder.data_netowrk.setImageResource(R.drawable.data_network_yes);
        } else {
            holder.data_netowrk.setImageResource(R.drawable.data_network_no);
        }

        //Call and SMS Functionality
        holder.img_call_blo.setImageResource(R.drawable.baseline_call_24);
        holder.img_sms_blo.setImageResource(R.drawable.baseline_sms_24);
        holder.img_call_blo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=boothModelArrayList.get(holder.getAdapterPosition()).bLOMobileNumber;
                My_Utility myUtility=new My_Utility();
                myUtility.callClicked(mobile,context);
            }
        });
        holder.img_sms_blo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=boothModelArrayList.get(holder.getAdapterPosition()).bLOMobileNumber;
                My_Utility myUtility=new My_Utility();
                myUtility.callClicked(mobile,context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return boothModelArrayList.size();
    }

    public void setFilteredList(ArrayList<Booth_Model> filteredList) {
        this.boothModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    public void smsClicked(String number) {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("sms:" + number));
        smsIntent.putExtra("sms_body", "Hello");
        smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(smsIntent);

    }

    public void composeEmail(String[] addresses, String subject, Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void callClicked(String number) {

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView assembly_id, sector_no, booth_no, booth_name_eng, booth_name_hi, blo_name, blo_mobile;
        ImageView webcasting, cctv, videography, critical, senstive, vulnerable, call_network, data_netowrk, ps_location, img_call_blo, img_sms_blo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            booth_no = itemView.findViewById(R.id.booth_no_tv);
            assembly_id = itemView.findViewById(R.id.ac_id_tv);
            booth_name_eng = itemView.findViewById(R.id.boot_name_tv);
            booth_name_hi = itemView.findViewById(R.id.booth_name_hi_tv);
            blo_name = itemView.findViewById(R.id.blo_name_tv);
            blo_mobile = itemView.findViewById(R.id.blo_mobile);
            sector_no = itemView.findViewById(R.id.sector_no_tv);
            webcasting = itemView.findViewById(R.id.img_webcasting);
            cctv = itemView.findViewById(R.id.img_cctv);
            videography = itemView.findViewById(R.id.img_videography);
            senstive = itemView.findViewById(R.id.img_senstive);
            critical = itemView.findViewById(R.id.img_critical);
            vulnerable = itemView.findViewById(R.id.img_vlunerable);
            call_network = itemView.findViewById(R.id.img_call_network);
            data_netowrk = itemView.findViewById(R.id.img_data_netowrk);
            img_call_blo = itemView.findViewById(R.id.img_call_blo);
            img_sms_blo = itemView.findViewById(R.id.img_sms_blo);


        }
    }

    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }
}
