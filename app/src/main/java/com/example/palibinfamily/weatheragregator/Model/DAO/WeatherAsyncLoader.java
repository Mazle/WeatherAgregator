package com.example.palibinfamily.weatheragregator.Model.DAO;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.GregorianCalendar;
import java.util.concurrent.CompletableFuture;

public class WeatherAsyncLoader extends AsyncTask<Object,Object,WeatherSnapshot> {
    private WeatherAsyncLoaderClallbackListener listener;

    public WeatherAsyncLoader(WeatherAsyncLoaderClallbackListener listener) {
        this.listener = listener;
    }

    @Override
    protected WeatherSnapshot doInBackground(Object... params) {
        //TODO: type & null check!!!!
        String location = (String) params[0];
        String siteTitle = (String) params[1];
        GregorianCalendar date = (GregorianCalendar) params[2];
        WeatherSnapshot weatherSnapshot = null;
        try {
            weatherSnapshot = DAOFacade.getWeatherFrom(siteTitle,date,location);
        }catch (Exception e){

        }
        return weatherSnapshot;
    }

    @Override
    protected void onPostExecute(WeatherSnapshot weatherSnapshot) {
        super.onPostExecute(weatherSnapshot);
        listener.updateData(weatherSnapshot);
    }
}

