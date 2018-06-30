package com.example.palibinfamily.weatheragregator.View.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setContent() {
        //todo: написать метод заполнения адаптера
    }
}
