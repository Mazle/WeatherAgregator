package com.example.palibinfamily.weatheragregator;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
import com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity;
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
                    showForegroundNotification("ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы");
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


    private static final int NOTIFICATION_ID = 1;

    private void showForegroundNotification(String contentText) {
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), MainActivity.class);
        showTaskIntent.setAction(Intent.ACTION_MAIN);
        showTaskIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        showTaskIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_weather_few_clouds)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }
}
