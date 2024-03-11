package com.degs.econtacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {
    ArrayList<HomeItemModel> homeItemModelArrayList;
    Context context;

    public HomeItemAdapter(ArrayList<HomeItemModel> homeItemModelArrayList, Context context) {
        this.homeItemModelArrayList = homeItemModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_items_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_title.setText(homeItemModelArrayList.get(position).item_title);
        holder.item_image.setImageResource(homeItemModelArrayList.get(position).item_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (homeItemModelArrayList.get(holder.getAdapterPosition()).id) {
                    case 5:
                        context.startActivity(new Intent(context, Role.class));
                    case 6:
                        context.startActivity(new Intent(context, Department.class));
                    case 7:
                        context.startActivity(new Intent(context, Post.class));


                }
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (homeItemModelArrayList.get(holder.getAdapterPosition()).id){
//                    case 1:
//                        Intent intent=new Intent(context.getApplicationContext(), Register.class);
//                        String url="https://election.vinayakinfotech.co.in/api/deooffice/";
//                        intent.putExtra("url",url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                        break;
//                    case 2:
//                        // Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent4=new Intent(context, Nodal.class);
//                        context.startActivity(intent4);
//                        break;
//                    case 3:
//                        //  Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent5=new Intent(context, Committee.class);
//                        context.startActivity(intent5);
//                        break;
//                    case 4:
//                        //Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent2=new Intent(context,DepartmentActivity.class);
//                        context.startActivity(intent2);
//
//                        break;
//                    case 5:
//                        //   Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent6=new Intent(context, Role.class);
//                        context.startActivity(intent6);
//                        break;
//                    case 6:
//                        //  Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent3=new Intent(context, Post.class);
//                        context.startActivity(intent3);
//                        break;
//                    case 7:
//                        // Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent7=new Intent(context, PoliceStation.class);
//                        context.startActivity(intent7);
//                        break;case 8:
//                        // Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent8=new Intent(context, Sector.class);
//                        context.startActivity(intent8);
//                        break;
//                    case 9:
//                        // Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent9=new Intent(context, RoOffice.class);
//                        context.startActivity(intent9);
//                        break;
//                    case 10:
//                        // Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent10=new Intent(context, Booth.class);
//                        context.startActivity(intent10);
//                        break;
//                    case 11:
//                        //Toast.makeText(context, "Clicked:"+String.valueOf(itemModelArrayList.get(holder.getBindingAdapterPosition()).id), Toast.LENGTH_SHORT).show();
//                        Intent intent11=new Intent(context, ElectionLinks.class);
//                        context.startActivity(intent11);
//                        break;
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return homeItemModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.imgCategory);
            item_title = itemView.findViewById(R.id.category_name_tv);
        }
    }
}
