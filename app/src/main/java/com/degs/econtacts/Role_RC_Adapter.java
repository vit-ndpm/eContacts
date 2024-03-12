package com.degs.econtacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Role_RC_Adapter extends RecyclerView.Adapter<Role_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Role_Model> roleModelArrayList;

    public Role_RC_Adapter(Context context, ArrayList<Role_Model> roleModelArrayList) {
        this.context = context;
        this.roleModelArrayList = roleModelArrayList;
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
        String name_eng=roleModelArrayList.get(position).name_eng+"("+roleModelArrayList.get(position).short_name_eng+")";
        String name_hi=roleModelArrayList.get(position).name_hi+"("+roleModelArrayList.get(position).short_name_hi+")";
        holder.role_name_eng.setText(name_eng);
        holder.role_name_hi.setText(name_hi);
    }

    @Override
    public int getItemCount() {
        return roleModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView role_name_eng;
        TextView role_name_hi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            role_name_eng=itemView.findViewById(R.id.officer_name_eng_tv);
            role_name_hi=itemView.findViewById(R.id.officer_name_hi_tv);
        }
    }
    public void setFilteredList(ArrayList<Role_Model> filteredList) {
        this.roleModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
