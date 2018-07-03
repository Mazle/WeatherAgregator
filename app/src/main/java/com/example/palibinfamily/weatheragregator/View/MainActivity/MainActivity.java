package com.example.palibinfamily.weatheragregator.View.MainActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.IBinder;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//import com.example.palibinfamily.weatheragregator.R;

import com.example.palibinfamily.weatheragregator.Model.LocationProvider.Locator;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
//import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.Dummies;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.CustomViews.DaysBar;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.CustomViews.MainView;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.MainScreen;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.PlaceSelection.PlaceSelection;
import com.example.palibinfamily.weatheragregator.View.Settings.SettingsActivity;
import com.example.palibinfamily.weatheragregator.View.WeatherView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements WeatherView, View.OnClickListener, ViewPager.OnPageChangeListener {
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    final String LOG_TAG = "MainActivity";
    private static MainActivityPresenter presenter;
    private ArrayList<WeatherSnapshot> weatherList;

    public static MainActivityPresenter getPresenter() {
        return presenter;
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(LOG_TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                MainView mainView = findViewById(R.id.MainView);
                if (mainView != null) {
                    mainView.postInvalidate();
                }
                Log.d(LOG_TAG, "onPageScrolled postInvalidate");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(LOG_TAG, "onPageScrollStateChanged " + state);
            }
        });

//        mViewPager.setCurrentItem();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        intent = new Intent("com.example.darkness.servicetest");

        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };

//        TextView textView = findViewById(R.id.textView);
//        Locator locator = new Locator();
//        textView.setText(locator.getCityName());

        DaysBar daysBar = findViewById(R.id.bottomDaysBar);
        daysBar.setViewPager(mViewPager);
        Log.d(LOG_TAG, "MainActivitypresenter beforecreated");
        presenter = new MainActivityPresenter(this);
        Log.d(LOG_TAG, "MainActivitypresenter aftercreated");
//        weatherList = presenter.getWeatherValuesList();
//        weatherList.sort(new Comparator<WeatherSnapshot>() {
//            @Override
//            public int compare(WeatherSnapshot o1, WeatherSnapshot o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });

//        daysBar.setWeatherDtata(weatherList);

        Geocoder geocoder = new Geocoder(getApplicationContext(),Locale.getDefault());
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocation(54.3147081,48.3618814,5);//uln
//            addresses = geocoder.getFromLocation(54.6086886,48.9230989,5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String res = addresses.get(0).getLocality() + " " + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryName() ;
//        for (Address a:addresses){
//            res += a.getLocality();
//        }
        Log.d(LOG_TAG, "Geocoder: " + res);

        Log.d(LOG_TAG, "MainActivity created");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            return true;
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void onClickStart(View view) {
//        startService(new Intent(this, TestService.class));
//    }
//    public void onClickStop(View view) {
//        stopService(new Intent(this, TestService.class));
//    }

    public void onCityClick(View view) {
        Intent intent = new Intent(MainActivity.this, PlaceSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onClickBind(View v) {
        bindService(intent, sConn, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View v) {
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        DaysBar daysBar = findViewById(R.id.bottomDaysBar);
        daysBar.setPosition(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void update(ArrayList<WeatherSnapshot> snapShots) {
        weatherList = snapShots;
        DaysBar daysBar = findViewById(R.id.bottomDaysBar);
        if (daysBar != null){
            daysBar.setWeatherDtata(weatherList);
        }
        MainView mainView = findViewById(R.id.MainView);
        if (mainView != null) {
            mainView.postInvalidate();
        }
    }

    @Override
    public void updateLocation(String newLocation) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(newLocation);
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            WeatherSnapshot snapshot = null;
            Log.d(LOG_TAG, "SectionsPagerAdapter getItem " + position);
            if ((weatherList != null)&&(position + 1  < weatherList.size())) {
                if (presenter != null) {
                    try {
                        snapshot = presenter.getSnapshotFromDayNumber(position);
                    }catch (Exception e){

                    }
                }
            }

            return MainScreen.newInstance(position+1,snapshot);
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }
    }
}
