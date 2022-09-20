package com.example.chatbot;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;

public class UserProfile extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    EditText txtName,txtSurname,txtEmail,txtPhone,txtUsername,txtAddress;

    Button btnUpdate;
    String password=null;
    String user=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        txtName=(EditText)findViewById(R.id.txtName);
        txtSurname=(EditText)findViewById(R.id.txtSurname);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPhone=(EditText)findViewById(R.id.txtPhone);
        txtUsername=(EditText)findViewById(R.id.txtUsername);
        txtAddress=(EditText)findViewById(R.id.txtAddress);

        btnUpdate=(Button)findViewById(R.id.btnUpdate);

        txtUsername.setEnabled(false);
        txtUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserProfile.this,"Username cannot be edited",Toast.LENGTH_SHORT).show();
            }
        });


        ProgressDialog dialog=new ProgressDialog(UserProfile.this);
        dialog.setMessage("Loading Profile Please Wait ...");
        dialog.show();


        Bundle extras=getIntent().getExtras();
        user=extras.getString("username");
        txtUsername.setText(user);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(user)){
                    txtName.setText(snapshot.child(user).child("name").getValue().toString());
                    txtSurname.setText(snapshot.child(user).child("surname").getValue().toString());
                    txtEmail.setText(snapshot.child(user).child("email").getValue().toString());
                    txtPhone.setText(snapshot.child(user).child("phone").getValue().toString());
                    password=snapshot.child(user).child("password").getValue().toString();
                    txtAddress.setText(snapshot.child(user).child("address").getValue().toString());
                    dialog.dismiss();

                }else{
                    dialog.dismiss();
                    Toast.makeText(UserProfile.this, "User Not Found", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=txtName.getText().toString().trim();
                String Surname=txtSurname.getText().toString().trim();
                String Email=txtEmail.getText().toString().trim();
                String Username=txtUsername.getText().toString().trim();
                String Address=txtAddress.getText().toString().trim();
                String Phone=txtPhone.getText().toString().trim();

                if(Name.isEmpty()||Surname.isEmpty()||Email.isEmpty()||Phone.isEmpty()||Address.isEmpty()){
                    Toast.makeText(UserProfile.this, "Fill in All Details", Toast.LENGTH_SHORT).show();
                }else{
                    ProgressDialog dialog=new ProgressDialog(UserProfile.this);
                    dialog.setMessage("Updating Profile ...");
                    dialog.show();
                    User user=new User(Username, Name, Surname, Email, Phone, password,Address,"");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            reference.child(Username).setValue(user);
                            dialog.dismiss();
                            AlertDialog builder=new AlertDialog.Builder(UserProfile.this)
                                    .setIcon(android.R.drawable.ic_menu_save)
                                    //set title
                                    .setTitle("Success")
                                    //set message
                                    .setMessage("User Updated successfully ")
                                    //set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                            recreate();

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