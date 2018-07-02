package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO;

import android.os.AsyncTask;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.ConfigHelpers;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.FullWeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.DAO.WeatherGetter;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TestWeatherSnapshot;

import java.util.Date;
import java.util.GregorianCalendar;

public class ParserHandler implements WeatherGetter {
    //TODO: убрать костыли, которые я сейчас сюда навтыкаю. т.к. парсер парсит всю неделю сразу
    private final String TAG = "ParserHandler";
    private WeatherSnapshot snapShot = null;//new TestWeatherSnapshot();

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
            snapShot = tmp;
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
//        Log.d(TAG, "getWeatherFrom: " + date.toString());
        return snapShot;//new TestWeatherSnapshot();
    }
}
