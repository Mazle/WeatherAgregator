package com.example.palibinfamily.weatheragregator.Model.LocationProvider;

import android.os.AsyncTask;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.ConfigHelpers;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.FullWeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;

public class Locator {
    private static final String TAG = "Locator";
    String location = null;
    AsyncLocator asyncLocator;

    private class AsyncLocator extends AsyncTask<String, Void, Void> {
        private final String TAG = "inetLoader";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запустить индикацию загрузки
        }
        @Override
        protected Void doInBackground(String... params) {
            WeatherParser parser = new WeatherParser();
            location = (parser.execConfigElementString(ConfigHelpers.getConfig2ip()));
            return null;
            //params[0] - Ваша ссылка
            //Получение данных
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //завершить индикацию загрузки
            //Устанавливаете необходимый текст
        }
    }

    public Locator() {
        asyncLocator = new AsyncLocator();
        asyncLocator.execute("");
    }

    public String getCityName(){

        while (location == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "getCityName: " + location);
        return location;
    }
}
