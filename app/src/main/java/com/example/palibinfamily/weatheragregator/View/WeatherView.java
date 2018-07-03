package com.example.palibinfamily.weatheragregator.View;

import android.content.Context;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.ArrayList;

public interface WeatherView {
    void update(ArrayList<WeatherSnapshot> snapShots);
    void updateLocation(String newLocation);
    Context getViewContext();
}
