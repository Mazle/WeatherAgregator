package com.example.palibinfamily.weatheragregator;

import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TESTHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Preferences {
    public final static String PREFERENCES_FILE_NAME = "1";
    public final static String NOTIFICATION_FREQUENCY_KEY = "2";
    public final static String CHECKED_SITES_TITLES_SET_KEY = "3";
    public final static String CHECKED_WEATHER_PROPERTIES_KEY = "4";

    public void saveChangesInPreferences (boolean isChecked, String KEY, int id) {

    }
    public ArrayList<String> getCheckedTitles () {
        //todo: DEV.Написать логику извлечения выбранных в настройках заголовках сайтов
        return TESTHelper.getTestTitles();
    }
    public HashMap<String,Boolean> getCheckedWeatherParams() {
        //todo DEV.Написать логику извлечения выбранных в настройках параметров погоды
        return null;
    }

}
