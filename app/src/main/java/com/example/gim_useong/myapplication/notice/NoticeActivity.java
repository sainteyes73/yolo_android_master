package com.example.gim_useong.myapplication.notice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.gim_useong.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoticeActivity extends AppCompatActivity{
    private ListView listView, mainListView;

    static boolean calledAlready = false;

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("notice");

        reference.child("number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NoticeAdapter adapter = new NoticeAdapter();
            //    MainAdapter mainAdapter = new MainAdapter();
                for(DataSnapshot fireSnapshot : dataSnapshot.getChildren()){

                    long number = fireSnapshot.child("num").getValue(long.class);
                    String date = fireSnapshot.child("date").getValue(String.class);
                    String title = fireSnapshot.child("title").getValue(String.class);
                    String detail = fireSnapshot.child("detail").getValue(String.class);

                    adapter.addItem(Long.toString(number), title, date, detail);
                   // mainAdapter.addItem(Long.toString(number), title, date);
                }
                listView = (ListView)findViewById(R.id.notice_list);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Tag : ", "Failed to read value", databaseError.toException());
            }
        });
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.menu_notice);
    }
}