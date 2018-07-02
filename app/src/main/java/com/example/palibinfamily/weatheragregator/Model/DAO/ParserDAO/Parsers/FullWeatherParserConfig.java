package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FullWeatherParserConfig {
    private LinkedHashMap<String, WeatherParserConfig> parameters;

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
