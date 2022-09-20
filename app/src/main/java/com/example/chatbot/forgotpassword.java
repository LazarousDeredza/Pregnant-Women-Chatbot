package com.example.chatbot;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.*;

import static com.example.chatbot.AESHelper.decrypt;

public class forgotpassword extends AppCompatActivity {

    DatabaseReference reference;
    MaterialButton btnRecover;
    EditText name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        btnRecover=(MaterialButton) findViewById(R.id.btnRecover);


        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);

        reference= FirebaseDatabase.getInstance().getReference("users");


        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(forgotpassword.this);
                dialog.setMessage("Recovering  ...");
                dialog.show();
                String Name=name.getText().toString().trim().toLowerCase();
                String Email=email.getText().toString().trim().toLowerCase();
                if(Name.isEmpty()||Email.isEmpty()){
                    dialog.dismiss();
                    Toast.makeText(forgotpassword.this,"Please Enter all Details",Toast.LENGTH_SHORT).show();

                }else{
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean found=false;
                          if(snapshot.hasChild(Name)){
                              if (snapshot.child(Name).child("email").getValue().toString().toLowerCase().equals(Email)){
                                  dialog.dismiss();

                                  String Password=decrypt(snapshot.child(Name).child("password").getValue().toString());

                                  AlertDialog builder=new AlertDialog.Builder(forgotpassword.this)
                                          .setIcon(android.R.drawable.ic_menu_save)
                                          //set title
                                          .setTitle("Success")
                                          //set message
                                          .setMessage("Your Password is : ' "+ Password  +" ' ")
                                          //set positive button
                                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialogInterface, int i) {


                                              }
                                          })

                                          .show();

                              }else{
                                  dialog.dismiss();
                                  Toast.makeText(forgotpassword.this, "The Email Provided Does not match the privided Username", Toast.LENGTH_SHORT).show();
                              }

                          }else{
                              dialog.dismiss();
                              Toast.makeText(forgotpassword.this, "Username Not Found", Toast.LENGTH_SHORT).show();
                          }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

    }
}