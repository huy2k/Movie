package com.example.appp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class messengerAdapter extends RecyclerView.Adapter<messengerAdapter.messengerViewHolder> {
    ArrayList<messengerData> messengerDatas;

    public messengerAdapter(ArrayList<messengerData> messengerDatas) {
        this.messengerDatas = messengerDatas;
    }

    @Override
    public messengerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.messenger_row,parent,false);
        messengerViewHolder holder = new messengerViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(messengerViewHolder holder, int position) {
        messengerData data = messengerDatas.get(position);
        holder.name.setText(data.getUser());
        holder.message.setText(data.getMessage());
    }

    @Override
    public int getItemCount() {
        return messengerDatas.size();
    }

    class messengerViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,message;
        LinearLayout containerlayout;
        public messengerViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.messenger_username_name);
            message = (TextView) itemView.findViewById(R.id.messenger_Content);
        }
    }
}
