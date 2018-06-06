package com.example.gim_useong.myapplication.Preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.gim_useong.myapplication.R;

public class developerPreference extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        addPreferencesFromResource(R.xml.activity_developer_preference);
    }
}