package com.example.palibinfamily.weatheragregator;

import android.app.Application;

import com.example.palibinfamily.weatheragregator.Preferences;

public class MyApp extends Application {
    Preferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        preferences = new Preferences();
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
