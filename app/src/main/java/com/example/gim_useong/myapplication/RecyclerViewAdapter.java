package com.example.gim_useong.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<String> mydata;
    protected Context context;
    public RecyclerViewAdapter(Context context, List<String> mydata) {
        this.mydata = mydata;
        this.context = context;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, mydata);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.categoryTitle.setText(mydata.get(position));

    }
    @Override
    public int getItemCount() {
        return this.mydata.size();
    }
    public void deleteItem(int index) {
        mydata.remove(index);
        notifyItemRemoved(index);
    }
}