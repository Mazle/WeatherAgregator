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
        ArrayList<WeatherParserConfig.WPCitem> classList = new ArrayList<>();

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",2));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row",2));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__chart w_temperature-avg"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"chart chart__temperature"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"values"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,1)); // номер дня

        WeatherParserConfig config = new WeatherParserConfig();
        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        config.setPathItems(classList);
        parameters.put("temperature",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",6));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",1)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,1));

        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("humidity",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",3));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",1)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"w_wind"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("windStrength",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",3));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",1)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,7));
//        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"w_wind"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("windDirection",config);


        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",5));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row",2));

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"js_pressure pressureline w_pressure"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"chart chart__pressure"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"values"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"value",1));// номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"maxt"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("pressure",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"forecast_frame"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",1));// номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__value w_icon"));
//        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"tooltip"));

        config = new WeatherParserConfig();
//        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        config.setPathItems(classList);
        parameters.put("weatherType",config);

        FullWeatherParserConfig result = new FullWeatherParserConfig(parameters);
//        result.getWindDirectionMap().put("С",0);
//        result.getWindDirectionMap().put("СВ",1);
//        result.getWindDirectionMap().put("В",2);
//        result.getWindDirectionMap().put("ЮВ",3);
//        result.getWindDirectionMap().put("Ю",4);
//        result.getWindDirectionMap().put("ЮЗ",5);
//        result.getWindDirectionMap().put("З",6);
//        result.getWindDirectionMap().put("СЗ",7);
        return result;
    }

    public static FullWeatherParserConfig getConfigGismeteoForDayNum(int day){
        int dayNum = day + 1;
        LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
        ArrayList<WeatherParserConfig.WPCitem> classList = new ArrayList<>();

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",2));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row",2));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__chart w_temperature-avg"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"chart chart__temperature"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"values"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,dayNum)); // номер дня

        WeatherParserConfig config = new WeatherParserConfig();
//        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        config.setPathItems(classList);
        parameters.put("temperature",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",6));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",dayNum)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,1));

        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("humidity",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",3));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",dayNum)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"w_wind"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("windStrength",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",3));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",dayNum)); // номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.num,7));
//        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"w_wind"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("windDirection",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame",5));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row",2));

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"js_pressure pressureline w_pressure"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"chart chart__pressure"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"values"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"value",dayNum));// номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"maxt"));


        config = new WeatherParserConfig();
        config.setPathItems(classList);
        parameters.put("pressure",config);

        classList = new ArrayList<>();
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"content"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"flexbox clearfix"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"main"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"column-wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"__frame_sm"));

        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"forecast_frame"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__wrap"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget js_widget"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__body"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__container"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__row widget__row_table",1));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__item",dayNum));// номер дня
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"widget__value w_icon"));
        classList.add(new WeatherParserConfig.WPCitem(WeatherParserConfig.ParseType.className,"tooltip"));

        config = new WeatherParserConfig();
//        config.setUrl("https://www.gismeteo.ru/weather-moscow-4368/10-days/");
        config.setPathItems(classList);
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

}
