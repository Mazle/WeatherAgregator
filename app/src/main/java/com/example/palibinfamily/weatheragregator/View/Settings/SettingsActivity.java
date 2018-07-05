package com.example.palibinfamily.weatheragregator.View.Settings;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFacade;
import com.example.palibinfamily.weatheragregator.Preferences;
import com.example.palibinfamily.weatheragregator.Presenter.SettingsPresenter;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity;
import com.example.palibinfamily.weatheragregator.WeatherService;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity.createExplicitFromImplicitIntent;

public class SettingsActivity extends AppCompatActivity {
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    HashMap<String,CheckBox> dynamicCheckBoxes;
    SettingsPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        intent = createExplicitFromImplicitIntent(this,new Intent("com.example.palibinfamily.weatheragregator"));
        sConn = new ServiceConnection() {
            final String TAG = "ServiceConnection";
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(TAG, "SettingsActivity onServiceConnected");
                try {
                    presenter = new SettingsPresenter(((WeatherService.WeatherBinder) binder).getService());
                    LinearLayout sitesLinearLayout = (LinearLayout) findViewById(R.id.sitesLinearLayout);
                    dynamicCheckBoxes = insertNewCheckBoxes(presenter.getSitesTitlesList(),sitesLinearLayout);
                    insertToolbar();
                    checkChoosenWeatherProperties();
                    bound = true;
                }catch (Exception e){
                    Log.d(TAG, "onServiceConnected: getServicePresenter fail");
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "SettingsActivity onServiceDisconnected");
                bound = false;
            }
        };
        bindService(intent, sConn, BIND_AUTO_CREATE);
        //todo ВОПРОС. Чем страшно получать applicationContext?
//        presenter = new SettingsPresenter(this.getApplicationContext());
//        LinearLayout sitesLinearLayout = (LinearLayout) findViewById(R.id.sitesLinearLayout);
//        dynamicCheckBoxes = insertNewCheckBoxes(presenter.getSitesTitlesList(),sitesLinearLayout);
//        insertToolbar();
//        checkChoosenWeatherProperties();
    }



    //заполняет наш контейнер чекбоксами и возвращает лист с id для созданных чекбоксов
    private HashMap<String,CheckBox> insertNewCheckBoxes(ArrayList<String> titles, LinearLayout ll) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        HashMap<String,CheckBox> checkBoxHashMap = new HashMap<>();
        for (String title:titles) {
            CheckBox cb = createCheckBox(title);
            cb.setTag(title);
            cb.setChecked(Preferences.getPreferencesInstant(this).findSavedChoice(title,Preferences.CHECKED_SITES_TITLES_SET_KEY));
            cb.setLayoutParams(lParams);
            cb.setOnCheckedChangeListener(presenter);

            ll.addView(cb);
            checkBoxHashMap.put(title,cb);

        }
        return checkBoxHashMap;
    }
    private CheckBox createCheckBox(String title) {
        CheckBox cb = new CheckBox(this);
        cb.setText(title);
        return cb;

    }
    private void insertToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("List Activity");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);// возврат на предыдущий activity
            }
        });
    }
    private void checkChoosenWeatherProperties() {
        CheckBox temperatureBox = (CheckBox) findViewById(R.id.temperature);
        boolean presenterValue;
        presenterValue =presenter.getChoiceForPropertiy(temperatureBox.getId());
        if (presenterValue) temperatureBox.setChecked(true);

        CheckBox pressure = (CheckBox) findViewById(R.id.pressure);
        presenterValue=presenter.getChoiceForPropertiy(pressure.getId());
        if (presenterValue) pressure.setChecked(true);

        CheckBox hummidity = (CheckBox) findViewById(R.id.hummidity);
        presenterValue = presenter.getChoiceForPropertiy(hummidity.getId());
        if (presenterValue) hummidity.setChecked(true);

        CheckBox windSpeed = (CheckBox) findViewById(R.id.windSpeed);
        presenterValue =presenter.getChoiceForPropertiy(windSpeed.getId());
        if (presenterValue) windSpeed.setChecked(true);

        CheckBox windDirection = (CheckBox) findViewById(R.id.wind_direction);
        presenterValue = presenter.getChoiceForPropertiy(windDirection.getId());
        if (presenterValue) windDirection.setChecked(true);

        CheckBox precipitation = (CheckBox) findViewById(R.id.precipitation);
        presenterValue = presenter.getChoiceForPropertiy(precipitation.getId());
        if (presenterValue) precipitation.setChecked(true);
    }


    public void onWeatherPropertyClick(View view) {
        presenter.onWeatherPropertyClick(view);
    }

    @Override
    protected void onDestroy() {
        if (bound) {
            unbindService(sConn);
        }
        super.onDestroy();
    }
}
