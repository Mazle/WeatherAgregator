package com.example.palibinfamily.weatheragregator.Helpers;

import java.util.GregorianCalendar;

public class DateHelper {
    public static GregorianCalendar formatDMY(GregorianCalendar date) {
        //todo:ПРОВЕРИТЬ. вставляет ли он текущие значения даты
        return new GregorianCalendar(date.YEAR, date.MONTH, date.DAY_OF_MONTH);
    }
    public static String stringDMYFormat(GregorianCalendar date, String selector){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.DAY_OF_MONTH)
                     .append(selector)
                     .append(date.MONTH)
                     .append(selector)
                     .append(date.YEAR);
        return stringBuilder.toString();
    }

}
