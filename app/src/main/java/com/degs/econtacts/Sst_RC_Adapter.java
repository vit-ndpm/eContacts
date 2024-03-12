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
//        if (sstModelArrayList!=null){
//            holder.officer_name_tv.setText(sstModelArrayList.get(position).sstOfficersList.get(position).name_eng);
//            holder.officer_mobile_tv.setText(sstModelArrayList.get(position).sstOfficersList.get(position).mobile);
//        }
        holder.call_img_officer.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_police.setImageResource(R.drawable.baseline_call_24);

    }

    @Override
    public int getItemCount() {
        return sstModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sst_name_eng_tv,sst_name_hi_tv,assembly_tv,shift_tv,officer_name_tv,officer_mobile_tv,police_name_tv,police_mobile_tv;
        ImageView call_img_officer,call_img_police;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sst_name_eng_tv=itemView.findViewById(R.id.team_name_eng_tv);
            sst_name_hi_tv=itemView.findViewById(R.id.team_name_hi_tv);
            assembly_tv=itemView.findViewById(R.id.assembly_tv);
            shift_tv=itemView.findViewById(R.id.shift_tv);
//            officer_name_tv=itemView.findViewById(R.id.officer_name_tv);
//            officer_mobile_tv=itemView.findViewById(R.id.officer_mobile_tv);
//            police_name_tv=itemView.findViewById(R.id.police_office_name_tv);
//            police_mobile_tv=itemView.findViewById(R.id.police_mobile_tv);
            call_img_officer=itemView.findViewById(R.id.call_img_officer);
            call_img_police=itemView.findViewById(R.id.call_img_police);
        }
    }
    public void setFilteredList(ArrayList<Sst_Model> filteredList) {
        this.sstModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
