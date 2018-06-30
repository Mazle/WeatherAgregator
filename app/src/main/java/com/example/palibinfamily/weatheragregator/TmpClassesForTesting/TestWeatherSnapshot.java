package com.example.palibinfamily.weatheragregator.TmpClassesForTesting;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

public class TestWeatherSnapshot extends WeatherSnapshot {
    public TestWeatherSnapshot() {
        temperature = -50 + (int) (Math.random()*100);
        pressure = 700 + (int) (Math.random()*90);
        humidity = (int) (Math.random()*100);
        windDirection = "North";
        windSpeed = (int) (Math.random()*8);
        isRaining = true;
        isSnowing = false;
        cloudCover = "Облачно";
    }
}
