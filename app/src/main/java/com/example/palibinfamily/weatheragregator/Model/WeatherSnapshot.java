package com.example.palibinfamily.weatheragregator.Model;

import java.time.LocalDate;

/**
 * Данный объект содержит в себе информацию о погоде, собранную с сайта.
 *Потом информация с нескольких сайтов собирается в презентере и усредняется также в объекте данного класса
 *
 */

public class WeatherSnapshot {
    //todo РЕАЛИЗОВАТЬ. Определить в виде каккого типа переменной передавать дату и внести в класс данную переменную.
    private String weatherSource; // с какакого сайта
    private double temperature;
    private double windSpeed;
    private String windDirection;
    private int humidity;
    private int pressure;
    private int cloudCover;
    private LocalDate date;

    public void setWeatherSource(String weatherSource) {
        this.weatherSource = weatherSource;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
