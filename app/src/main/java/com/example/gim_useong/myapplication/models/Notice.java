package com.example.gim_useong.myapplication.models;

public class Notice {
    public String notice_title;
    public String notice_number;
    public String notice_contents;
    public String notice_date;

    public String getNotice_title(){
        return  notice_title;
    }
    public void setNotice_title(String title){
        this.notice_title = title;
    }
    public String getNotice_number(){
        return notice_number;
    }
    public void setNotice_number(String number){
        this.notice_number = number;
    }
    public String getNotice_contents(){ return notice_contents;}
    public void setNotice_contents(String contents){
        this.notice_contents = contents;
    }
    public String getNotice_date(){
        return notice_date;
    }
    public void setNotice_date(String date){
        this.notice_date = date;
    }
}