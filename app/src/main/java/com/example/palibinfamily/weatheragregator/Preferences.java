package com.example.palibinfamily.weatheragregator;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TESTHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Preferences {
    public final static String PREFERENCES_FILE_NAME = "WeatherAgregatorSettings";
    public final static String NOTIFICATION_FREQUENCY_KEY = "2";
    public final static String CHECKED_SITES_TITLES_SET_KEY = "3";
    public final static String CHECKED_WEATHER_PROPERTIES_KEY = "4";
    private Context context;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Preferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static Preferences getPreferencesInstant(Context context) {
        return ((MyApp) context.getApplicationContext()).getPreferences();
    }
    // todo проверить как работает
    public void saveChangesInPreferences (boolean isChecked, String KEY, int id) {
        //проверяем, существует ли в файле настроек данное множество. Если нет, то добавляем в множество id и записываем в файл
        HashSet<String> dataSet = (HashSet<String>) sharedPreferences.getStringSet(KEY,null);
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
                editor.putStringSet(KEY,updatedDataSet);
            }
            //Если с бокса сняли check, но он ессть в множестве - удалить его из множества
            if (dataSet.contains(stringValueOfId) & !isChecked) {
                updatedDataSet = makeCopyFrom(dataSet);
                updatedDataSet.remove(stringValueOfId);
                if (updatedDataSet.isEmpty()) editor.putStringSet(KEY,null);
                    else editor.putStringSet(KEY,updatedDataSet);
            }
        }
        editor.commit();

    }
    public void saveChangesInPreferences (boolean isChecked, String KEY, Object OTag) {
        //проверяем, существует ли в файле настроек данное множество. Если нет, то добавляем в множество id и записываем в файл
        //Todo ГОВНОКОД с приведением
        HashSet<String> dataSet = (HashSet<String>) sharedPreferences.getStringSet(KEY,null);
        String tag = (String) OTag;
        HashSet<String> updatedDataSet = new HashSet<>();
        if (dataSet==null) {
            updatedDataSet.add(tag);
            editor.putStringSet(KEY,updatedDataSet);
        } else {
            //Если box чекнули и его id нет в множестве, то добавляем, иначе - он и так есть в множестве
            if (!dataSet.contains(tag) & isChecked) {
                updatedDataSet = makeCopyFrom(dataSet);
                updatedDataSet.add(tag);
                editor.putStringSet(KEY,updatedDataSet);
            }
            //Если с бокса сняли check, но он ессть в множестве - удалить его из множества
            if (dataSet.contains(tag) & !isChecked) {
                updatedDataSet = makeCopyFrom(dataSet);
                updatedDataSet.remove(tag);
                if (updatedDataSet.isEmpty()) editor.putStringSet(KEY,null);
                else editor.putStringSet(KEY,updatedDataSet);
            }
        }
        editor.commit();

    }
    //проверяет наличие информации о выборе по заданному сайту
    public boolean findSavedChoice (String title, String KEY){
        Set<String> savedData = sharedPreferences.getStringSet(KEY,null);
        boolean result = false;
        if (savedData!=null) {
            result = savedData.contains(title) ? true : false;
        }
        return result;
    }
    public ArrayList<String> getCheckedTitles () {
        Set<String> setFromFile = sharedPreferences.getStringSet(CHECKED_SITES_TITLES_SET_KEY,null);
        HashSet<String> result = makeCopyFrom((HashSet<String>) setFromFile);
        return new ArrayList<>(result);
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
