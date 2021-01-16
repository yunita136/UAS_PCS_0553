package com.example.uas_18120553.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_18120553.R;

import com.example.uas_18120553.model.CountrysItem;
import com.example.uas_18120553.model.EventsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AdapterNextEvent extends RecyclerView.Adapter<AdapterNextEvent.ViewHolder> {
    Context context;
    List<EventsItem> items;

    public AdapterNextEvent(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<EventsItem> Items) {
        this.items= Items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event,parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvhome.setText(items.get(position).getStrHomeTeam());
        holder.tvaway.setText(items.get(position).getStrAwayTeam());

        holder.tvtanggal.setText(items.get(position).getStrTimestamp());

        if (items.get(position).getIntAwayScore()==null){
            holder.tvskor.setText("0 : 0");
        } else {

        }
        holder.tvskor.setText(items.get(position).getIntHomeScore()+" : "+ items.get(position).getIntAwayScore());

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivhome, ivaway;
        TextView tvhome, tvaway, tvtanggal, tvskor;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivhome=itemView.findViewById(R.id.ivhome);
            ivaway=itemView.findViewById(R.id.ivaway);
            tvhome=itemView.findViewById(R.id.tvhome);
            tvaway=itemView.findViewById(R.id.tvaway);
            tvtanggal=itemView.findViewById(R.id.tvtanggal);
            tvskor=itemView.findViewById(R.id.tvskor);


            this.itemView=itemView;
        }
    }

}
