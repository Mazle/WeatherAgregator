package com.example.palibinfamily.weatheragregator.TmpClassesForTesting;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class Dummies {
    public static ArrayList<WeatherSnapshot> generateTestData(){
        ArrayList<WeatherSnapshot> res = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        WeatherSnapshot snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(true);
        snap.setSnowing(false);
        snap.setHumidity(50);
        snap.setTemperature(25);
        snap.setPressure(1000);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(false);
        snap.setSnowing(false);
        snap.setHumidity(67);
        snap.setTemperature(10);
        snap.setPressure(777);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(false);
        snap.setSnowing(true);
        snap.setHumidity(34);
        snap.setTemperature(-5);
        snap.setPressure(455);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(false);
        snap.setSnowing(false);
        snap.setHumidity(Integer.MIN_VALUE);
        snap.setTemperature(44);
        snap.setPressure(987);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(false);
        snap.setSnowing(false);
        snap.setHumidity(56);
        snap.setTemperature(15);
        snap.setPressure(Integer.MIN_VALUE);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(false);
        snap.setSnowing(false);
        snap.setHumidity(Integer.MIN_VALUE);
        snap.setTemperature(12);
        snap.setPressure(Integer.MIN_VALUE);
        res.add(snap);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        snap = new WeatherSnapshot();
        snap.setDate(calendar.getTime());
        snap.setRaining(true);
        snap.setSnowing(false);
        snap.setHumidity(65);
        snap.setTemperature(10);
        snap.setPressure(1000);
        res.add(snap);
        return res;
    }
}
