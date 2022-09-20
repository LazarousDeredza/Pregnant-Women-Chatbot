package com.example.chatbot;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.*;

import static com.example.chatbot.AESHelper.encrypt;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference,ref;
    MaterialButton registerbtn,loginbtn;
    EditText username,password;
    TextView forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        registerbtn = findViewById(R.id.registerbtn);
        loginbtn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgotpass = findViewById(R.id.forgotpass);



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText(null);
                password.setText(null);
                Intent intent = new Intent(MainActivity.this,forgotpassword.class);
                startActivity(intent);
            }
        });



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText(null);
                password.setText(null);
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim().toLowerCase();
                String pass = password.getText().toString().trim();
                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                if(user.equals("admin")&&pass.toLowerCase().equals("admin")){

                    username.setText(null);
                    password.setText(null);
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AdminHome.class);
                    startActivity(intent);
                }
                else {
                    ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("Logging In ...");
                    dialog.show();

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(user)) {
                                User currentUser = snapshot.child(user.toLowerCase()).getValue(User.class);

                                if (currentUser.getPassword().equals(encrypt(pass))) {
                                    dialog.dismiss();
                                    username.setText(null);
                                    password.setText(null);
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    intent.putExtra("user", currentUser.getName() + " " + currentUser.getSurname());
                                    intent.putExtra("username", currentUser.getUsername());
                                    startActivity(intent);

                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                }
            }
        });

    }
}