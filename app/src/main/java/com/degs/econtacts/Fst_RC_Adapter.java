package com.degs.econtacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fst_RC_Adapter extends RecyclerView.Adapter<Fst_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Fst_Model>fstModelArrayList;

    public Fst_RC_Adapter(Context context, ArrayList<Fst_Model> fstModelArrayList) {
        this.context = context;
        this.fstModelArrayList = fstModelArrayList;
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
        holder.fst_name_eng_tv.setText(fstModelArrayList.get(position).name_eng);
        holder.fst_name_hi_tv.setText(fstModelArrayList.get(position).name_hi);
        holder.shift_tv.setText(fstModelArrayList.get(position).shift);
        holder.assembly_tv.setText(fstModelArrayList.get(position).assembly_name_eng);
        //Office and Police Office Detail
        String officer_name="";
        String officer_mobile="";
        String police_name="";
        String police_mobile="";
        officer_name=fstModelArrayList.get(position).fstOfficersList.get(0).name_eng.toString();
        officer_mobile=fstModelArrayList.get(position).fstOfficersList.get(0).mobile.toString();
        police_name=fstModelArrayList.get(position).fstOfficersList.get(1).name_eng.toString();
        police_mobile=fstModelArrayList.get(position).fstOfficersList.get(1).mobile.toString();
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

    }

    @Override
    public int getItemCount() {
        return fstModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fst_name_eng_tv,fst_name_hi_tv,assembly_tv,shift_tv,officer_name_tv,officer_mobile_tv,police_name_tv,police_mobile_tv;
        ImageView call_img_officer,call_img_police;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fst_name_eng_tv=itemView.findViewById(R.id.team_name_eng_tv);
            fst_name_hi_tv=itemView.findViewById(R.id.team_name_hi_tv);
            assembly_tv=itemView.findViewById(R.id.assembly_tv);
            shift_tv=itemView.findViewById(R.id.shift_tv);
            officer_name_tv=itemView.findViewById(R.id.officer_name_tv);
            officer_mobile_tv=itemView.findViewById(R.id.officer_mobile_tv);
            police_name_tv=itemView.findViewById(R.id.police_office_name_tv);
            police_mobile_tv=itemView.findViewById(R.id.police_mobile_tv);
            call_img_officer=itemView.findViewById(R.id.call_img_officer);
            call_img_police=itemView.findViewById(R.id.call_img_police);
        }
    }
    public void setFilteredList(ArrayList<Fst_Model> filteredList) {
        this.fstModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
