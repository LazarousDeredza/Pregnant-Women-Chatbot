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

public class AddQuestion extends AppCompatActivity {
    EditText question,answer;
    MaterialButton savebtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        savebtn = findViewById(R.id.savebtn);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Question = question.getText().toString().trim();
                String Answer = answer.getText().toString().trim();
                if(Question.isEmpty()||Answer.isEmpty()){
                    Toast.makeText(AddQuestion.this, "Please Fill in all Details ", Toast.LENGTH_SHORT).show();
                }
                else{
                    ProgressDialog dialog=new ProgressDialog(AddQuestion.this);
                    dialog.setMessage("Adding To Database ...");
                    dialog.show();
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("DataAnswers");

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String ID=String.valueOf(snapshot.getChildrenCount()+1);

                            QuestionClass qn = new QuestionClass(ID,Question,Answer);
                            reference.child(ID).setValue(qn);
                            dialog.dismiss();
                            AlertDialog builder=new AlertDialog.Builder(AddQuestion.this)
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

                                            question.setText(null);
                                            answer.setText(null);

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