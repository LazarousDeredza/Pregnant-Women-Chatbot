package com.example.chatbot;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmergencyAdapater extends RecyclerView.Adapter<EmergencyAdapater.MyViewHolder>{


    Context context;

    ArrayList<EmergerncyClass> list;

    public EmergencyAdapater(Context context, ArrayList<EmergerncyClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EmergencyAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.tipitem2,parent,false);
        return new EmergencyAdapater.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapater.MyViewHolder holder, int position) {

        EmergerncyClass tips=list.get(position);
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
