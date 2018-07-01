package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO;

import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFactory;
import com.example.palibinfamily.weatheragregator.Model.DAO.WeatherGetter;
import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.TESTHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ParserDAOFactory extends DAOFactory {
    @Override
    /*String - наименование заголовка сайта, Whether getter - реализация самого обработчика для конкретного сайта (ее проще
    *задать анонимаными классами в данном случае, мне кажется.
    */
    public WeatherGetter getWeatherGetter() {
        return new ParserHandler();
    }

    @Override
    public ArrayList<String> getSiteTitles() {
        //todo: КОСТЯ. Напиши метод, который парсит твой конфиг и берет заголовки сайтов, доступных для парсинга погоды
       return TESTHelper.getTestTitles();
    }
}
