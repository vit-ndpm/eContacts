package com.degs.econtacts;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Police_Station_RC_Adapter extends RecyclerView.Adapter<Police_Station_RC_Adapter.ViewHolder>  {
    Context context;
    ArrayList<Police_Station_Model>policeStationModelArrayList;

    public Police_Station_RC_Adapter(Context context, ArrayList<Police_Station_Model> policeStationModelArrayList) {
        this.context = context;
        this.policeStationModelArrayList = policeStationModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.police_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return policeStationModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.assembly_tv.setText(String.valueOf(policeStationModelArrayList.get(position).assembly_id));
        holder.policeStation_name_eng_tv.setText(policeStationModelArrayList.get(position).name_eng);
        holder.police_station_name_hi_tv.setText(policeStationModelArrayList.get(position).name_hi);
        holder.address_tv.setText(policeStationModelArrayList.get(position).address);
        holder.post_tv.setText("Thana Incharge:");
        holder.ti_name_tv.setText(policeStationModelArrayList.get(position).officer_name);
        holder.mobile_tv.setText(policeStationModelArrayList.get(position).officer_mobile);
        holder.email_tv.setText(policeStationModelArrayList.get(position).email);
        holder.call_img.setImageResource(R.drawable.baseline_call_24);
        holder.sms_img.setImageResource(R.drawable.baseline_sms_24);
        holder.whatsapp_img.setImageResource(R.drawable.whatsapp);
        holder.share_img.setImageResource(R.drawable.share);
        My_Utility myUtility=new My_Utility();
        holder.call_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=policeStationModelArrayList.get(position).officer_mobile;
                myUtility.callClicked(number,context);

            }
        });
        holder.sms_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=policeStationModelArrayList.get(position).officer_mobile;
                myUtility.smsClicked(number,context);

            }
        });
        holder.whatsapp_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=policeStationModelArrayList.get(position).officer_mobile;
                myUtility.openWhatsApp(context,number);

            }
        });
        holder.email_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=policeStationModelArrayList.get(position).email;
                myUtility.callClicked(email,context);

            }
        });
        holder.share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=policeStationModelArrayList.get(position).officer_mobile;
                myUtility.shareText(context,number);

            }
        });
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView role_tv,assembly_tv,policeStation_name_eng_tv,police_station_name_hi_tv,address_tv,post_tv,ti_name_tv,mobile_tv,email_tv;
        ImageView call_img,sms_img,share_img,whatsapp_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            policeStation_name_eng_tv=itemView.findViewById(R.id.police_station_name_eng_tv);
            police_station_name_hi_tv=itemView.findViewById(R.id.police_station_name_hitv);
            address_tv=itemView.findViewById(R.id.address_tv);
            post_tv=itemView.findViewById(R.id.post_tv);
            ti_name_tv=itemView.findViewById(R.id.ti_name_tv);
            mobile_tv=itemView.findViewById(R.id.mobile_tv);
            email_tv=itemView.findViewById(R.id.email_tv);
            call_img=itemView.findViewById(R.id.call_img);
            sms_img=itemView.findViewById(R.id.sms_img);
            share_img=itemView.findViewById(R.id.img_share);
            whatsapp_img=itemView.findViewById(R.id.img_whatsapp);

        }
    }
    public void setFilteredList(ArrayList<Police_Station_Model> filteredList) {
        this.policeStationModelArrayList = filteredList;
        notifyDataSetChanged();
    }
}
