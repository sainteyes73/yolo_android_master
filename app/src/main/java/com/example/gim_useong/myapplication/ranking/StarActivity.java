package com.example.gim_useong.myapplication.ranking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.gim_useong.myapplication.BaseActivity;
import com.example.gim_useong.myapplication.R;
import com.example.gim_useong.myapplication.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StarActivity extends BaseActivity {
    private DatabaseReference mPostReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("posts");
        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getAllData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getAllData(DataSnapshot dataSnapshot){
        int i=0;
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            String Max=snapshot.child("starCount").getValue().toString();
            Log.d("aaaa","aa"+snapshot.child("starCount").getValue());
            i++;
        }
        Log.d("aaa","aaa"+i);
    }
}
