package com.example.gim_useong.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
        Button addMyDataButton = (Button)findViewById(R.id.add_mydata_button);
        getAllMyData();
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

    }
    private void getAllMyData(){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
               // Post post = dataSnapshot.getValue(Post.class);
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                List<String> arr= dataSnapshot.child("body").getValue(t);
                allMyData=arr;
                //Log.d("aaaaa",arr.get(0));
                recyclerViewAdapter = new RecyclerViewAdapter(ModifyActivity.this, arr);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(ModifyActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);


    }
    private void mydataDeletion(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String mydataTitle = singleSnapshot.getValue(String.class);
            for(int i = 0; i < allMyData.size(); i++){
                if(allMyData.get(i).equals(mydataTitle)){
                    allMyData.remove(i);
                }
            }
            Log.d(TAG, "MyData tile " + mydataTitle);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerViewAdapter = new RecyclerViewAdapter(ModifyActivity.this, allMyData);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}