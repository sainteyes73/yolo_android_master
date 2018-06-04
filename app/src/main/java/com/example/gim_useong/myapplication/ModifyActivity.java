package com.example.gim_useong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gim_useong.myapplication.models.Post;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyActivity extends BaseActivity {
    public static final String EXTRA_POST_KEY = "post_key";
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private EditText addMyDataBox;
    //private DatabaseReference databaseReference;
    private DatabaseReference mPostReference;
    private DatabaseReference mUserPostRefernce;
    private List<String> allMyData;
    private String mPostKey;
    private FloatingActionButton m_fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        Log.d("a123",mPostKey);
        //databaseReference=FirebaseDatabase.getInstance().getReference();
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("posts").child(mPostKey);
        mUserPostRefernce = FirebaseDatabase.getInstance().getReference().child("user-posts").child(getUid())
                .child(mPostKey);
        allMyData = new ArrayList<String>();
        addMyDataBox = (EditText)findViewById(R.id.add_mydata_box);
        recyclerView = (RecyclerView)findViewById(R.id.mydata_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        m_fab=(FloatingActionButton)findViewById(R.id.fab_modify);
        m_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostReference.child("body").setValue(allMyData);
                mUserPostRefernce.child("body").setValue(allMyData);
                Intent intent=new Intent(ModifyActivity.this,ListViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button addMyDataButton = (Button)findViewById(R.id.add_mydata_button);
        //getAllMyData();
        addMyDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredMyData = addMyDataBox.getText().toString();
                if(TextUtils.isEmpty(enteredMyData)){
                    Toast.makeText(ModifyActivity.this, "You must enter a mydata first", Toast.LENGTH_LONG).show();
                    return;
                }
                allMyData.add(enteredMyData);
                mPostReference.child("body").setValue(allMyData);
                mUserPostRefernce.child("body").setValue(allMyData);
                addMyDataBox.setText("");
                recyclerViewAdapter = new RecyclerViewAdapter(ModifyActivity.this, allMyData);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getAllMyData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getAllMyData(DataSnapshot dataSnapshot){

        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
        List<String> arr=null;
        arr= dataSnapshot.child("body").getValue(t);
        allMyData=arr;
        if(arr.isEmpty()){
            mPostReference.removeValue();
            mUserPostRefernce.removeValue();
            Toast.makeText(ModifyActivity.this,
                    "인식된 물품이 없습니다",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        //Log.d("aaaaa",arr.get(0));
        recyclerViewAdapter = new RecyclerViewAdapter(ModifyActivity.this, arr);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void taskDeletion(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String taskTitle = singleSnapshot.getValue(String.class);
            for(int i = 0; i < allMyData.size(); i++){
                if(allMyData.get(i).equals(taskTitle)){
                    allMyData.remove(i);
                }
            }
            Log.d(TAG, "Task tile " + taskTitle);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerViewAdapter = new RecyclerViewAdapter(ModifyActivity.this, allMyData);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}