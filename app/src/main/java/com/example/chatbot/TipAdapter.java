package com.example.chatbot;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.MyViewHolder>{


    Context context;

    ArrayList<TipsClass> list;

    public TipAdapter(Context context, ArrayList<TipsClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TipAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.tipitem,parent,false);
        return new TipAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TipAdapter.MyViewHolder holder, int position) {

        TipsClass tips=list.get(position);
        holder.TipNumber.setText(tips.getID());
        holder.Tip.setText(tips.getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView TipNumber,Tip;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TipNumber=itemView.findViewById(R.id.txtTipNumber);
            Tip=itemView.findViewById(R.id.txtTip);

        }

    }
}
