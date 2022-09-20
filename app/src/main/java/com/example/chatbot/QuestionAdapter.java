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

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{

    Context context;

    ArrayList<QuestionClass> list;

    public QuestionAdapter(Context context, ArrayList<QuestionClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.qitem,parent,false);
        return new QuestionAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.MyViewHolder holder, int position) {

        QuestionClass qn=list.get(position);
        holder.number.setText(qn.getID());
        holder.question.setText(qn.Question);
        holder.answer.setText(qn.getAnswer());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView number,question,answer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            number=itemView.findViewById(R.id.txtQuestionNumber);
            question=itemView.findViewById(R.id.txtQuestion);
            answer=itemView.findViewById(R.id.txtAnswer);

        }

    }


}
