package com.example.chatbot;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.database.*;

public class AdminHome extends AppCompatActivity {
    Button btnTips,btnLiveChat,btnContacts,btnEmegerncyPreparedness,btnLiveChat2;

    FloatingActionButton logout;
    FloatingActionsMenu floatingActionsMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        floatingActionsMenu=(FloatingActionsMenu) findViewById(R.id.floatingActionsMenu);


        btnTips=(Button) findViewById(R.id.btnTips);
        btnEmegerncyPreparedness=(Button) findViewById(R.id.btnEmegerncyPreparedness);
        btnLiveChat=(Button) findViewById(R.id.btnLiveChat);
        btnContacts=(Button) findViewById(R.id.btnContacts);
        btnLiveChat2=(Button) findViewById(R.id.btnLiveChat2);

        logout=(FloatingActionButton) findViewById(R.id.logout);


        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Making the Home button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator
                (R.drawable.menublk);




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Intent intent=new Intent(AdminHome.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(AdminHome.this, "logout successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });





        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();

                // addTip();

                Intent intent=new Intent(AdminHome.this,AddTip.class);

                startActivity(intent);

            }
        });
        btnEmegerncyPreparedness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Intent intent=new Intent(AdminHome.this,AddEmergerncy.class);
                startActivity(intent);

            }
        });
        btnLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingActionsMenu.collapse();
                Intent intent=new Intent(AdminHome.this,AddQuestion.class);
                startActivity(intent);

            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingActionsMenu.collapse();

                //addContacts();

                Intent intent=new Intent(AdminHome.this,AddContact.class);


                startActivity(intent);
            }
        });
        btnLiveChat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingActionsMenu.collapse();
                Intent intent=new Intent(AdminHome.this,ViewQuestion.class);
                startActivity(intent);

            }
        });


        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {

                btnLiveChat2.setVisibility(View.INVISIBLE);
               ;

            }

            @Override
            public void onMenuCollapsed() {
                btnLiveChat2.setVisibility(View.VISIBLE);



            }
        });

    }
}