package com.example.palibinfamily.weatheragregator.Helpers;

import java.util.GregorianCalendar;

public class DateHelper {
    public static GregorianCalendar formatDMY(GregorianCalendar date) {
        //todo:ПРОВЕРИТЬ. вставляет ли он текущие значения даты
        return new GregorianCalendar(date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH));
    }
    public static String stringDMYFormat(GregorianCalendar date, String selector){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.get(GregorianCalendar.DAY_OF_MONTH))
                     .append(selector)
                     .append(date.get(GregorianCalendar.MONTH))
                     .append(selector)
                     .append(date.get(GregorianCalendar.YEAR));
        return stringBuilder.toString();
    }
    public static void increaseDays (GregorianCalendar date, int daysToPlus) {
        date.set(GregorianCalendar.DAY_OF_MONTH,date.get(GregorianCalendar.DAY_OF_MONTH)+daysToPlus);
    }
    public static GregorianCalendar getDMYCopy (GregorianCalendar date) {
        return  new GregorianCalendar(date.get(GregorianCalendar.YEAR),
                date.get(GregorianCalendar.MONTH),
                date.get(GregorianCalendar.DAY_OF_MONTH));
    }

}
