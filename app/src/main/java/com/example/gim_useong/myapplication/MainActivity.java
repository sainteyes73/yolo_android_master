package com.example.gim_useong.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gim_useong.myapplication.models.User;
import com.example.gim_useong.myapplication.notice.NoticeActivity;
import com.example.gim_useong.myapplication.notice.TipActivity;
import com.example.gim_useong.myapplication.ranking.StarActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting();


    }
    @Override
    public void onStart(){
        super.onStart();

        initView();
        setnav();
    }
    public void setting(){

        mDatabase= FirebaseDatabase.getInstance().getReference().child("users").child(getUid());
    }
    public void setnav(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        final TextView navUsername = (TextView)headerView.findViewById(R.id.nickname);
        final TextView navEmail= (TextView)headerView.findViewById(R.id.head_id);
        navigationView.setNavigationItemSelectedListener(this);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                navUsername.setText(user.username);
                navEmail.setText(user.email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView Import_product=(ImageView) findViewById(R.id.import_product);
        ImageView My_menu=(ImageView)findViewById(R.id.my_item);
        ImageView TimeLine=(ImageView)findViewById(R.id.timeline);
        ImageView Notice=(ImageView)findViewById(R.id.notice);
        ImageView Tip=(ImageView)findViewById(R.id.tip);
        ImageView Ranking=(ImageView)findViewById(R.id.ranking);
        Import_product.setOnClickListener(this);
        My_menu.setOnClickListener(this);
        TimeLine.setOnClickListener(this);
        Notice.setOnClickListener(this);
        Tip.setOnClickListener(this);
        Ranking.setOnClickListener(this);
        setSupportActionBar(toolbar);
        //setnav();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.import_product:
                Intent intent=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.my_item:
                Intent intent1=new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.timeline:
                Intent intent2=new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.notice:
                Intent intent3=new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intent3);
                break;
            case R.id.tip:
                Intent intent4=new Intent(MainActivity.this, TipActivity.class);
                startActivity(intent4);
                break;
            case R.id.ranking:
                Intent intent5=new Intent(MainActivity.this,StarActivity.class);
                startActivity(intent5);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_product) {
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        }else if (id == R.id.camera_recognition) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        }else if(id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }else if(id == R.id.menu_notice){
            Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
            startActivity(intent);
        }else if(id==R.id.setting){
            Intent intent = new Intent(MainActivity.this,SettingPreference.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
