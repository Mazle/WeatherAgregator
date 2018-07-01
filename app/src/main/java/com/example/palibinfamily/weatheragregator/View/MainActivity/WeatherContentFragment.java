package com.example.palibinfamily.weatheragregator.View.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.palibinfamily.weatheragregator.R;

public class WeatherContentFragment extends Fragment {
    private TextView temperature;
    private TextView windSpeed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_content_fragment,container, false);
        temperature = (TextView) view.findViewById(R.id.temperature);
        windSpeed = (TextView) view.findViewById(R.id.windSpeed);

        return view;
    }

   // public void setTemperature(int temperature) {
   //     this.temperature.setText();
    //}
}
