package com.example.palibinfamily.weatheragregator;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TESTHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Preferences {
    public final static String PREFERENCES_FILE_NAME = "WeatherAgregatorSettings";
    public final static String NOTIFICATION_FREQUENCY_KEY = "2";
    public final static String CHECKED_SITES_TITLES_SET_KEY = "3";
    public final static String CHECKED_WEATHER_PROPERTIES_KEY = "4";
    private Context context;
    public Preferences(Context context) {
        this.context = context;
    }
    // todo проверить как работает
    public void saveChangesInPreferences (boolean isChecked, String KEY, int id) {
        //получаем файл настроек и editor
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //проверяем, существует ли в файле настроек данное множество. Если нет, то добавляем в множество id и записываем в файл
        HashSet<String> dataSet = new HashSet<>();
        sharedPreferences.getStringSet(KEY,dataSet);
        String stringValueOfId = Integer.toString(id);
        HashSet<String> updatedDataSet = new HashSet<>();
        if (dataSet==null) {
            updatedDataSet.add(stringValueOfId);
            editor.putStringSet(KEY,updatedDataSet);
        } else {
            //Если box чекнули и его id нет в множестве, то добавляем, иначе - он и так есть в множестве
            if (!dataSet.contains(stringValueOfId) & isChecked) {
                updatedDataSet = makeCopyFrom(dataSet);
                updatedDataSet.add(stringValueOfId);
            }
            //Если с бокса сняли check, но он ессть в множестве - удалить его из множества
            if (dataSet.contains(stringValueOfId) & !isChecked) {
                updatedDataSet = makeCopyFrom(dataSet);
                updatedDataSet.remove(stringValueOfId);
                if (updatedDataSet.isEmpty()) editor.putStringSet(KEY,null);
                    else editor.putStringSet(KEY,updatedDataSet);
            }
        }
        editor.apply();

    }
    public ArrayList<String> getCheckedTitles () {
        //todo: DEV.Написать логику извлечения выбранных в настройках заголовках сайтов
        return TESTHelper.getTestTitles();
    }
    public HashMap<String,Boolean> getCheckedWeatherParams() {
        //todo DEV.Написать логику извлечения выбранных в настройках параметров погоды
        return null;
    }
    private HashSet<String> makeCopyFrom(HashSet<String> original) {
        HashSet<String> result = new HashSet<>();
        if (original!=null) {
            for (String value : original) {
                result.add(value);
            }
        }
        return result;
    }
}
