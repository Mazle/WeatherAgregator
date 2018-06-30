package com.example.palibinfamily.weatheragregator.TmpClassesForTesting;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

public class TestWeatherSnapshot extends WeatherSnapshot {
    public TestWeatherSnapshot() {
        temperature = -50 + (int) Math.random()*100;

        //pressure =
    }
}
