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
    private AsyncLocator asyncLocator;
    private LocatorListener listener;

    public LocatorListener getListener() {
        return listener;
    }

    public void setListener(LocatorListener listener) {
        this.listener = listener;
    }

    private class AsyncLocator extends AsyncTask<String, Void, String> {
        private final String TAG = "inetLoader";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запустить индикацию загрузки
        }
        @Override
        protected String doInBackground(String... params) {
            String location = null;
            WeatherParser parser = new WeatherParser();
            parser.getUrl("https://2ip.ru/");
            location = (parser.execXpathToString("html>body>div>div:eq(1)>div:eq(4)>div:eq(1)>div>table>tbody>tr:eq(3)>td"));
            return location;
//            return "Казань Россия";
            //params[0] - Ваша ссылка
            //Получение данных
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //завершить индикацию загрузки
            //Устанавливаете необходимый текст
            Log.d(TAG, "getCityName: " + result);
            listener.updateLocation(result);
        }
    }

    public Locator() {
    }

    public String getLocationName(){
        asyncLocator = new AsyncLocator();
        asyncLocator.execute("");
        return null;
    }
}
