package com.example.palibinfamily.weatheragregator.View.PlaceSelection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.CustomViews.MainView;
import com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class PlaceSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    // country spinner
    private locationItem country;
    private locationItem state;
    private locationItem city;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner :{
                locationItem selected = (locationItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "country " + selected.name + " id " + selected.id, Toast.LENGTH_LONG).show();
                //http://www.gosigmaway.com/api/ca_api/api.php?type=getCities&countryId=181&stateId=3067
                country = selected;
                jsonLoader countryLoader = new jsonLoader();
                countryLoader.execute("http://www.gosigmaway.com/api/ca_api/api.php?type=getStates&countryId="+country.id,"state");
                break;}
            case R.id.spinner2 :{
                locationItem selected = (locationItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "state " + selected.name + " id " + selected.id, Toast.LENGTH_LONG).show();
                //http://www.gosigmaway.com/api/ca_api/api.php?type=getCities&countryId=181&stateId=3067
                state = selected;
                jsonLoader countryLoader = new jsonLoader();
                countryLoader.execute("http://www.gosigmaway.com/api/ca_api/api.php?type=getCities&countryId="+country.id+"&stateId="+state.id,"city");
                break;}
            case R.id.spinner3 :{
                locationItem selected = (locationItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "city " + selected.name + " id " + selected.id, Toast.LENGTH_LONG).show();
                //http://www.gosigmaway.com/api/ca_api/api.php?type=getCities&countryId=181&stateId=3067
                city = selected;
                break;}
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        MainActivity.getPresenter().updateLocation(country.name + " " + city.name);
    }

    private class locationItem{
        private String name;
        private int id;

        public locationItem() {
        }

        public locationItem(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    private class jsonLoader extends AsyncTask<String, Void, Void> {
        private final String TAG = "CountryLoader";
        private ArrayList<locationItem> resultList = new ArrayList<>();
        private String resultType;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запустить индикацию загрузки
        }
        @Override
        protected Void doInBackground(String... params) {
            resultType = params[1];
            URL gosigmaway = null; // URL to Parse
            try {
                gosigmaway = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection connection = null;
            try {
                connection = gosigmaway.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jObject = null;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                for (String line; (line = bufferedReader.readLine()) != null;) {
                    sb.append(line);
                }
                jObject = new JSONObject(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                JSONArray array = jObject.getJSONArray("result");
                int len = array.length();
                for (int i = 0; i < len; i++) {
                    JSONObject json = array.getJSONObject(i);
                    resultList.add(new locationItem(json.getString("name").toString(), json.getInt("id")));
                }
            }catch (Exception e){

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setResultList(resultList,resultType);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_selection);
        //http://www.gosigmaway.com/api/ca_api/api.php?type=getCities&countryId=181&stateId=3067
        jsonLoader countryLoader = new jsonLoader();
        countryLoader.execute("http://www.gosigmaway.com/api/ca_api/api.php?type=getCountries","country");

    }

    private void setResultList(ArrayList<locationItem> countries, String  resultType){
        switch (resultType){
            case "country":{
                ArrayAdapter<locationItem> countryListAdapter = new ArrayAdapter<locationItem>(this,R.layout.support_simple_spinner_dropdown_item, countries);
                countryListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                countryListAdapter.notifyDataSetChanged();
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                spinner.setAdapter(countryListAdapter);
                spinner.setOnItemSelectedListener(this);
                break;}
            case "state":{
                ArrayAdapter<locationItem> countryListAdapter = new ArrayAdapter<locationItem>(this,R.layout.support_simple_spinner_dropdown_item, countries);
                countryListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                countryListAdapter.notifyDataSetChanged();
                Spinner spinner = (Spinner)findViewById(R.id.spinner2);
                spinner.setAdapter(countryListAdapter);
                spinner.setOnItemSelectedListener(this);
                break;}
            case "city":{
                ArrayAdapter<locationItem> countryListAdapter = new ArrayAdapter<locationItem>(this,R.layout.support_simple_spinner_dropdown_item, countries);
                countryListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                countryListAdapter.notifyDataSetChanged();
                Spinner spinner = (Spinner)findViewById(R.id.spinner3);
                spinner.setAdapter(countryListAdapter);
                spinner.setOnItemSelectedListener(this);
                break;}
        }
    }
}
