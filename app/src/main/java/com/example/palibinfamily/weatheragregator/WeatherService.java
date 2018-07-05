package com.example.palibinfamily.weatheragregator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
import com.example.palibinfamily.weatheragregator.View.WeatherView;

import java.util.concurrent.TimeUnit;

public class WeatherService extends Service {
    private static final String TAG = "WeatherService";
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
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i<=500; i++) {
                    Log.d(TAG, " !!!!!!!!!!!!!! i = " + i);
//                    showForegroundNotification("!!!!!!!!!!!!!!");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                stopSelf();
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
