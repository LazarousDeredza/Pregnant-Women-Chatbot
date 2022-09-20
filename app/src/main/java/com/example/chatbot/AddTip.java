package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.*;

import java.util.List;


public class AddTip extends AppCompatActivity {


    EditText Tip;
    MaterialButton savebtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tip);



        Tip = findViewById(R.id.tip);
        savebtn = findViewById(R.id.savebtn);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tip = Tip.getText().toString().trim();

                if(tip.isEmpty()){
                    Toast.makeText(AddTip.this, "Please Enter Something ", Toast.LENGTH_SHORT).show();
                }
                else{
                    ProgressDialog dialog=new ProgressDialog(AddTip.this);
                    dialog.setMessage("Adding To Database ...");
                    dialog.show();
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("tips");

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                          String ID=String.valueOf(snapshot.getChildrenCount()+1);



                            TipsClass tips = new TipsClass(ID,tip);
                            reference.child(ID).setValue(tips);
                            dialog.dismiss();
                            AlertDialog builder=new AlertDialog.Builder(AddTip.this)
                                    .setIcon(android.R.drawable.ic_menu_save)
                                    //set title
                                    .setTitle("Success")
                                    //set message
                                    .setMessage("Record added successfully ")
                                    //set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked

                                            Tip.setText(null);

                                        }
                                    })

                                    .show();


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