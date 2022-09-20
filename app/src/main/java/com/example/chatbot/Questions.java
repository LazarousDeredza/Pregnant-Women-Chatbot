package com.example.chatbot;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Questions extends AppCompatActivity {

      DatabaseReference reference;
      ListView listView;
      ArrayList<String> arrayList=new ArrayList<>();
      ArrayAdapter<String> arrayAdapter;
      Module module;
      String myuserName=null;
      String myuser=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        Bundle extras=getIntent().getExtras();
        if(extras!=null){
        myuser=extras.getString("user");
        myuserName=extras.getString("username");

        }

        listView=(ListView)findViewById(R.id.listviewtxt);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        module=(Module)getApplicationContext() ;

        reference= FirebaseDatabase.getInstance().getReference("DataAnswers");

        ProgressDialog dialog=new ProgressDialog(Questions.this);
        dialog.setMessage("Sncyronizing, Please Wait ...");
        dialog.show();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

        if(snapshot.hasChildren()) {
              for (DataSnapshot child : snapshot.getChildren()) {
                    String Answer = snapshot.child(child.getKey().toString()).getValue(QnClass.class).ans();
                    String Question = snapshot.child(child.getKey().toString()).getValue(QnClass.class).qn();
                    Toast.makeText(Questions.this, "Inbox Sncyronized", Toast.LENGTH_LONG).show();
                    arrayList.add(Question);
                    arrayList.add(Answer);
                    arrayAdapter.notifyDataSetChanged();
                    dialog.dismiss();
              }
        }else{
                    Toast.makeText(Questions.this, "Inbox is Empty", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
        }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
        });

    }
}