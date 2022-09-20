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

public class Home extends AppCompatActivity {
    FloatingActionButton profile,settings,help,logout;
    View txtUser;
    FloatingActionsMenu floatingActionsMenu;
    Button btnTips,btnLiveChat,btnSupportGroup,btnContacts,btnEmegerncyPreparedness;

    ImageView imgEmegerncyPreparedness,imgTips,imgContacts;

    DatabaseReference reference;

    public String myuser=null;
    public String myuserName=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // Settings settings1=new Settings();
        //settings1.setDarkModeSwitch();

        profile=(FloatingActionButton) findViewById(R.id.profile);
        settings=(FloatingActionButton) findViewById(R.id.settings);
        help=(FloatingActionButton) findViewById(R.id.help);
        logout=(FloatingActionButton) findViewById(R.id.logout);
        floatingActionsMenu=(FloatingActionsMenu) findViewById(R.id.floatingActionsMenu);
        imgEmegerncyPreparedness=(ImageView) findViewById(R.id.imgEmegerncyPreparedness);
        imgTips=(ImageView) findViewById(R.id.imgTips);
        imgContacts=(ImageView) findViewById(R.id.imgContacts);
        btnTips=(Button) findViewById(R.id.btnTips);
        btnEmegerncyPreparedness=(Button) findViewById(R.id.btnEmegerncyPreparedness);
        btnLiveChat=(Button) findViewById(R.id.btnLiveChat);
        btnSupportGroup=(Button) findViewById(R.id.btnSupportGroup);
        btnContacts=(Button) findViewById(R.id.btnContacts);

        txtUser = findViewById(R.id.action_user);




//Toolbar
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Making the Home button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator
                (R.drawable.menublk);



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
               Intent intent=new Intent(Home.this,UserProfile.class);
               intent.putExtra("username",myuserName);
               startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
               Intent intent=new Intent(Home.this,Settings.class);
               intent.putExtra("user",myuser);
               intent.putExtra("username",myuserName);
               startActivity(intent);


            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Toast.makeText(Home.this, "help clicked", Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(Home.this, "logout successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        floatingActionsMenu.collapse();



        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                imgEmegerncyPreparedness.setVisibility(View.INVISIBLE);
                imgTips.setVisibility(View.INVISIBLE);
                imgContacts.setVisibility(View.INVISIBLE);
                btnContacts.setVisibility(View.INVISIBLE);
                btnTips.setVisibility(View.INVISIBLE);
                btnEmegerncyPreparedness.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onMenuCollapsed() {
                btnTips.setVisibility(View.VISIBLE);
                btnEmegerncyPreparedness.setVisibility(View.VISIBLE);
                btnContacts.setVisibility(View.VISIBLE);
                imgTips.setVisibility(View.VISIBLE);
                imgContacts.setVisibility(View.VISIBLE);
                imgEmegerncyPreparedness.setVisibility(View.VISIBLE);

            }
        });


        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();

               // addTip();

                Intent intent=new Intent(Home.this,Tips.class);
                intent.putExtra("user",myuser);
                intent.putExtra("username",myuserName);

                startActivity(intent);

            }
        });
        btnEmegerncyPreparedness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();
                Intent intent=new Intent(Home.this,Emergerncy.class);
                intent.putExtra("user",myuser);
                intent.putExtra("username",myuserName);

                startActivity(intent);

            }
        });
        btnLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingActionsMenu.collapse();
                Intent intent=new Intent(Home.this,Chat.class);
                intent.putExtra("user",myuser);
                intent.putExtra("username",myuserName);

                startActivity(intent);

            }
        });
        btnSupportGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionsMenu.collapse();

                Intent intent=new Intent(Home.this,Alarm.class);
                /*intent.putExtra("user",myuser);
                intent.putExtra("username",myuserName);*/

                startActivity(intent);
            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingActionsMenu.collapse();

                //addContacts();

                Intent intent=new Intent(Home.this,Contact.class);
                intent.putExtra("user",myuser);
                intent.putExtra("username",myuserName);

                startActivity(intent);
            }
        });


    }

    private void addTip() {

        reference= FirebaseDatabase.getInstance().getReference("tips");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TipsClass tip1=new TipsClass("1","Take a prenatal Vitamin");
                reference.child(tip1.getID()).setValue(tip1);

                TipsClass tip2=new TipsClass("2","Exercise Regulary");
                reference.child(tip2.getID()).setValue(tip2);

                TipsClass tip3=new TipsClass("3","Change Your Chores (avoid harsh or toxic cleaners, heavy lifting) ");
                reference.child(tip3.getID()).setValue(tip3);

                TipsClass tip4=new TipsClass("4","Track Your Weight gain (Normal weigth gain is 25-35 pounds) ");
                reference.child(tip4.getID()).setValue(tip4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void addContacts() {
        reference= FirebaseDatabase.getInstance().getReference("contacts");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ContactsClass con1=new ContactsClass("Dr Billy Rigaya","Glenora A Medical Clinic","+263 (04) 611971)","Family Physician");
                reference.child(con1.getName()).setValue(con1);

                ContactsClass con2=new ContactsClass("Mr T Goche","Chitungwiza Hospital","+263 (070) 22111","Surgeon");
                reference.child(con2.getName()).setValue(con2);

                ContactsClass con3=new ContactsClass("Mrs M Jamieson","Mater Dei Hospital ","+263 (09) 240000 ","Nuerologist");
                reference.child(con3.getName()).setValue(con3);

                ContactsClass con4=new ContactsClass("Mr P Mangudah","Subarban Medical","+263 (04) 229962","Internist");
                reference.child(con4.getName()).setValue(con4);

                ContactsClass con5=new ContactsClass("Dr M Tawanda","Mazorodze Clinic","+26377863668","Radiologist");
                reference.child(con5.getName()).setValue(con5);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }







    //Adding Button to Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Bundle extras=getIntent().getExtras();
        String user=extras.getString("user");
        if(extras!=null){
            user=extras.getString("user");
            myuser=extras.getString("user");
            myuserName=extras.getString("username");
          //  Toast.makeText(this, myuserName, Toast.LENGTH_SHORT).show();
        }
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item=menu.findItem(R.id.action_user);
        item.setTitle(user);
        return super.onCreateOptionsMenu(menu);
    }

  //Handing click on toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if(id==R.id.action_settings){
            return true;
        }*/
        if(id==R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}