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
public class Tips extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;

    TipAdapter myAdapter;
    ArrayList<TipsClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        recyclerView =findViewById(R.id.tipsList);
        database= FirebaseDatabase.getInstance().getReference("tips");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapter=new TipAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        ProgressDialog dialog = new ProgressDialog(Tips.this);
        dialog.setMessage("Loading  Data ...");
        dialog.show();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    TipsClass tip=dataSnapshot.getValue(TipsClass.class);
                    list.add(tip);

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