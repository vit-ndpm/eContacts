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
                Intent intent;
                String url;

                switch (homeItemModelArrayList.get(holder.getAdapterPosition()).id) {
                    case 1:
                        intent = new Intent(context, ARO_Office.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/roOffice";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(context, ARO_Office.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/aroOffice";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                        case 3:
                        intent = new Intent(context, Nodal.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/allNodals";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case 5:
                        context.startActivity(new Intent(context, Role.class));
                        break;
                    case 6:
                        context.startActivity(new Intent(context, Department.class));
                        break;
                    case 7:
                        context.startActivity(new Intent(context, Post.class));
                        break;
                    case 8:
                        context.startActivity(new Intent(context, Booth.class));
                        break;
                    case 9:
                        context.startActivity(new Intent(context, Polling_Party.class));
                        break;
                    case 10:
                        context.startActivity(new Intent(context, Control_Room.class));
                        break;
                    case 11:
                        intent = new Intent(context, Fst.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/allFsts";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case 12:
                        intent = new Intent(context, Fst.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/allSsts";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                        case 13:
                        intent = new Intent(context, Fst.class);
                        url = "https://ndpm.vinayakinfotech.co.in/api/allVsts";
                        intent.putExtra("url", url);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;


                }
            }
        });


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
