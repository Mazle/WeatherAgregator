package com.example.palibinfamily.weatheragregator.Model.DAO;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.ParserDAOFactory;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class DAOFactory {
            //Передаваемый классу параметр для определения исчтоника объектов данных.
        public static final int PARSER_DATA = 1;


        //Список объектов, которые должен уметь обрабатывать наш обработчик.
        //todo дописать в DAO фабрики и классы
        //
        public abstract WeatherGetter getWeatherGetter();
        public abstract ArrayList<String> getSiteTitles();


        public static DAOFactory getFactoryDao(int type) {
            switch (type) {
                case (PARSER_DATA):
                    return new ParserDAOFactory();
                default:
                    return null;
            }
        }
    }


