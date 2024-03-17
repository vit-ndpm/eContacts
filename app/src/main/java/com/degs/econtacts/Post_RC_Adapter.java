package com.degs.econtacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Post_RC_Adapter extends RecyclerView.Adapter<Post_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Post_Model> postModelArrayList;

    public Post_RC_Adapter(Context context, ArrayList<Post_Model> postModelArrayList) {
        this.context = context;
        this.postModelArrayList = postModelArrayList;
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
        String name=postModelArrayList.get(position).name_eng+"("+postModelArrayList.get(position).department_short_name_eng+")";
        String name_hi=postModelArrayList.get(position).name_hi+"("+postModelArrayList.get(position).department_short_name_hi+")";
        holder.post_name_eng.setText(name);
        holder.post_name_hi.setText(name_hi);

    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView post_name_eng,post_name_hi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_name_eng=itemView.findViewById(R.id.officer_name_eng_tv);
            post_name_hi=itemView.findViewById(R.id.officer_name_hi_tv);
        }
    }
    public void setFilteredList(ArrayList<Post_Model> filteredList) {
        this.postModelArrayList = filteredList;
        notifyDataSetChanged();
    }

}
