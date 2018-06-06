package com.example.gim_useong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class SettingPreference extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_setting_preference);

        final Preference alarm_Switch = (Preference) findPreference("alarm_preference");
        alarm_Switch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean alarm_On = (boolean) newValue;
                if (alarm_On) {
                    // switch on
                    alarm_Switch.setSummary("Switch on");
                } else {
                    // switch off
                    alarm_Switch.setSummary("Switch off");
                }
                return true;
            }
        });

        final Preference sound_Switch = (Preference)findPreference("sound_preference");
        sound_Switch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean sound_On = (boolean) newValue;
                if (sound_On) {
                    // switch on
                    sound_Switch.setSummary("Sound on");
                } else {
                    // switch off
                    sound_Switch.setSummary("Sound off");
                }
                return true;
            }
        });
//
//        ListPreference developer_informatio = (ListPreference)findPreference("developer_information");
//        Preference developer_information = (Preference)findPreference("developer_information");
//        developer_information.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//
//
//                return false;
//            }
//        });

    }
}