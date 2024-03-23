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

public class RO_Office_RC_Adapter extends RecyclerView.Adapter<RO_Office_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<RO_Office_Model> roOfficeModelArrayList;

    public RO_Office_RC_Adapter(Context context, ArrayList<RO_Office_Model> roOfficeModelArrayList) {
        this.context = context;
        this.roOfficeModelArrayList = roOfficeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.officer_items2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_eng_tv.setText(roOfficeModelArrayList.get(position).name_eng);
        holder.name_hi_tv.setText(roOfficeModelArrayList.get(position).name_hi);
        holder.mobile_tv.setText(roOfficeModelArrayList.get(position).mobile);
        holder.email_tv.setText(roOfficeModelArrayList.get(position).email);
        holder.department.setText(roOfficeModelArrayList.get(position).dept_name);
        holder.post.setText(roOfficeModelArrayList.get(position).post_name);
        holder.role.setText(roOfficeModelArrayList.get(position).role_name);
//        holder.assembly.setText(aroOfficeModelArrayList.get(position).);
        holder.call_img.setImageResource(R.drawable.baseline_call_24);
        holder.sms_img.setImageResource(R.drawable.baseline_sms_24);
        holder.email_img.setImageResource(R.drawable.baseline_email_24);
        holder.img_whatsapp.setImageResource(R.drawable.whatsapp);
        holder.img_share.setImageResource(R.drawable.share);
        My_Utility myUtility = new My_Utility();
        holder.img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=roOfficeModelArrayList.get(holder.getAdapterPosition()).name_eng;
                String post=roOfficeModelArrayList.get(holder.getAdapterPosition()).post_name;
                String number=roOfficeModelArrayList.get(holder.getAdapterPosition()).mobile;
                String email=roOfficeModelArrayList.get(holder.getAdapterPosition()).email;
                String textToSend="Name:"+name+"\n"+"Post:"+post+"\n"+"Mobile:"+number+"\n"+"e-Mail:"+email;
                myUtility.shareText(context,textToSend);

            }
        });
        holder.sms_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number=roOfficeModelArrayList.get(holder.getAdapterPosition()).mobile;

                myUtility.smsClicked(number,context);

            }
        });
        holder.call_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number=roOfficeModelArrayList.get(holder.getAdapterPosition()).mobile;

                myUtility.callClicked(number,context);

            }
        });
        holder.img_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=roOfficeModelArrayList.get(holder.getAdapterPosition()).mobile;
                myUtility.openWhatsApp(context,number);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roOfficeModelArrayList.size();
    }

    public void setFilteredList(ArrayList<RO_Office_Model> filteredList) {
        this.roOfficeModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_eng_tv, name_hi_tv, mobile_tv, email_tv, department, post, assembly, role;
        ImageView call_img, sms_img, email_img, img_whatsapp,img_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_eng_tv = itemView.findViewById(R.id.police_station_name_eng_tv);
            name_hi_tv = itemView.findViewById(R.id.police_station_name_hitv);
            mobile_tv = itemView.findViewById(R.id.mobile_tv);
            email_tv = itemView.findViewById(R.id.email_tv);
            department = itemView.findViewById(R.id.ti_name_tv);
            post = itemView.findViewById(R.id.post_tv);
            assembly = itemView.findViewById(R.id.assembly_tv);
            role = itemView.findViewById(R.id.role_tv);
            call_img = itemView.findViewById(R.id.call_img);
            sms_img = itemView.findViewById(R.id.sms_img);
            email_img = itemView.findViewById(R.id.email_img);
            img_whatsapp = itemView.findViewById(R.id.img_whatsapp);
            img_share = itemView.findViewById(R.id.img_share);
        }
    }
}
