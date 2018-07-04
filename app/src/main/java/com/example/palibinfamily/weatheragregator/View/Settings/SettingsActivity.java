package com.example.palibinfamily.weatheragregator.View.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFacade;
import com.example.palibinfamily.weatheragregator.Preferences;
import com.example.palibinfamily.weatheragregator.Presenter.SettingsPresenter;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {
    HashMap<String,CheckBox> dynamicCheckBoxes;
    SettingsPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        //todo ВОПРОС. Чем страшно получать applicationContext?
        presenter = new SettingsPresenter(this.getApplicationContext());
        LinearLayout sitesLinearLayout = (LinearLayout) findViewById(R.id.sitesLinearLayout);
        dynamicCheckBoxes = insertNewCheckBoxes(presenter.getSitesTitlesList(),sitesLinearLayout);
        insertToolbar();


    }

    //заполняет наш контейнер чекбоксами и возвращает лист с id для созданных чекбоксов
    private HashMap<String,CheckBox> insertNewCheckBoxes(ArrayList<String> titles, LinearLayout ll) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        HashMap<String,CheckBox> checkBoxHashMap = new HashMap<>();
        for (String title:titles) {
            CheckBox cb = createCheckBox(title);
            cb.setTag(title);
            cb.setChecked(Preferences.getPreferencesInstant(this).findSavedChoice(title));
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


}
