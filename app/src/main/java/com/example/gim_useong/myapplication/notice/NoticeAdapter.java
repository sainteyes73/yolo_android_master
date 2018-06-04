package com.example.gim_useong.myapplication.notice;

import android.content.Context;
import android.media.audiofx.NoiseSuppressor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gim_useong.myapplication.R;
import com.example.gim_useong.myapplication.models.Notice;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter{
    private ArrayList<Notice> noticeList = new ArrayList<>();

    @Override
    public int getCount(){ return noticeList.size(); }

    @Override
    public Notice getItem(int position){ return noticeList.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_row_list, parent, false);
        }

        TextView n_number = (TextView)convertView.findViewById(R.id.notice_number);
        TextView n_title = (TextView)convertView.findViewById(R.id.notice_title);
        TextView n_date = (TextView)convertView.findViewById(R.id.notice_date);

        Notice noticeListData = getItem(position);

        n_number.setText(noticeListData.getNotice_number());
        n_title.setText(noticeListData.getNotice_title());
        n_date.setText(noticeListData.getNotice_date());

        n_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    public void addItem(String t, String d, String c) {
        Notice notice_list_data = new Notice();

        notice_list_data.setNotice_title(t);
        notice_list_data.setNotice_date(d);
        notice_list_data.setNotice_contents(c);

        noticeList.add(notice_list_data);
    }

}
