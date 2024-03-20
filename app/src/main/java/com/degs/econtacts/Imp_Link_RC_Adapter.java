package com.degs.econtacts;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Imp_Link_RC_Adapter extends RecyclerView.Adapter<Imp_Link_RC_Adapter.ViewHolder> {
    Context context;
    ArrayList<Imp_Link_Model>impLinkModelArrayList;

    public Imp_Link_RC_Adapter(Context context, ArrayList<Imp_Link_Model> impLinkModelArrayList) {
        this.context = context;
        this.impLinkModelArrayList = impLinkModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.link_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return impLinkModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cat_name_eng.setText(impLinkModelArrayList.get(position).category.name_eng);
        holder.cat_name_hi.setText(impLinkModelArrayList.get(position).category.name_hi);
        holder.title.setText(impLinkModelArrayList.get(position).title);
        holder.description.setText(impLinkModelArrayList.get(position).description);
        holder.cat_img.setImageResource(R.drawable.link);

        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(impLinkModelArrayList.get(holder.getAdapterPosition()).url));
            context.startActivity(browserIntent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            ClipboardManager clipboardManager=(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("Link",impLinkModelArrayList.get(holder.getAdapterPosition()).url);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "Copied to ClipBoard"+clipData, Toast.LENGTH_SHORT).show();
            return true;
        });



    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cat_name_eng,cat_name_hi,title,description,url;
        ImageView cat_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_name_eng=itemView.findViewById(R.id.name_eng_link);
            cat_name_hi=itemView.findViewById(R.id.name_hi_link);
            title=itemView.findViewById(R.id.title_link);
            description=itemView.findViewById(R.id.decription_link);
            cat_img=itemView.findViewById(R.id.img_cat_link);

        }
    }
}
