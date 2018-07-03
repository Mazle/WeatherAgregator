package com.example.palibinfamily.weatheragregator.Presenter;

import android.content.Context;
import android.widget.CompoundButton;

import com.example.palibinfamily.weatheragregator.Model.DAO.DAOFacade;

import java.util.ArrayList;

public class SettingsPresenter implements CompoundButton.OnCheckedChangeListener{
    Context context;
    public SettingsPresenter(Context context) {
        this.context = context;
    }

    public ArrayList<String> getSitesTitlesList() {
        return DAOFacade.getTitlesList();
}

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }


}
