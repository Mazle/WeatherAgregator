package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConfigHelpers {
    public static FullWeatherParserConfig getConfigForeca(){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();

        return new FullWeatherParserConfig(parameters);
    }

    public static FullWeatherParserConfig getConfigGismeteo(){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
        int day = 0;
        WeatherParserConfig config = new WeatherParserConfig();

        config = new WeatherParserConfig();
        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(4)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")");
        parameters.put("temperature",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(9)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div");
        parameters.put("humidity",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(0)");
        parameters.put("windStrength",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(2)");
        parameters.put("windDirection",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(8)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")>div:eq(0)");
        parameters.put("pressure",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(0)>div>div>div:eq(0)>div>div:eq(1)>div:eq(" + day + ")>div>span");
        parameters.put("weatherType",config);

        return new FullWeatherParserConfig(parameters);
    }

    public static FullWeatherParserConfig getConfigGismeteoForDayNum(int day){
        int dayNum = day + 1;
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();


        WeatherParserConfig config = new WeatherParserConfig();
//        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(4)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")");
        parameters.put("temperature",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(9)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div");
        parameters.put("humidity",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(0)");
        parameters.put("windStrength",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(2)");
        parameters.put("windDirection",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(8)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")>div:eq(0)");
        parameters.put("pressure",config);

        config = new WeatherParserConfig();
        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(0)>div>div>div:eq(0)>div>div:eq(1)>div:eq(" + day + ")>div>span");
        parameters.put("weatherType",config);

        return new FullWeatherParserConfig(parameters);
    }

    public static WeatherParserConfig getConfig2ip(){

        ArrayList<WeatherParserConfig.WPCitem> classList = new ArrayList<>();

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"ip-info-entry__value",4));


        WeatherParserConfig config = new WeatherParserConfig();
        config.setUrl("https://2ip.ru/");
        config.setPathItems(classList);

        return config;
    }

    public static WeatherParserConfig getConfigip2location(){

        ArrayList<WeatherParserConfig.WPCitem> classList = new ArrayList<>();

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.id,"cityName",4));


        WeatherParserConfig config = new WeatherParserConfig();
        config.setUrl("https://www.ip2location.com/");
        config.setPathItems(classList);

        return config;
    }

}
