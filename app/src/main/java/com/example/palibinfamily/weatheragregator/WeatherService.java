package com.example.palibinfamily.weatheragregator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
import com.example.palibinfamily.weatheragregator.View.WeatherView;

public class WeatherService extends Service {
    private MainActivityPresenter servicePresenter = new MainActivityPresenter();
    private WeatherBinder binder = new WeatherBinder();
    private Preferences preferences;
    public class WeatherBinder extends Binder{
        public WeatherService getService(WeatherView view){
            servicePresenter.setView(view);
            return WeatherService.this;
        }
        public WeatherService getService(){
            return WeatherService.this;
        }
    }

    public Preferences getPreferences() {
        preferences = new Preferences(getApplicationContext());
        return preferences;
    }

    public MainActivityPresenter getServicePresenter() {
        return servicePresenter;
    }

    public WeatherService() {
//        preferences = new Preferences(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
