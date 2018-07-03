package com.example.palibinfamily.weatheragregator.Model.DAO;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

public interface WeatherAsyncLoaderClallbackListener {
    void updateData(WeatherSnapshot snapShot);
}
