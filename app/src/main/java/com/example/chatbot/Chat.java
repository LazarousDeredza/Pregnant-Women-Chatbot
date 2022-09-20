package com.example.chatbot;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Chat extends AppCompatActivity {
    EditText txtMessage;
    Button btnSend;
   AlertDialog alertDialog;

    DatabaseReference reference,aiReference,re;
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    Module module;
   String myuser=null;
   String msg=null;
    String myuserName=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //Toast.makeText(Chat.this, "Starting ", Toast.LENGTH_LONG).show();
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            myuser=extras.getString("user");
            myuserName=extras.getString("username");

        }

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm aaa");



        aiReference= FirebaseDatabase.getInstance().getReference("DataAnswers");
        //Toast.makeText(Chat.this,"Username = "+myuserName +", Name = "+myuser,Toast.LENGTH_LONG ).show();
        listView=(ListView)findViewById(R.id.listviewtxt);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        btnSend=(Button)findViewById(R.id.btnSend);
        txtMessage=(EditText)findViewById(R.id.txtMessage);

        module=(Module)getApplicationContext() ;



        reference= FirebaseDatabase.getInstance().getReference("chats");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String Msg=txtMessage.getText().toString().toLowerCase().trim();
                if(Msg.isEmpty()){
                    Toast.makeText(Chat.this, "Please Enter A Message / Question ", Toast.LENGTH_SHORT).show();
                }else{
                    if(Msg.equals("get answers")){

                        Intent intent=new Intent(Chat.this,Questions.class);
                        intent.putExtra("user",myuser);
                        intent.putExtra("username",myuserName);
                        txtMessage.setText(null);
                        startActivity(intent);

                    }else {

                        ProgressDialog dialog=new ProgressDialog(Chat.this);
                        dialog.setMessage("Sending  ...");
                        dialog.show();
                      //  msg=Msg.replaceAll("\\W","").replaceAll("_","");
                        msg=Msg.replaceAll("[^a-zA-Z-0\\s]","").replaceAll("_","");

                        //Toast.makeText(Chat.this,msg,Toast.LENGTH_LONG).show();
                        aiReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                date = dateFormat.format(calendar.getTime());

                                ChatClass chat=new ChatClass(msg,"",date);
                                boolean ansfound=false;
                                String ans=null;

                                for (DataSnapshot snap:snapshot.getChildren()){

                                    if(snapshot.child(snap.getKey().toString()).child("question").getValue().toString().toLowerCase().replaceAll("[^a-zA-Z-0\\s]","").replaceAll("_","").trim().equals(msg)){
                                        ansfound=true;
                                        ans=snapshot.child(snap.getKey().toString()).child("answer").getValue().toString();
                                        break;
                                    }
                                }

                                if(ansfound){
                                    Toast.makeText(Chat.this , ans, Toast.LENGTH_SHORT).show();
                                    chat.setAnswer(ans);
                                   // dialog.dismiss();
                                     String value = chat.toString();
                                    String value2 = chat.AIanswer();
                                  //  Toast.makeText(Chat.this, "Ready", Toast.LENGTH_LONG).show();
                                    arrayList.add(value);
                                    arrayList.add(value2);
                                    arrayAdapter.notifyDataSetChanged();
                                    txtMessage.setText(null);
                                    dialog.dismiss();


                                }else{
                                    chat.setAnswer("....... ChatBot Couldn't find a matching Reply for you ,Sorry ! !");
                                    String value = chat.toString();
                                    String value2 = chat.AIanswer();
                                    Toast.makeText(Chat.this, "Question Not Found", Toast.LENGTH_LONG).show();
                                    arrayList.add(value);
                                    arrayList.add(value2);
                                    arrayAdapter.notifyDataSetChanged();
                                    txtMessage.setText(null);
                                    dialog.dismiss();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                }
            }
        });


    }


}