package com.example.palibinfamily.weatheragregator.Presenter;

import android.support.v7.app.AppCompatActivity;

import com.example.palibinfamily.weatheragregator.Helpers.DateHelper;
import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFacade;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.MyApp;
import com.example.palibinfamily.weatheragregator.Preferences;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/*
* требуется передача активити в конструкторе презентера
* в  презентере берется в расчет, что активити - наследник класса android.support.v7.app.AppCompatActivity;
* ВОПРОС: как усреднять Направление ветра?
* todo написать удаление старых дат из hashMap
 */

public class MainActivityPresenter {
    // Ключ - дата, в формате DateHelper.formatDMY(date)
    private HashMap<String,ArrayList<WeatherSnapshot>> contentListFromSites;
    private HashMap<String,WeatherSnapshot> weatherValues;

    private AppCompatActivity view;
    public MainActivityPresenter() {

    }
    //возвращает map с усредненными прогнозами для
    //todo Внедрить зависимости выдачи в активити от Локализации, Выбранных параметров отображения погоды, языка
    public HashMap<String, WeatherSnapshot> getWeatherValues() {
        //Парсим сайты, выбранные в настройках
        initContentList();
        //усредняем результаты
        getAverageValuesFromAllSnapshotsByDates(contentListFromSites);
        return weatherValues;
    }

    //todo UPGRADE. Сделать настраиваемое количество дней в запросе.
    private HashMap<String, ArrayList<WeatherSnapshot>> initContentList() {
        if (contentListFromSites == null) contentListFromSites = new HashMap<>();
        //cчитываем заголовки сайтов, отмеченные чекбоксом true в настройках
        ArrayList<String> siteTitles = getSiteTitlesFromPreferences();
        //Вызываем парсер, заполняющий weatherSnapshot для каждого дня из каждого источника источников, заносим в contentListFromSites
        return getСontentFromSites(siteTitles);
    }

    //Дает список выбранных в настройках сайтов в качестве источников
    private ArrayList<String> getSiteTitlesFromPreferences() {
        ArrayList<String> siteTitles = ((MyApp)view.getApplicationContext()).getPreferences().getCheckedTitles();
        return siteTitles;
    }
    //Создает map по датам, заполняя значения list'ом из прогнозов по текущей дате
    private HashMap<String,ArrayList<WeatherSnapshot>> getСontentFromSites(ArrayList<String> sitesTitles) {
        GregorianCalendar date = new GregorianCalendar();
        date = DateHelper.formatDMY(date);
        for (String title : sitesTitles) {
            //Todo РЕАЛИЗОВАТЬ. определить удобный способ и воткнуть подтягиватель данных о времени
            //Заполняем map пустыми list
            contentListFromSites.put(DateHelper.stringDMYFormat(date,"."), new ArrayList<WeatherSnapshot>());
            //пустые list в map заполняем данными с сайтов по ключу даты формата string
            contentListFromSites.get(date).add(DAOFacade.getWeatherFrom(title,date));
            //todo ПРОВЕРИТЬ. Не выходит ли дата за 31 день месяца
            date.add(date.DAY_OF_MONTH, date.DAY_OF_MONTH+1);
        }
        return contentListFromSites;
    }
   //вычисляет среднее значение для всех Snapshots в map
    private void getAverageValuesFromAllSnapshotsByDates(HashMap<String,ArrayList<WeatherSnapshot>> map) {
        for (Map.Entry<String,ArrayList<WeatherSnapshot>> entry: map.entrySet()) {
            WeatherSnapshotAgregator agregator = new WeatherSnapshotAgregator();
                for (WeatherSnapshot snapshot: entry.getValue()) {
                    agregator.temperature.add(snapshot.getTemperature());
                    agregator.windSpeed.add(snapshot.getWindSpeed());
                    agregator.windDirection.add(snapshot.getWindDirection());
                    agregator.humidity.add(snapshot.getHumidity());
                    agregator.pressure.add(snapshot.getPressure());
                    agregator.cloudCover.add(snapshot.getCloudCover());
                    agregator.isRaining.add(snapshot.isRaining());
                    agregator.isSnowing.add(snapshot.isSnowing());
                }
                weatherValues.put(entry.getKey(),agregator.averageToSnapshot(new WeatherSnapshot()));
        }
    }

    private class WeatherSnapshotAgregator {
        ArrayList<Integer> temperature;
        ArrayList<Integer> windSpeed;
        ArrayList<String> windDirection;
        ArrayList<Integer> humidity;
        ArrayList<Integer> pressure;
        ArrayList<Integer> cloudCover;
        ArrayList<Boolean> isRaining;
        ArrayList<Boolean> isSnowing;
        WeatherSnapshot averageToSnapshot(WeatherSnapshot snapshot){
            snapshot.setTemperature(getIntegerAveragedValue(this.temperature));
            snapshot.setWindSpeed(getIntegerAveragedValue(this.windSpeed));
            //todo ЗАПЛАТКА.усреднить направление ветра
            snapshot.setWindDirection("North");
            //todo ЗАПЛАТКА.Усреднить дождь.
            snapshot.setRaining(true);
            //todo ЗАПЛАТКА.усреднить снег
            snapshot.setSnowing(false);
            snapshot.setHumidity(getIntegerAveragedValue(this.humidity));
            snapshot.setPressure(getIntegerAveragedValue(this.pressure));
            snapshot.setCloudCover(getIntegerAveragedValue(this.cloudCover));
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
