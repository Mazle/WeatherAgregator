package com.example.palibinfamily.weatheragregator.Model.DAO;

import android.util.Log;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.ParserHandler;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DAOFacade {
    private static final String TAG = "DAOFacade";
    private static DAOFactory actualFactory = DAOFactory.getFactoryDao(DAOFactory.PARSER_DATA);
    private static WeatherGetter weatherGetter = actualFactory.getWeatherGetter();

    public static ArrayList getTitlesList () {
        return actualFactory.getSiteTitles();
    }
    public static WeatherSnapshot getWeatherFrom(String SiteTitle, GregorianCalendar date) {
        //TODO: и тут костыль
        WeatherSnapshot snap = null;
        while (snap == null){
            snap = weatherGetter.getWeatherFrom(SiteTitle, date);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "getWeatherFrom: " + snap.getDate().toLocaleString());
        return snap;
    }


}
