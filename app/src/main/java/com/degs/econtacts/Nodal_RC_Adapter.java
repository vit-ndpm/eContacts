package com.degs.econtacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Nodal_RC_Adapter extends RecyclerView.Adapter<Nodal_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Nodal_Model> nodalmodelArrayList;

    public Nodal_RC_Adapter(Context context, ArrayList<Nodal_Model> nodalmodelArrayList) {
        this.context = context;
        this.nodalmodelArrayList = nodalmodelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.nodal_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String jobName=nodalmodelArrayList.get(position).name_eng+"\n("+nodalmodelArrayList.get(position).name_hi+")";
        holder.job_name.setText(jobName);

        holder.nodal_name.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(0).name_eng);
        holder.nodal_post.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(0).mobile);
        holder.img_nodal_call.setImageResource(R.drawable.baseline_call_24);
        holder.img_nodal_sms.setImageResource(R.drawable.baseline_sms_24);
        holder.img_nodal_email.setImageResource(R.drawable.baseline_email_24);

        holder.as1_name.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(1).name_eng);
        holder.as1_mobile.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(1).mobile);
        holder.img_as1_call.setImageResource(R.drawable.baseline_call_24);
        holder.img_as1_sms.setImageResource(R.drawable.baseline_sms_24);
        holder.img_as1_email.setImageResource(R.drawable.baseline_email_24);

        holder.as2_name.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(2).name_eng);
        holder.as2_mobile.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(2).mobile);
        holder.img_as2_call.setImageResource(R.drawable.baseline_call_24);
        holder.img_as2_sms.setImageResource(R.drawable.baseline_sms_24);
        holder.img_as2_email.setImageResource(R.drawable.baseline_email_24);


        holder.as3_name.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(3).name_eng);
        holder.as3_mobile.setText(nodalmodelArrayList.get(position).officerModelArrayList.get(3).mobile);
        holder.img_as3_call.setImageResource(R.drawable.baseline_call_24);
        holder.img_as3_sms.setImageResource(R.drawable.baseline_sms_24);
        holder.img_as3_email.setImageResource(R.drawable.baseline_email_24);

My_Utility myUtility=new My_Utility();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deptId = nodalmodelArrayList.get(holder.getAdapterPosition()).name_eng;
                //Toast.makeText(context, "Clicked On :"+deptId, Toast.LENGTH_SHORT).show();
                //Call SMS and email Button Listner Functions starts here
                //for Nodal
                holder.img_nodal_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(0).mobile;
                        myUtility.callClicked(number,context);
                    }
                });
                holder.img_nodal_sms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(0).mobile;
                        myUtility.smsClicked(number,context);
                    }
                });
                holder.img_nodal_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(0).email;
                        String[]addresses=new String[1];
                        addresses[0]=email;
                        myUtility.composeEmail(addresses,"Subject",context);
                    }
                });

                //for Assistant Nodal-1
                holder.img_as1_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(1).mobile;
                        myUtility.callClicked(number,context);
                    }
                });
                holder.img_as1_sms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(1).mobile;
                        myUtility.callClicked(number,context);
                    }
                });
                holder.img_as1_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(1).email;
                        String[]addresses=new String[1];
                        addresses[0]=email;
                        myUtility.composeEmail(addresses,"Subject",context);
                    }
                });
                //for Assistant Nodal-2
                holder.img_as2_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(2).mobile;
                        myUtility.callClicked(number,context);
                    }
                });
                holder.img_as2_sms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(2).mobile;
                        myUtility.smsClicked(number,context);
                    }
                });
                holder.img_as2_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(2).email;
                        String[]addresses=new String[1];
                        addresses[0]=email;
                        myUtility.composeEmail(addresses,"Subject",context);
                    }
                });
                //for Assistant Nodal-3
                holder.img_as3_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(2).mobile;
                        myUtility.callClicked(number,context);
                    }
                });
                holder.img_as3_sms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(2).mobile;
                        myUtility.smsClicked(number,context);
                    }
                });
                holder.img_as3_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=nodalmodelArrayList.get(holder.getAdapterPosition()).officerModelArrayList.get(3).email;
                        String[]addresses=new String[1];
                        addresses[0]=email;
                        myUtility.composeEmail(addresses,"Subject",context);
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return nodalmodelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView job_name, nodal_name,nodal_post,nodal_mobile;
        TextView as1_name,as1_post,as1_mobile;
        TextView as2_name,as2_post,as2_mobile;
        TextView as3_name,as3_post,as3_mobile;
        ImageView img_nodal_sms,img_nodal_email,img_nodal_call;
        ImageView img_as1_sms,img_as1_email,img_as1_call;
        ImageView img_as2_sms,img_as2_email,img_as2_call;
        ImageView img_as3_sms,img_as3_email,img_as3_call;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            job_name = itemView.findViewById(R.id.job_title);


            nodal_name=itemView.findViewById(R.id.nodal_name);
            nodal_post=itemView.findViewById(R.id.nodal_post);
            img_nodal_call=itemView.findViewById(R.id.img_nodal_call);
            img_nodal_sms=itemView.findViewById(R.id.img_nodal_sms);
            img_nodal_email=itemView.findViewById(R.id.img_nodal_email);
            //nodal_mobile=itemView.findViewById(R.id.nodal_mobile);

            as1_name=itemView.findViewById(R.id.as1_name);
            as1_mobile=itemView.findViewById(R.id.as1_post);
            img_as1_call=itemView.findViewById(R.id.img_as1_call);
            img_as1_sms=itemView.findViewById(R.id.img_as1_sms);
            img_as1_email=itemView.findViewById(R.id.img_as1_email);

            as2_name=itemView.findViewById(R.id.as2_name);
            as2_mobile=itemView.findViewById(R.id.as2_post);
            img_as2_call=itemView.findViewById(R.id.img_as2_call);
            img_as2_sms=itemView.findViewById(R.id.img_as2_sms);
            img_as2_email=itemView.findViewById(R.id.img_as2_email);

            as3_name=itemView.findViewById(R.id.as3_name);
            as3_mobile=itemView.findViewById(R.id.as3_post);
            img_as3_call=itemView.findViewById(R.id.img_as3_call);
            img_as3_sms=itemView.findViewById(R.id.img_as3_sms);
            img_as3_email=itemView.findViewById(R.id.img_as3_email);
        }
    }
    public void setFilteredList(ArrayList<Nodal_Model> filteredList) {
        this.nodalmodelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
