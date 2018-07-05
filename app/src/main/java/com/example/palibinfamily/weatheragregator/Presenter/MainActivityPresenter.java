package com.example.palibinfamily.weatheragregator.Presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.*;
import com.example.palibinfamily.weatheragregator.Model.LocationProvider.Locator;
import com.example.palibinfamily.weatheragregator.Model.LocationProvider.LocatorListener;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.MyApp;
import com.example.palibinfamily.weatheragregator.Preferences;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.WeatherView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/*
* требуется передача активити в конструкторе презентера
* в  презентере берется в расчет, что активити - наследник класса android.support.v7.app.AppCompatActivity;
* ВОПРОС: как усреднять Направление ветра?
* todo написать удаление старых дат из LinkedHashMap
 */

public class MainActivityPresenter implements WeatherAsyncLoaderClallbackListener, LocatorListener{
    private final String TAG = "MainActivityPresenter";
    // Ключ - дата, в формате DateHelper.formatDMY(date)
    private LinkedHashMap<String,ArrayList<WeatherSnapshot>> contentMapFromSites;
    private LinkedHashMap<String,WeatherSnapshot> averagedWeatherValues;
    //карта соответствия порядкового номера дня соответствующей дате
    private LinkedHashMap<Integer,String> stringDatesInWeatherValues = new LinkedHashMap<>();
    private WeatherView view;
    private String location;

    public MainActivityPresenter() {
        this.view = null;
        Log.d(TAG, "MainActivityPresenter: ");
        //сначала узнаем место. TODO: порыться в настройках

        if (location == null){
            Locator locator = new Locator();
            locator.setListener(this);
            locator.getLocationName();
        }
        //        view.update(getWeatherValuesList());
    }

    public WeatherView getView() {
        return view;
    }

    public void setView(WeatherView view) {
        this.view = view;
        try {
            view.updateLocation(location);
            view.update(getWeatherValuesList());
        }catch (Exception e){
            Log.d(TAG, "setView: no view");
        }
    }

    public MainActivityPresenter(WeatherView view) {
        this.view = view;
        //todo добавить настройку количества дней обзора
        Log.d(TAG, "MainActivityPresenter: ");
        //сначала узнаем место. TODO: порыться в настройках

        if (location == null){
            Locator locator = new Locator();
            locator.setListener(this);
            locator.getLocationName();
        }
        //        view.update(getWeatherValuesList());
    }

    public ArrayList<WeatherSnapshot> getWeatherValuesList() {
        ArrayList<WeatherSnapshot> list = new ArrayList<>();
        for (Map.Entry<Integer,String> entry: stringDatesInWeatherValues.entrySet()) {
            list.add(averagedWeatherValues.get(entry.getValue()));
        }
        return list;
    }
    public WeatherSnapshot getSnapshotFromDayNumber(int numb) {
        List<WeatherSnapshot> result = getWeatherValuesList();
        if (result != null){
            WeatherSnapshot snap = null;
            try{
                snap = result.get(numb);
            }catch (Exception e){

            }
            return snap;
        }else {
            return null;
        }
    }

    //возвращает map с усредненными прогнозами для
    //todo Внедрить зависимости выдачи в активити от Локализации, Выбранных параметров отображения погоды, языка
    public void downloadWeatherValues(int daysAmount) {
        //Парсим сайты, выбранные в настройках
        Log.d(TAG, "downloadWeatherValues");
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
        ArrayList<String> siteTitles = null;
        if (view != null) {
            try {
                siteTitles = ((MyApp) view.getViewContext()).getPreferences().getCheckedTitles();
            }catch (Exception e){
                Log.d(TAG, "getSiteTitlesFromPreferences: no view");
            }
        }
        return siteTitles;
    }
    //Создает map по датам, заполняя значения list'ом из прогнозов по текущей дате из разных сайтов
    private LinkedHashMap<String,ArrayList<WeatherSnapshot>> getСontentFromSites(ArrayList<String> sitesTitles, int dayAmount) {
        if (location != null) {
            GregorianCalendar date = new GregorianCalendar();
            date = DateHelper.formatDMY(date);
            //todo BADPRACTICE.исправить через итераторы
            //Знаю, что не good Practiсe , но вспоминать как работают итераторы времени нет
            int iterationNumber = 0;
            for (String title : sitesTitles) {
                int daysCount = 0;
                GregorianCalendar iteratorDate = DateHelper.getDMYCopy(date);
                while (daysCount < dayAmount) {
                    //Todo РЕАЛИЗОВАТЬ. определить удобный способ и воткнуть подтягиватель данных о времени
                    //Заполняем map пустыми list


                    String dateKey = DateHelper.stringDMYFormat(iteratorDate, ".");
//                // задаем соответствие между порядковыми номероми в листе и значениями ключей в LinkedHashMap
//                //todo БАГФИКС.сделать так, чтобы данные не перезаписывались при каждой итерации
                    if (iterationNumber == 0) {
                        stringDatesInWeatherValues.put(daysCount, dateKey);
                        contentMapFromSites.put(dateKey, new ArrayList<WeatherSnapshot>());
                    }
//                //пустые list в map заполняем данными с сайтов по ключу даты формата string
//                WeatherSnapshot snapshot = DAOFacade.getWeatherFrom(title,iteratorDate);
//                contentMapFromSites.get(dateKey).add(snapshot);

                    WeatherAsyncLoader loader = new WeatherAsyncLoader(this);
                    GregorianCalendar localCalendar = new GregorianCalendar();
                    localCalendar.setTime(iteratorDate.getTime());
                    loader.execute(location, title, localCalendar);

                    DateHelper.increaseDays(iteratorDate, 1);
                    daysCount++;
                    Log.d(TAG, "getСontentFromSites: daysCount = " + daysCount);
                }
                iterationNumber++;
            }
        }
        return contentMapFromSites;
    }
   //вычисляет среднее значение для всех Snapshots в map
    private void getAverageValuesFromAllSnapshotsByDates(LinkedHashMap<String,ArrayList<WeatherSnapshot>> map) {
        if (map != null) {
            for (Map.Entry<String, ArrayList<WeatherSnapshot>> entry : map.entrySet()) {
                WeatherSnapshotAgregator agregator = new WeatherSnapshotAgregator();
                WeatherSnapshot snapshotForAveraging = new WeatherSnapshot();
                if (entry.getValue() != null) {
                    for (WeatherSnapshot snapshot : entry.getValue()) {
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
                    if (averagedWeatherValues == null)
                        averagedWeatherValues = new LinkedHashMap<>();
                    averagedWeatherValues.put(entry.getKey(), agregator.averageToSnapshot(new WeatherSnapshot()));
                }
            }
        }
    }

    @Override
    public void updateData(WeatherSnapshot snapShot) {
        if (snapShot != null) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(snapShot.getDate());
            String dateKey = DateHelper.stringDMYFormat(calendar, ".");
            Log.d(TAG, "updateData: dateKey " + dateKey);
            // задаем соответствие между порядковыми номероми в листе и значениями ключей в LinkedHashMap
            //todo БАГФИКС.сделать так, чтобы данные не перезаписывались при каждой итерации
//            contentMapFromSites.put(dateKey, new ArrayList<WeatherSnapshot>());
            contentMapFromSites.get(dateKey).clear();
            contentMapFromSites.get(dateKey).add(snapShot);
            getAverageValuesFromAllSnapshotsByDates(contentMapFromSites);

//            ArrayList<WeatherSnapshot> tmp = new ArrayList<>();
//            tmp.add(snapShot);
            if (view != null) {
                try {
                    view.update(getWeatherValuesList());
                }catch (Exception e){
                    Log.d(TAG, "updateData: no view");
                }
            }
//            view.update(tmp);
        }else{
            Log.d(TAG, "updateData: snapshotnull");
        }
    }

    @Override
    public void updateLocation(String newLocation) {
        this.location = newLocation;
        //TODO: перекачть погоду
        try {
            downloadWeatherValues(7);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (view != null) {
            try {
                view.updateLocation(newLocation);
                Log.d(TAG, "updateLocation:view");
            }catch (Exception e){
                Log.d(TAG, "updateLocation: no view");
            }
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
            SettingsPresenter settingsPresenter;
            //TODO:  ШТА??? где я тут контекст возьму??
            if (view != null) {
                try {
                    settingsPresenter = new SettingsPresenter(view.getViewContext().getApplicationContext());

                    if (settingsPresenter.getChoiceForPropertiy(R.id.temperature)) {
                        snapshot.setTemperature(getIntegerAveragedValue(this.temperature));
                    } else {
                        snapshot.setTemperature(Integer.MIN_VALUE);
                    }
                    if (settingsPresenter.getChoiceForPropertiy(R.id.windSpeed)) {
                        snapshot.setWindSpeed(getIntegerAveragedValue(this.windSpeed));
                    } else {
                        snapshot.setWindSpeed(Integer.MIN_VALUE);
                    }
                    //todo ЗАПЛАТКА.усреднить направление ветра
//                snapshot.setWindDirection("North");
                    //todo ЗАПЛАТКА.Усреднить дождь.
                    snapshot.setRaining(this.isRaining.get(0));
                    //todo ЗАПЛАТКА.усреднить снег
                    snapshot.setSnowing(this.isSnowing.get(0));
                    if (settingsPresenter.getChoiceForPropertiy(R.id.hummidity)) {
                        snapshot.setHumidity(getIntegerAveragedValue(this.humidity));
                    } else {
                        snapshot.setHumidity(Integer.MIN_VALUE);
                    }
                    if (settingsPresenter.getChoiceForPropertiy(R.id.pressure)) {
                        snapshot.setPressure(getIntegerAveragedValue(this.pressure));
                    } else {
                        snapshot.setPressure(Integer.MIN_VALUE);
                    }
                    //todo ЗАПЛАТКА. усреднить облачность.
                    snapshot.setCloudCover("облачно");
                    //TODO: ЗАТЫЫЫЫЫЫЫЫЫЫЫЧКА на дату и ветер.
                    snapshot.setDate(this.date);
                    if (settingsPresenter.getChoiceForPropertiy(R.id.wind_direction)) {
                        snapshot.setWindDirection(this.windDirection.get(0));
                    } else {
                        snapshot.setWindDirection(null);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "averageToSnapshot: no view");
                }
            }
        return snapshot;
        }
        //Todo ПРОВЕРИТЬ. Будет ли так работать Jenerick.
        private Integer getIntegerAveragedValue(ArrayList<Integer> list) {
            Integer summ = 0;//Integer.MIN_VALUE;
            Integer num = 0;
            try {
                for (Integer value : list) {
                    if ((value <10000)&&(value > -1000)) {
                        summ = summ + value;
                        num++;
                    }
                }
                //по моему здесь может таиться какая то подстава, но мне некогда читать по этой теме
                // ага, division by zero например =)
                summ = summ / num;
            }catch (Exception e){
                summ = Integer.MIN_VALUE;
            }
            return summ;
        }
        private Double getDoubleAveragedValue(ArrayList<Double> list) {
            Double summ = 0d;
            try {
                for (Double value:list) {
                    summ = summ + value;
                }
                summ = summ / list.size();
            }catch (Exception e){

            }
            return summ;
        }
    }

}
