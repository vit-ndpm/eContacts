package com.degs.econtacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sst_RC_Adapter extends RecyclerView.Adapter<Sst_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Sst_Model>sstModelArrayList;

    public Sst_RC_Adapter(Context context, ArrayList<Sst_Model> sstModelArrayList) {
        this.context = context;
        this.sstModelArrayList = sstModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.team_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sst_name_eng_tv.setText(sstModelArrayList.get(position).name_eng);
        holder.sst_name_hi_tv.setText(sstModelArrayList.get(position).name_hi);
        holder.shift_tv.setText(sstModelArrayList.get(position).shift);
        holder.assembly_tv.setText(sstModelArrayList.get(position).assembly_name_eng);
        //Office and Police Office Detail
        String officer_name="";
        String officer_mobile="";
        String police_name="";
        String police_mobile="";
        officer_name=sstModelArrayList.get(position).sstOfficersList.get(0).name_eng.toString();
        officer_mobile=sstModelArrayList.get(position).sstOfficersList.get(0).mobile.toString();
        police_name=sstModelArrayList.get(position).sstOfficersList.get(1).name_eng.toString();
        police_mobile=sstModelArrayList.get(position).sstOfficersList.get(1).mobile.toString();
        holder.officer_name_tv.setText(officer_name);
        holder.officer_mobile_tv.setText(officer_mobile);
        holder.police_name_tv.setText(police_name);
        holder.police_mobile_tv.setText(police_mobile);
//         officer_name="";
//         officer_mobile="";
//         police_name="";
//         police_mobile="";

        //Office and Police Office Detail End

        Log.d("Details of Officers List","Officer name:"+officer_name+"mobile:"+officer_mobile+"Police Name:"+police_name+"mobile"+police_mobile);
        holder.call_img_officer.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_police.setImageResource(R.drawable.baseline_call_24);
        holder.sms_img_officer.setImageResource(R.drawable.baseline_sms_24);
        holder.sms_img_police.setImageResource(R.drawable.baseline_sms_24);
        holder.sms_img_officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(0).mobile;
                smsClicked(number);
            }
        });
        holder.sms_img_police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(1).mobile;
                smsClicked(number);
            }
        });
        holder.call_img_officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(0).mobile;
                callClicked(number);
            }
        });
        holder.call_img_police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(1).mobile;
                callClicked(number);
            }
        });
        holder.call_img_officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(0).mobile;
                callClicked(number);
            }
        });
        holder.call_img_police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=sstModelArrayList.get(holder.getAdapterPosition()).sstOfficersList.get(1).mobile;
                callClicked(number);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sstModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sst_name_eng_tv,sst_name_hi_tv,assembly_tv,shift_tv,officer_name_tv,officer_mobile_tv,police_name_tv,police_mobile_tv;
        ImageView call_img_officer,call_img_police,sms_img_police,sms_img_officer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sst_name_eng_tv=itemView.findViewById(R.id.team_name_eng_tv);
            sst_name_hi_tv=itemView.findViewById(R.id.team_name_hi_tv);
            assembly_tv=itemView.findViewById(R.id.assembly_tv);
            shift_tv=itemView.findViewById(R.id.shift_tv);
            officer_name_tv=itemView.findViewById(R.id.officer_name_tv);
            officer_mobile_tv=itemView.findViewById(R.id.officer_mobile_tv);
            police_name_tv=itemView.findViewById(R.id.police_office_name_tv);
            police_mobile_tv=itemView.findViewById(R.id.police_mobile_tv);
            call_img_officer=itemView.findViewById(R.id.call_img_officer);
            call_img_police=itemView.findViewById(R.id.call_img_police);
            sms_img_officer=itemView.findViewById(R.id.sms_img_officer);
            sms_img_police=itemView.findViewById(R.id.sms_img_police);
        }
    }
    public void setFilteredList(ArrayList<Sst_Model> filteredList) {
        this.sstModelArrayList = filteredList;
        notifyDataSetChanged();
    }
    public void smsClicked(String number){
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("sms:"+number));
        smsIntent.putExtra("sms_body", "Hello");
        smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(smsIntent);

    }
    public void composeEmail(String[] addresses, String subject,Context context) {
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
}
