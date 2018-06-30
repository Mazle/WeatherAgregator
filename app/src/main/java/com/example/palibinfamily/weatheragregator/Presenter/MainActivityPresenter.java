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

//требуется передача активити в конструкторе презентера
public class MainActivityPresenter {
    // Ключ - дата, в формате DateHelper.formatDMY(date)
    private HashMap<String,ArrayList<WeatherSnapshot>> contentListFromSites;
    private HashMap<String,WeatherSnapshot>

    private AppCompatActivity view;
    public MainActivityPresenter() {

    }
    //возвращает map с усредненными прогнозами для
    public HashMap<String, WeatherSnapshot> getAverageWeatherSnapshotList() {
        //Парсим сайты, выбранные в настройках
        initContentList();
        //усредняем результаты

        return ;
    }

    //todo UPGRADE. Сделать настраиваемое количество дней в запросе.
    private HashMap<String, ArrayList<WeatherSnapshot>> initContentList() {
        if (contentListFromSites == null) contentListFromSites = new HashMap<>();
        //cчитываем заголовки сайтов, отмеченные чекбоксом true в настройках
        ArrayList<String> siteTitles = getSiteTitlesFromPreferences();
        //Вызываем парсер, заполняющий weatherSnapshot для каждого из источников заносим в contentForAdapter
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
            //todo ОТРЕДАКТИРОВАТЬ. формат данных даты
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
    private WeatherSnapshot getAverageValueFromAllSnapshots(HashMap<String,ArrayList<WeatherSnapshot>> map) {
        WeatherSnapshot averagedSnapshot = new WeatherSnapshot();

        for (Map.Entry<String,ArrayList<WeatherSnapshot>> list: map.entrySet()) {

        }
    }

}
