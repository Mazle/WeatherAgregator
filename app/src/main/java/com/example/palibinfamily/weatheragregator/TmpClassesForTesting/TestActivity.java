package com.example.palibinfamily.weatheragregator.TmpClassesForTesting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
import com.example.palibinfamily.weatheragregator.R;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        MainActivityPresenter presenter = new MainActivityPresenter(this);
        ArrayList<WeatherSnapshot> list = presenter.getWeatherValuesList();
        WeatherSnapshot snapshot = presenter.getWeatherValuesList().get(6);

        TextView tempView = (TextView) findViewById(R.id.testTemperature);
        tempView.setText(Integer.valueOf(snapshot.getTemperature()).toString());

        TextView pressureView = (TextView) findViewById(R.id.testPressure);
        pressureView.setText(Integer.valueOf(snapshot.getPressure()).toString());

        TextView humidityView = (TextView) findViewById(R.id.testHummidity);
        humidityView.setText(Integer.valueOf(snapshot.getHumidity()).toString());

        TextView speedView = (TextView) findViewById(R.id.testWindSpeed);
        speedView.setText(Integer.valueOf(snapshot.getWindSpeed()).toString());

        TextView cloudView = (TextView) findViewById(R.id.testCloudness);
        cloudView.setText(snapshot.getCloudCover());

        TextView directView = (TextView) findViewById(R.id.testWindDirection);
        directView.setText(snapshot.getWindDirection());

        TextView snowView = (TextView) findViewById(R.id.testSnow);
        snowView.setText(Boolean.valueOf(snapshot.isSnowing()).toString());

        TextView rainView = (TextView) findViewById(R.id.testRain);
        rainView.setText(Boolean.valueOf(snapshot.isRaining()).toString());


    }

}
