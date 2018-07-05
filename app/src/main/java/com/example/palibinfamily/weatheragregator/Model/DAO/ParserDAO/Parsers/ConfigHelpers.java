package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConfigHelpers {
    public static FullWeatherParserConfig getConfigForeca(){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();

        return new FullWeatherParserConfig(parameters);
    }

    public static FullWeatherParserConfig getConfigGismeteo(String url){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
        int day = 0;
        WeatherParserConfig config = new WeatherParserConfig();

        config = new WeatherParserConfig();
        if (url == null){
            config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        } else {
            config.setUrl("https://www.gismeteo.ru" + url + "10-days/");
        }
        config.setUrl("https://www.gismeteo.ru" + url + "10-days/");
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

//        ArrayList<WeatherParserConfig.WPCitem> classList = new ArrayList<>();

//        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"ip-info-entry__value",4));


        WeatherParserConfig config = new WeatherParserConfig();
        config.setUrl("https://2ip.ru/");
//        config.setPathItems(classList);
        config.setXpath("html>body>div>div:eq(1)>div:eq(4)>div:eq(1)>div>table>tbody>tr:eq(3)>td");
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

    public static FullWeatherParserConfig getConfigYandex(String url){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
        WeatherParserConfig config = new WeatherParserConfig();

        config = new WeatherParserConfig();
        if (url == null){
            config.setUrl("https://yandex.ru/pogoda/213");
        } else {
            config.setUrl("https://yandex.ru" + url);
        }
//        config.setUrl("https://www.gismeteo.ru" + url + "10-days/");
        //  html/body/div[1]/div[3]/div[2]/div/div[1]
        //  html/body/div[1]/div[3]/div[2]/div/div[1] /a/div[2]/span[2]
        // /html>body>div:eq(0)>div:eq(2)>div:eq(1)>div>div:eq(0)>             a>div:eq(1)>span:eq(1)
        config.setXpath("html>body>div:eq(0)>div:eq(2)>div:eq(1)>div>div:eq(0)>a>div:eq(3)>span:eq(1)");
        //                                                                  /// day
        parameters.put("temperature",config);

//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(9)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div");
//        parameters.put("humidity",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(0)");
//        parameters.put("windStrength",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(2)");
//        parameters.put("windDirection",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(8)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")>div:eq(0)");
//        parameters.put("pressure",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(0)>div>div>div:eq(0)>div>div:eq(1)>div:eq(" + day + ")>div>span");
//        parameters.put("weatherType",config);

        return new FullWeatherParserConfig(parameters);
    }

    public static FullWeatherParserConfig getConfigYandexForDayNum(int day){
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
        WeatherParserConfig config = new WeatherParserConfig();

        config = new WeatherParserConfig();
//        if (url == null){
//            config.setUrl("https://yandex.ru/pogoda/213");
//        } else {
//            config.setUrl("https://yandex.ru/pogoda/search?request=" + url + " 7 дней");
//        }
//        config.setUrl("https://www.gismeteo.ru" + url + "10-days/");
        //  html/body/div[1]/div[3]/div[2]/div/div[1]
        //  html/body/div[1]/div[3]/div[2]/div/div[1] /a/div[2]/span[2]
        // /html>body>div:eq(0)>div:eq(2)>div:eq(1)>div>div:eq(0)>             a>div:eq(1)>span:eq(1)
        config.setXpath("html>body>div:eq(0)>div:eq(2)>div:eq(1)>div>div:eq(" + day + ")>a>div:eq(3)>span:eq(0)");
        //                                                                  /// day
        parameters.put("temperature",config);

//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(9)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div");
//        parameters.put("humidity",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(0)");
//        parameters.put("windStrength",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(5)>div>div:eq(1)>div>div:eq(1)>div:eq(" + day + ")>div>div:eq(2)");
//        parameters.put("windDirection",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(8)>div>div:eq(1)>div>div:eq(1)>div>div>div>div:eq(" + day + ")>div:eq(0)");
//        parameters.put("pressure",config);
//
//        config = new WeatherParserConfig();
//        config.setXpath("html>body>section>div>div>div>div>div:eq(1)>div:eq(0)>div>div>div:eq(0)>div>div:eq(1)>div:eq(" + day + ")>div>span");
//        parameters.put("weatherType",config);

        return new FullWeatherParserConfig(parameters);
    }

}
