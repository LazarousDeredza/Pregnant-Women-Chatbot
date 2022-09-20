package com.example.chatbot;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ViewQuestion extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;

    QuestionAdapter myAdapter;
    ArrayList<QuestionClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);


        recyclerView =findViewById(R.id.questionList);
        database= FirebaseDatabase.getInstance().getReference("DataAnswers");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapter=new QuestionAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        ProgressDialog dialog = new ProgressDialog(ViewQuestion.this);
        dialog.setMessage("Loading  Data ...");
        dialog.show();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    QuestionClass qn=dataSnapshot.getValue(QuestionClass.class);
                    list.add(qn);

                }
                myAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}