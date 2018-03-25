package com.example.hp.booleans.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.booleans.FetchData.Data;
import com.example.hp.booleans.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 24-03-2018.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.viewHolder> {
    Context context;
    ArrayList<Data> list;

    public CustomRecyclerViewAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_main_list_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String imageUrl = list.get(position).getImage_url();
        String title = list.get(position).getTitle();
        Log.i("Ojas",imageUrl);
        Picasso.get().load(imageUrl).into(holder.imageView);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public viewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rec_imageView);
            textView = (TextView) itemView.findViewById(R.id.rec_textView);
        }
    }
}
