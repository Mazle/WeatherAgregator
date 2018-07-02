package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO;

import android.os.AsyncTask;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.ConfigHelpers;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.FullWeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.DAO.WeatherGetter;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TestWeatherSnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ParserHandler implements WeatherGetter {
    //TODO: убрать костыли, которые я сейчас сюда навтыкаю. т.к. парсер парсит всю неделю сразу
    private final String TAG = "ParserHandler";
    private WeatherSnapshot snapShot = null;//new TestWeatherSnapshot();
    private HashMap<String,WeatherSnapshot> snapShots = new HashMap<>();

    private class inetLoader extends AsyncTask<String, Void, Void> {
        private final String TAG = "inetLoader";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запустить индикацию загрузки
        }
        @Override
        protected Void doInBackground(String... params) {
            FullWeatherParserConfig fullConfig = ConfigHelpers.getConfigGismeteo();
            WeatherParser parser = new WeatherParser(fullConfig);
            WeatherSnapshot tmp = parser.getWeather();
            tmp.setDate(new Date());
            Log.d(TAG, "doInBackground: " + tmp.getTemperature() + " - temperature");
            Log.d(TAG, "doInBackground: " + tmp.getHumidity() + " - humidity");
            Log.d(TAG, "doInBackground: " + tmp.getWindSpeed() + " - wind");
            Log.d(TAG, "doInBackground: " + tmp.getDate().toLocaleString() + " - date");
            Log.d(TAG, "doInBackground: " + tmp.getWindDirection() + " - getWindDirection");
            snapShot = tmp;

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date());
            String dateKey = DateHelper.stringDMYFormat(gregorianCalendar, ".");
            snapShots.put(dateKey,tmp);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            for (int i = 1; i < 9; i++){
                fullConfig = ConfigHelpers.getConfigGismeteoForDayNum(i);
                parser.setConfig(fullConfig);
                tmp = parser.getWeather();
                tmp.setDate(calendar.getTime());
                gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(calendar.getTime());
                dateKey = DateHelper.stringDMYFormat(gregorianCalendar, ".");
                snapShots.put(dateKey,tmp);
                Log.d(TAG, "doInBackground: " + tmp.getTemperature() + " - temperature");
                Log.d(TAG, "doInBackground: " + tmp.getHumidity() + " - humidity");
                Log.d(TAG, "doInBackground: " + tmp.getWindSpeed() + " - wind");
                Log.d(TAG, "doInBackground: " + tmp.getDate().toLocaleString() + " - date");
                Log.d(TAG, "doInBackground: " + tmp.getWindDirection() + " - getWindDirection");
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

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

    public ParserHandler(){
        //TODO: вот сейчас я буду грузить весь сайт
        inetLoader loader = new inetLoader();
        loader.execute("dummu");
    }


    @Override
    public WeatherSnapshot getWeatherFrom(String Title, GregorianCalendar date) {
        //todo: КОСТЯ. Напиши метод, который переданному заголовку сайта возвращает погоду с данного сайта за указанную дату
        String dateKey = DateHelper.stringDMYFormat(date, ".");
//        Log.d(TAG, "getWeatherFrom: " + dateKey);
        return snapShots.get(dateKey);//new TestWeatherSnapshot();
    }
}
