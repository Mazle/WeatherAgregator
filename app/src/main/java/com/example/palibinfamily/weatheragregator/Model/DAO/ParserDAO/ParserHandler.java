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
            String location = "Россия, Ульяновск";
            //Locator locator = new Locator();
            //location = locator.getCityName();
            WeatherParser parser = new WeatherParser();
            parser.getUrl("https://2ip.ru/");
            location = (parser.execXpathToString("html>body>div>div:eq(1)>div:eq(4)>div:eq(1)>div>table>tbody>tr:eq(3)>td"));

//            location = "Россия, димитровград";

            Log.d(TAG, "doInBackground: location" + location);
            //TODO: вот это вот куда - нибудь вынести

            Document doc = null;
            try {
                doc = Jsoup.parse(new URL("https://www.gismeteo.ru/search/"+location+"/"), 15000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Elements el2 = doc.select("html>body>section>div>div>section>div>div>div:eq(0)>a:eq(0)");
//            System.out.println(el2.attr("href"));
            String href = el2.attr("href");
//            System.out.println(fullHref);
            Log.d(TAG, "doInBackground: href" + href);
            ///////////////////////////////////////////

            FullWeatherParserConfig fullConfig = ConfigHelpers.getConfigGismeteo(href);
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
