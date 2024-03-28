package com.degs.econtacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OtehrOfficerRCViewAdapter extends RecyclerView.Adapter<OtehrOfficerRCViewAdapter.ViewHolder> {
    Context context;
    ArrayList<OtherOfficerModel> list;

    public OtehrOfficerRCViewAdapter(Context context, ArrayList<OtherOfficerModel> otherOfficerModelArrayList) {
        this.context = context;
        this.list = otherOfficerModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.other_items_new, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.mobile.setText(list.get(position).mobile);
        holder.role.setText(list.get(position).role);

        if (list.get(holder.getAdapterPosition()).mobile != null) {
            holder.imgCall.setImageResource(R.drawable.baseline_call_24);
            holder.imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + list.get(holder.getAdapterPosition()).mobile));
                    context.startActivity(intent);
                }
            });
        }
        else {
            holder.name.setText("Comming Soon...");
            holder.imgCall.setImageResource(R.drawable.baseline_no_cell_24);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, mobile, role;
        ImageView imgCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.new_name_tv);
            mobile = itemView.findViewById(R.id.new_mobile_tv);
            role = itemView.findViewById(R.id.new_role_tv);
            imgCall = itemView.findViewById(R.id.imgCall);
        }
    }

}
