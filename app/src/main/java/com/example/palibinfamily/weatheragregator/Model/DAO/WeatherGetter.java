package com.example.palibinfamily.weatheragregator.Model.DAO;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.GregorianCalendar;

public interface WeatherGetter {
    public WeatherSnapshot getWeatherFrom(String title, GregorianCalendar date);
}
