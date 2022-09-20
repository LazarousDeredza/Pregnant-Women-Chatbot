package com.example.chatbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private Switch darkModeSwitch;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView usernameTextView,email,logout,editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(new DarkModePrefManager(this).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        setContentView(R.layout.activity_settings);

        setDarkModeSwitch();
        usernameTextView=(TextView)findViewById(R.id.usernameTextView);
        email=(TextView)findViewById(R.id.email);
        logout=(TextView)findViewById(R.id.logout);
        editProfile=(TextView)findViewById(R.id.editProfile);


        Bundle extras=getIntent().getExtras();
       // String user=extras.getString("user");


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProgressDialog dialog=new ProgressDialog(Settings.this);
                dialog.setMessage("Processing ...");
                dialog.show();
                String UserName=extras.getString("username");
                if(snapshot.hasChild(UserName)){
                    String myemail=snapshot.child(UserName).child("email").getValue().toString();
                    String myname=snapshot.child(UserName).child("name").getValue().toString()+" "+snapshot.child(UserName).child("surname").getValue().toString();
                    email.setText(myemail);
                    usernameTextView.setText(myname);
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Settings.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(Settings.this, "logout successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName=extras.getString("username");
                Intent intent=new Intent(Settings.this,UserProfile.class);
                intent.putExtra("username",UserName);
                startActivity(intent);
            }
        });



    }







    void setDarkModeSwitch(){
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        darkModeSwitch.setChecked(new DarkModePrefManager(this).isNightMode());
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DarkModePrefManager darkModePrefManager = new DarkModePrefManager(Settings.this);
                darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            }
        });
    }
}