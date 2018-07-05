package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO;

import android.os.AsyncTask;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.ConfigHelpers;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.FullWeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.DAO.WeatherGetter;
import com.example.palibinfamily.weatheragregator.Model.LocationProvider.Locator;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TestWeatherSnapshot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ParserHandler implements WeatherGetter {
    //TODO: убрать костыли, которые я сейчас сюда навтыкаю. т.к. парсер парсит всю неделю сразу
    private final String TAG = "ParserHandler";
    private WeatherSnapshot snapShot = null;//new TestWeatherSnapshot();
    private HashMap<String,HashMap> cahce = new HashMap<>();
    private String cacheLocation = "";


    private void download(String Title,FullWeatherParserConfig fullConfig){
        HashMap<String,WeatherSnapshot> snapShots = new HashMap<>();
        WeatherParser parser = null;//new WeatherParser();
        parser = new WeatherParser(fullConfig);
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
        Log.d(TAG, "doInBackground: dateKey " + dateKey);
        snapShots.put(dateKey, tmp);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        for (int i = 1; i < 9; i++) {
            ///TODO: вот тут нехорошая затычка. захардкожен яндекс/гисметео
            if (Title.equals("GisMeteo")) {
                fullConfig = ConfigHelpers.getConfigGismeteoForDayNum(i);
            }else{
                fullConfig = ConfigHelpers.getConfigYandexForDayNum(i);
            }
            parser.setConfig(fullConfig);
            tmp = parser.getWeather();
            tmp.setDate(calendar.getTime());
            gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(calendar.getTime());
            dateKey = DateHelper.stringDMYFormat(gregorianCalendar, ".");
            snapShots.put(dateKey, tmp);
            Log.d(TAG, "doInBackground: dateKey " + dateKey);
            Log.d(TAG, "doInBackground: " + tmp.getTemperature() + " - temperature");
            Log.d(TAG, "doInBackground: " + tmp.getHumidity() + " - humidity");
            Log.d(TAG, "doInBackground: " + tmp.getWindSpeed() + " - wind");
            Log.d(TAG, "doInBackground: " + tmp.getDate().toLocaleString() + " - date");
            Log.d(TAG, "doInBackground: " + tmp.getWindDirection() + " - getWindDirection");
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        cahce.put(Title,snapShots);
    }

    @Override
    public WeatherSnapshot getWeatherFrom(String Title, GregorianCalendar date, String location) {
        //todo: КОСТЯ. Напиши метод, который переданному заголовку сайта возвращает погоду с данного сайта за указанную дату
        String returnDateKey = DateHelper.stringDMYFormat(date, ".");
//        Log.d(TAG, "getWeatherFrom: " + dateKey);

        HashMap<String,WeatherSnapshot> cacheResult = cahce.get(Title);
        Log.d(TAG, "doInBackground: Title" + Title);

        if ((cacheResult != null)&& cacheResult.containsKey(returnDateKey) && (location.equals(cacheLocation))) {
            Log.d(TAG, "getWeatherFrom: cache" + returnDateKey);
            return cacheResult.get(returnDateKey);
        }else {
            ///TODO: вот тут нехорошая затычка. захардкожен поиск яндекс/гисметео
            FullWeatherParserConfig fullConfig;
            if (Title.equals("GisMeteo")) {
                Log.d(TAG, "getWeatherFrom: location" + returnDateKey);
                cacheLocation = location;
                Log.d(TAG, "doInBackground: returnDateKey " + returnDateKey);

                //TODO: вот это вот куда - нибудь вынести
                Document doc = null;
                try {
                    doc = Jsoup.parse(new URL("https://www.gismeteo.ru/search/" + location + "/"), 15000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Elements el2 = doc.select("html>body>section>div>div>section>div>div>div:eq(0)>a:eq(0)");
//            System.out.println(el2.attr("href"));
                String href = el2.attr("href");
//            System.out.println(fullHref);
                Log.d(TAG, "doInBackground: href" + href);


                fullConfig = ConfigHelpers.getConfigGismeteo(href);
            }else{
                byte[] bytes = location.getBytes();
                StringBuilder sb = new StringBuilder();
                for (int i=0; i < bytes.length; i++){
                    sb.append("%");
                    sb.append(String.format("%02X", bytes[i]));
                }
                System.out.println(sb.toString());

                Document doc = null;
                try {
                    doc = Jsoup.parse(new URL("https://yandex.ru/pogoda/search?request=" + sb.toString() ), 15000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Elements el2 = doc.select("html>body>div:eq(2)>div>div>div:eq(0)>ul>li:eq(0)>a");
//                System.out.println(el2.attr("href"));

                fullConfig = ConfigHelpers.getConfigYandex(el2.attr("href"));
                Log.d(TAG, "doInBackground: getConfigYandex" + el2.attr("href"));
            }
            download(Title, fullConfig);
            cacheResult = cahce.get(Title);
            return cacheResult.get(returnDateKey);//new TestWeatherSnapshot();
        }
    }
}
