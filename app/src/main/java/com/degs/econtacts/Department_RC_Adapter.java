package com.degs.econtacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Department_RC_Adapter extends RecyclerView.Adapter <Department_RC_Adapter.ViewHolder>{
    ArrayList <Department_Model>departmentModelArrayList;
    Context context;

    public Department_RC_Adapter(ArrayList<Department_Model> departmentModelArrayList, Context context) {
        this.departmentModelArrayList = departmentModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.department_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name=departmentModelArrayList.get(position).name_eng+"("+departmentModelArrayList.get(position).short_name_eng+")";
        String name_hi=departmentModelArrayList.get(position).name_hi+"("+departmentModelArrayList.get(position).short_name_hi+")";
        holder.department_name_eng.setText(name);
        holder.department_name_hi.setText(name_hi);

    }

    @Override
    public int getItemCount() {
        return departmentModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView department_name_eng;
        TextView department_name_hi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            department_name_eng=itemView.findViewById(R.id.officer_name_eng_tv);
            department_name_hi=itemView.findViewById(R.id.officer_name_hi_tv);
        }
    }

    public void setFilteredList(ArrayList<Department_Model> filteredList) {
        this.departmentModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
