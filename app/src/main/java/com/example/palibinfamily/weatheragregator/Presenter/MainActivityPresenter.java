package com.example.palibinfamily.weatheragregator.Presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFacade;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.MyApp;
import com.example.palibinfamily.weatheragregator.Preferences;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* требуется передача активити в конструкторе презентера
* в  презентере берется в расчет, что активити - наследник класса android.support.v7.app.AppCompatActivity;
* ВОПРОС: как усреднять Направление ветра?
* todo написать удаление старых дат из LinkedHashMap
 */

public class MainActivityPresenter {
    private final String TAG = "MainActivityPresenter";
    // Ключ - дата, в формате DateHelper.formatDMY(date)
    private LinkedHashMap<String,ArrayList<WeatherSnapshot>> contentMapFromSites;
    private LinkedHashMap<String,WeatherSnapshot> averagedWeatherValues;
    //карта соответствия порядкового номера дня соответствующей дате
    private LinkedHashMap<Integer,String> stringDatesInWeatherValues = new LinkedHashMap<>();
    private AppCompatActivity view;

    public MainActivityPresenter(AppCompatActivity view) {
        this.view = view;
        //todo добавить настройку количества дней обзора
        Log.d(TAG, "MainActivityPresenter: ");
        downloadWeatherValues(7);

    }

    public ArrayList<WeatherSnapshot> getWeatherValuesList() {
        ArrayList<WeatherSnapshot> list = new ArrayList<>();
        for (Map.Entry<Integer,String> entry: stringDatesInWeatherValues.entrySet()) {
            list.add(averagedWeatherValues.get(entry.getValue()));
        }
        return list;
    }
    public WeatherSnapshot getSnapshotFromDayNumber(int numb) {
        return getWeatherValuesList().get(numb);
    }

    //возвращает map с усредненными прогнозами для
    //todo Внедрить зависимости выдачи в активити от Локализации, Выбранных параметров отображения погоды, языка
    private void downloadWeatherValues(int daysAmount) {
        //Парсим сайты, выбранные в настройках
        initContentList(daysAmount);
        //усредняем результаты
        getAverageValuesFromAllSnapshotsByDates(contentMapFromSites);
    }
    private LinkedHashMap<String, ArrayList<WeatherSnapshot>> initContentList(int daysAmount) {
        if (contentMapFromSites == null) contentMapFromSites = new LinkedHashMap<>();
        //cчитываем заголовки сайтов, отмеченные чекбоксом true в настройках
        ArrayList<String> siteTitles = getSiteTitlesFromPreferences();
        //Вызываем парсер, заполняющий weatherSnapshot для каждого дня из каждого источника, заносим в contentListFromSites
        return getСontentFromSites(siteTitles,daysAmount);
    }
    //Дает список выбранных в настройках сайтов в качестве источников
    private ArrayList<String> getSiteTitlesFromPreferences() {
        ArrayList<String> siteTitles = ((MyApp)view.getApplicationContext()).getPreferences().getCheckedTitles();
        return siteTitles;
    }
    //Создает map по датам, заполняя значения list'ом из прогнозов по текущей дате из разных сайтов
    private LinkedHashMap<String,ArrayList<WeatherSnapshot>> getСontentFromSites(ArrayList<String> sitesTitles, int dayAmount) {
        GregorianCalendar date = new GregorianCalendar();
        date = DateHelper.formatDMY(date);
        //todo BADPRACTICE.исправить через итераторы
        //Знаю, что не good Practiсe , но вспоминать как работают итераторы времени нет
        int iterationNumber = 0;
        for (String title : sitesTitles) {
            int daysCount = 0;
            GregorianCalendar iteratorDate = DateHelper.getDMYCopy(date);
            while (daysCount< dayAmount) {
                //Todo РЕАЛИЗОВАТЬ. определить удобный способ и воткнуть подтягиватель данных о времени
                //Заполняем map пустыми list
                String dateKey = DateHelper.stringDMYFormat(iteratorDate, ".");
                // задаем соответствие между порядковыми номероми в листе и значениями ключей в LinkedHashMap
                //todo БАГФИКС.сделать так, чтобы данные не перезаписывались при каждой итерации
                if (iterationNumber==0) {
                    stringDatesInWeatherValues.put(daysCount, dateKey);
                    contentMapFromSites.put(dateKey, new ArrayList<WeatherSnapshot>());
                }
                //пустые list в map заполняем данными с сайтов по ключу даты формата string
                WeatherSnapshot snapshot = DAOFacade.getWeatherFrom(title,iteratorDate);
                contentMapFromSites.get(dateKey).add(snapshot);
                DateHelper.increaseDays(iteratorDate,1);
                daysCount++;
                Log.d(TAG, "getСontentFromSites: daysCount = " + daysCount);
            }
            iterationNumber++;
        }
        return contentMapFromSites;
    }
   //вычисляет среднее значение для всех Snapshots в map
    private void getAverageValuesFromAllSnapshotsByDates(LinkedHashMap<String,ArrayList<WeatherSnapshot>> map) {
        for (Map.Entry<String,ArrayList<WeatherSnapshot>> entry: map.entrySet()) {
            WeatherSnapshotAgregator agregator = new WeatherSnapshotAgregator();
            WeatherSnapshot snapshotForAveraging = new WeatherSnapshot();
            for (WeatherSnapshot snapshot: entry.getValue()) {
                    agregator.temperature.add(snapshot.getTemperature());
                    agregator.windSpeed.add(snapshot.getWindSpeed());
                    agregator.windDirection.add(snapshot.getWindDirection());
                    agregator.humidity.add(snapshot.getHumidity());
                    agregator.pressure.add(snapshot.getPressure());
                    agregator.cloudCover.add(snapshot.getCloudCover());
                    agregator.isRaining.add(snapshot.isRaining());
                    agregator.isSnowing.add(snapshot.isSnowing());
                    snapshotForAveraging.setDate(snapshot.getDate());

                    //TODO: дата дата
                    agregator.date = snapshot.getDate();
                }
            if (averagedWeatherValues == null) averagedWeatherValues = new LinkedHashMap<>();
            averagedWeatherValues.put(entry.getKey(),agregator.averageToSnapshot(new WeatherSnapshot()));
        }
    }
    //класс, представляющий собой WeatherSnapshot, только в качестве полей - arrayList со значениями Полей других WeatherSnapshot
    private class WeatherSnapshotAgregator {
        ArrayList<Integer> temperature;
        ArrayList<Integer> windSpeed;
        ArrayList<String> windDirection;
        ArrayList<Integer> humidity;
        ArrayList<Integer> pressure;
        ArrayList<String> cloudCover;
        ArrayList<Boolean> isRaining;
        ArrayList<Boolean> isSnowing;
        Date date;

        public WeatherSnapshotAgregator() {
            this.temperature = new ArrayList<>();
            this.windSpeed = new ArrayList<>();
            this.windDirection = new ArrayList<>();
            this.humidity = new ArrayList<>();
            this.pressure = new ArrayList<>();
            this.cloudCover = new ArrayList<>();
            this.isRaining = new ArrayList<>();
            this.isSnowing = new ArrayList<>();
        }

        private WeatherSnapshot averageToSnapshot(WeatherSnapshot snapshot){
            snapshot.setTemperature(getIntegerAveragedValue(this.temperature));
            snapshot.setWindSpeed(getIntegerAveragedValue(this.windSpeed));
            //todo ЗАПЛАТКА.усреднить направление ветра
            snapshot.setWindDirection("North");
            //todo ЗАПЛАТКА.Усреднить дождь.
            snapshot.setRaining(this.isRaining.get(0));
            //todo ЗАПЛАТКА.усреднить снег
            snapshot.setSnowing(this.isSnowing.get(0));
            snapshot.setHumidity(getIntegerAveragedValue(this.humidity));
            snapshot.setPressure(getIntegerAveragedValue(this.pressure));
            //todo ЗАПЛАТКА. усреднить облачность.
            snapshot.setCloudCover("облачно");
            //TODO: ЗАТЫЫЫЫЫЫЫЫЫЫЫЧКА на дату и ветер.
            snapshot.setDate(this.date);
            snapshot.setWindDirection(this.windDirection.get(0));
        return snapshot;
        }
        //Todo ПРОВЕРИТЬ. Будет ли так работать Jenerick.
        private Integer getIntegerAveragedValue(ArrayList<Integer> list) {
            Integer summ = 0;
            for (Integer value:list) {
                summ = summ + value;
            }
            //по моему здесь может таиться какая то подстава, но мне некогда читать по этой теме
            return summ/list.size();
        }
        private Double getDoubleAveragedValue(ArrayList<Double> list) {
            Double summ = 0d;
            for (Double value:list) {
                summ = summ + value;
            }
            return summ/list.size();
        }
    }

}
