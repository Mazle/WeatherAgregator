package com.example.palibinfamily.weatheragregator.Model.DAO;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.ParserHandler;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DAOFacade {
    private static DAOFactory actualFactory = DAOFactory.getFactoryDao(DAOFactory.PARSER_DATA);
    private static WeatherGetter weatherGetter = actualFactory.getWeatherGetter();

    public static ArrayList getTitlesList () {
        return actualFactory.getSiteTitles();
    }
    public static WeatherSnapshot getWeatherFrom(String SiteTitle, GregorianCalendar date) {
        return weatherGetter.getWeatherFrom(SiteTitle, date);
    }


}
