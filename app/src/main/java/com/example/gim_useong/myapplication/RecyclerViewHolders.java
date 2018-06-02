package com.example.gim_useong.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
public class RecyclerViewHolders extends RecyclerView.ViewHolder{
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public ImageView markIcon;
    public TextView categoryTitle;
    public ImageView deleteIcon;
    private List<String> mydataObject;

    private RecyclerViewAdapter recyclerViewAdapter;
    public RecyclerViewHolders(final View itemView, final List<String> mydataObject) {
        super(itemView);
        this.mydataObject = mydataObject;
        categoryTitle = (TextView)itemView.findViewById(R.id.mydata_title);
        markIcon = (ImageView)itemView.findViewById(R.id.mydata_icon);
        deleteIcon = (ImageView)itemView.findViewById(R.id.mydata_delete);

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete icon has been clicked", Toast.LENGTH_LONG).show();
                final String mydataTitle = mydataObject.get(getAdapterPosition());
                Log.d(TAG, "MyData Title " + mydataTitle);


                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });


            }
        });

    }
}