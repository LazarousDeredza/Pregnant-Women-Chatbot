package com.example.chatbot;

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

public class AddContact extends AppCompatActivity {

    EditText name,surname,profession,location,phone,tittle;
    MaterialButton addbtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);



        name = (EditText) findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        location = findViewById(R.id.location);
        profession = findViewById(R.id.profession);
        phone = findViewById(R.id.phone);
        tittle = findViewById(R.id.tittle);

        addbtn = findViewById(R.id.addbtn);



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Surname = surname.getText().toString().trim();
                String Location = location.getText().toString().trim().toLowerCase();
                String Profession = profession.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String Tittle = tittle.getText().toString().trim();

                if(Name.isEmpty() || Surname.isEmpty() || Location.isEmpty() || Profession.isEmpty() || Phone.isEmpty()){
                    Toast.makeText(AddContact.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                            ProgressDialog dialog=new ProgressDialog(AddContact.this);
                            dialog.setMessage("Adding To Database ...");
                            dialog.show();
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("contacts");

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        ContactsClass contact = new ContactsClass(Tittle +" "+Name+ " " +Surname, Location, Phone, Profession);
                                        reference.child(Name+Surname).setValue(contact);
                                        dialog.dismiss();
                                        AlertDialog builder=new AlertDialog.Builder(AddContact.this)
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

                                                        tittle.setText(null);
                                                        name.setText(null);
                                                        surname.setText(null);
                                                        location.setText(null);
                                                        profession.setText(null);
                                                        phone.setText(null);

                                                        finish();
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