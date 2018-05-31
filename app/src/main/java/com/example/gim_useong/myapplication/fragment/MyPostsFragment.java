package com.example.gim_useong.myapplication.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyPostsFragment extends PostListFragment {

    public MyPostsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // 내 포스트
        return databaseReference.child("user-posts")
                .child(getUid());
    }
}
