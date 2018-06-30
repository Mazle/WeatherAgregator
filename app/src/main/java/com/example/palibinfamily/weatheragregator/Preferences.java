package com.example.palibinfamily.weatheragregator;

import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TESTHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Preferences {
    public ArrayList<String> getCheckedTitles () {
        //todo: DEV.Написать логику извлечения выбранных в настройках заголовках сайтов
        return TESTHelper.getTestTitles();
    }
    public HashMap<String,Boolean> getCheckedWeatherParams() {
        //todo DEV.Написать логику извлечения выбранных в настройках параметров погоды
        return null;
    }

}
