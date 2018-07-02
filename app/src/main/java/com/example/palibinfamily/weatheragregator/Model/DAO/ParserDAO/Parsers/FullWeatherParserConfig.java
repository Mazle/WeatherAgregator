package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FullWeatherParserConfig {
    private LinkedHashMap<String, WeatherParserConfig> parameters;
    private HashMap<String,Integer> windDirectionMap = new HashMap<>();

    public HashMap<String, Integer> getWindDirectionMap() {
        return windDirectionMap;
    }

    public void setWindDirectionMap(HashMap<String, Integer> windDirectionMap) {
        this.windDirectionMap = windDirectionMap;
    }

    public FullWeatherParserConfig(LinkedHashMap<String, WeatherParserConfig> parameters) {
        this.parameters = parameters;
    }

    public LinkedHashMap<String, WeatherParserConfig> getParameters() {
        return parameters;
    }

    public void setParameters(LinkedHashMap<String, WeatherParserConfig> parameters) {
        this.parameters = parameters;
    }
}
