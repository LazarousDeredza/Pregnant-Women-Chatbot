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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;

    ArrayList<ContactsClass> list;

    public MyAdapter(Context context, ArrayList<ContactsClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ContactsClass contact=list.get(position);
        holder.name.setText(contact.getName());
        holder.location.setText(contact.getLocation());
        holder.phone.setText(contact.getPhone());
        holder.profession.setText(contact.getProfession());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,location,phone,profession;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.txtName);
            location=itemView.findViewById(R.id.txtLocation);
            phone=itemView.findViewById(R.id.txtPhone);
            profession=itemView.findViewById(R.id.txtProfession);

        }

    }




}
