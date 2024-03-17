package com.degs.econtacts;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Polling_Party_RC_Adapter extends RecyclerView.Adapter<Polling_Party_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Polling_Party_Model>polling_party_modelArrayList;

    public Polling_Party_RC_Adapter(Context context, ArrayList<Polling_Party_Model> polling_party_modelArrayList) {
        this.context = context;
        this.polling_party_modelArrayList = polling_party_modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.polling_party_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.assembly_name_tv.setText(polling_party_modelArrayList.get(position).assembly_name);
        holder.booth_name_tv.setText(polling_party_modelArrayList.get(position).booth_name);


        holder.po_name_tv.setText(polling_party_modelArrayList.get(position).po_name);
        holder.p1_name_tv.setText(polling_party_modelArrayList.get(position).p1_name);
        holder.p2_name_tv.setText(polling_party_modelArrayList.get(position).p2_name);
        holder.p3_name_tv.setText(polling_party_modelArrayList.get(position).p3_name);


        holder.po_mobile_tv.setText(polling_party_modelArrayList.get(position).po_mobile);
        holder.p1_mobile_tv.setText(polling_party_modelArrayList.get(position).p1_mobile);
        holder.p2_mobile_tv.setText(polling_party_modelArrayList.get(position).p2_mobile);
        holder.p3_mobile_tv.setText(polling_party_modelArrayList.get(position).p3_mobile);


        //calls
        holder.call_img_po.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_p1.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_p2.setImageResource(R.drawable.baseline_call_24);
        holder.call_img_p3.setImageResource(R.drawable.baseline_call_24);
        My_Utility myUtility=new My_Utility();
        holder.call_img_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).po_mobile.toString();
                myUtility.callClicked(number,context);

            }
        });
        holder.call_img_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p1_mobile.toString();
                myUtility.callClicked(number,context);

            }
        });
        holder.call_img_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p2_mobile.toString();
                myUtility.callClicked(number,context);

            }
        });
        holder.call_img_p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p3_mobile.toString();
                myUtility.callClicked(number,context);

            }
        });
        holder.sms_img_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).po_mobile.toString();
                myUtility.smsClicked(number,context);

            }
        });
        holder.sms_img_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p1_mobile.toString();
                myUtility.smsClicked(number,context);

            }
        });
        holder.sms_img_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p2_mobile.toString();
                myUtility.smsClicked(number,context);

            }
        });
        holder.sms_img_p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=polling_party_modelArrayList.get(holder.getAdapterPosition()).p3_mobile.toString();
                myUtility.smsClicked(number,context);

            }
        });






    }

    @Override
    public int getItemCount() {
        return polling_party_modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView call_img_po,call_img_p1,call_img_p2,call_img_p3,sms_img_po,sms_img_p1,sms_img_p2,sms_img_p3;
        TextView booth_name_tv,assembly_name_tv,po_name_tv,p1_name_tv,p2_name_tv,p3_name_tv,po_mobile_tv,p1_mobile_tv,p2_mobile_tv,p3_mobile_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sms_img_po=itemView.findViewById(R.id.img_sms_po);
            sms_img_p1=itemView.findViewById(R.id.img_sms_p1);
            sms_img_p2=itemView.findViewById(R.id.img_sms_p2);
            sms_img_p3=itemView.findViewById(R.id.img_sms_p3);

            call_img_po=itemView.findViewById(R.id.img_call_po);
            call_img_p1=itemView.findViewById(R.id.img_call_p1);
            call_img_p2=itemView.findViewById(R.id.img_call_p2);
            call_img_p3=itemView.findViewById(R.id.img_call_p3);


            booth_name_tv=itemView.findViewById(R.id.booth_nameTv);
            assembly_name_tv=itemView.findViewById(R.id.ac_name_tv);

            po_name_tv=itemView.findViewById(R.id.po_name_tv);
            p1_name_tv=itemView.findViewById(R.id.p1_name_tv);
            p2_name_tv=itemView.findViewById(R.id.p2_name_tv);
            p3_name_tv=itemView.findViewById(R.id.p3_name_tv);


            po_mobile_tv=itemView.findViewById(R.id.po_mobile);
            p1_mobile_tv=itemView.findViewById(R.id.p1_mobile);
            p2_mobile_tv=itemView.findViewById(R.id.p2_mobile);
            p3_mobile_tv=itemView.findViewById(R.id.p3_mobile);
        }
    }
}
