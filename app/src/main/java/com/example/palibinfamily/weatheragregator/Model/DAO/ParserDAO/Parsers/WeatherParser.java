package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;


import android.util.Log;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherParser {
    private final String TAG = "WeatherParser";
    private FullWeatherParserConfig config;
    private Document doc = null;

    public WeatherParser(){

    }

    public WeatherParser(FullWeatherParserConfig config) {
        this.config = config;
    }

    public FullWeatherParserConfig getConfig() {
        return config;
    }

    public void setConfig(FullWeatherParserConfig config) {
        this.config = config;
    }

    public void getUrl(String url){
        if (url != null){
            try {
                doc = Jsoup.parse(new URL(url), 15000);
                Log.d(TAG, "getUrl: " + url);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    public List<Attribute> execXpathToAttributesList(String xpath){
        List<Attribute> result = null;
        if (xpath != null){
            Elements elements = doc.select(xpath);
            //System.out.println(elements.toString());
            result = elements.first().attributes().asList();
        }
        return result;
    }

    public String execXpathToString(String xpath){
        String result = null;
//        System.out.println(xpath + ":" + doc);
        if ((xpath != null)&&(doc != null)){
            Elements elements = doc.select(xpath);
//            System.out.println(xpath + ":" + elements.text());
            result = elements.text();
        }
        return result;
    }
//
//    public String execConfigElementString(WeatherParserConfig configElement){
//        String result = null;
//        if (configElement.getUrl() != null) {
//            try {
//                doc = Jsoup.parse(new URL(configElement.getUrl()), 15000);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        //TODO check null els & doc
//        Elements els = doc.body().getAllElements();
//
//        for(WeatherParserConfig.WPCitem item:configElement.getPathItems()){
//            int counterClassName = 0;
//            int counterId = 0;
//            int counterNum = 0;
//            boolean found = false;
//            for (Element el:els) {
//                switch (item.getParseType()){
//                    case className:{
//                        if (el.className().equals(item.getName())) {
//                            counterClassName++;
////                            System.out.println("" + counterClassName + ":" + item.getNum() + "[" + el.className() +"]");
//                            if ((item.getNum() < 0)||(counterClassName == item.getNum())) {
//                                els = el.getAllElements();
////                            System.out.println(el.className() + ":" + el.text() + "|" + el.toString());
////                                System.out.println("" + counterClassName + ":" + item.getNum() + "[" + el.className() +"]");
//                                found = true;
//                            }
//                        }
//
//                        break;}
//                    case num:{
//                        if (counterNum == item.getNum()){
//                            els = el.getAllElements();
////                            System.out.println("" + counter + ":" + el.text() + "|" + el.toString());
////                            System.out.println("" + counterNum + ":" + item.getNum() + "[" + el.className() +"]");
//                            found = true;
//                        }
//                        break; }
//                    case id:{
//                        if (el.id().equals(item.getName())) {
//                            counterId++;
//                            System.out.println("" + counterId + ":" + el.text() + "|" + el.toString());
//                            if ((item.getNum() < 0)||(counterId == item.getNum())) {
//                                els = el.getAllElements();
//                                found = true;
//                            }
//                        }
//                        break;}
//                }
//                counterNum++;
//            }
//            if (!found){
////                System.out.println("[ERROR]" + item.getName() + " not found");
////                Log.d(TAG,item.getName() + " not found");
//            }
//        }
////        int i = 0;
//        Element el2 = els.first();
//        try {
////                System.out.println("!" + Integer.parseInt(el2.text().replaceAll("°","")));
//
////                Log.d(TAG, "execConfigElementString: el2.toString " + el2.toString());
////                Log.d(TAG, "execConfigElementString: el2.text " + el2.text());
////                System.out.println("execConfigElementString: el2.toString" + el2.toString());
////                System.out.println("execConfigElementString: el2.text" + el2.text());
//            result = el2.text();
//        }catch (Exception e){
//
//        }
//        return result;
//    }
//
//
//    private Integer execConfigElement(WeatherParserConfig configElement){
//        Integer result = null;
//        if (configElement.getUrl() != null) {
//            try {
//                doc = Jsoup.parse(new URL(configElement.getUrl()), 15000);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        //TODO check null els & doc
//        Elements els = doc.body().getAllElements();
//
//        for(WeatherParserConfig.WPCitem item:configElement.getPathItems()){
//            int counterClassName = 0;
//            int counterNum = 0;
//            boolean found = false;
//            for (Element el:els) {
//                switch (item.getParseType()){
//                    case className:{
//                        if (el.className().equals(item.getName())) {
//                            counterClassName++;
////                            System.out.println("" + counterClassName + ":" + item.getNum() + "[" + el.className() +"]");
//                            if ((item.getNum() < 0)||(counterClassName == item.getNum())) {
//                                els = el.getAllElements();
////                            System.out.println(el.className() + ":" + el.text() + "|" + el.toString());
////                                System.out.println("" + counterClassName + ":" + item.getNum() + "[" + el.className() +"]");
//                                found = true;
//                            }
//                        }
//
//                        break;}
//                    case num:{
//                        if (counterNum == item.getNum()){
//                            els = el.getAllElements();
////                            System.out.println("" + counter + ":" + el.text() + "|" + el.toString());
////                            System.out.println("" + counterNum + ":" + item.getNum() + "[" + el.className() +"]");
//                            found = true;
//                        }
//                        break; }
//                }
//                counterNum++;
//            }
//            if (!found){
////                System.out.println("[ERROR]" + item.getName() + " not found");
////                Log.d(TAG,item.getName() + " not found");
//            }
//        }
//        int i = 0;
//        for (Element el2:els){
//            try {
////                System.out.println("!" + Integer.parseInt(el2.text().replaceAll("°","")));
//                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
//                Matcher matcher = pat.matcher(el2.text());
//                if (matcher.find()) {
//                    result = (int)(Float.parseFloat(matcher.group()));
//                };
//
//            }catch (Exception e){
//
//            }
//            i++;
//        }
//        return result;
//    }
    private WeatherSnapshot getZeroSnapshot(){
        WeatherSnapshot result = new WeatherSnapshot();
        result.setSnowing(false);
        result.setRaining(false);
        result.setWindDirection("");
        result.setTemperature(Integer.MAX_VALUE);
        result.setHumidity(Integer.MAX_VALUE);
        result.setPressure(Integer.MAX_VALUE);
        result.setWindSpeed(Integer.MAX_VALUE);
        return result;
    }

    //@Override
    public WeatherSnapshot getWeather() {
        WeatherSnapshot result = getZeroSnapshot();
        String text;

        for (Map.Entry<String , WeatherParserConfig> configElement : config.getParameters().entrySet()){
//            System.out.println(configElement.getKey() + ":" + configElement.getValue().getUrl());
            Log.d(TAG,configElement.getKey() + ":" + configElement.getValue().getUrl());
            if (configElement.getValue().getUrl() != null){
                getUrl(configElement.getValue().getUrl());
            }
            String execResult;
            switch (configElement.getKey()){
                case "temperature":{
//                    System.out.println(configElement.getValue().getXpath());
                    execResult = execXpathToString(configElement.getValue().getXpath());
                    if (execResult != null) {
                        try {
                            result.setTemperature(Integer.parseInt(execResult));
                        }catch (Exception e){

                        }
                    }
                    break;}
                case "humidity":{
                    execResult = execXpathToString(configElement.getValue().getXpath());
                    if (execResult != null) {
                        try {
                            result.setHumidity(Integer.parseInt(execResult));
                        }catch (Exception e){

                        }
                    }
                    break;}
                case "windStrength":{
                    execResult = execXpathToString(configElement.getValue().getXpath());
                    if (execResult != null) {
                        try {
                            result.setWindSpeed(Integer.parseInt(execResult));
                        }catch (Exception e){

                        }
                    }
                    break;}
                case "windDirection":{
                    execResult = execXpathToString(configElement.getValue().getXpath());
                    if (execResult != null) {
                        result.setWindDirection(execResult);
//                        Log.d(TAG, "getWeather windDirection: " + text);
                    }
                    break;}
                case "weatherType":{
                    List<Attribute> attributes = execXpathToAttributesList(configElement.getValue().getXpath());
                    if (attributes != null) {
                        execResult = attributes.get(1).toString();
                        if (execResult != null) {
                            result.setRaining(false);
                            result.setSnowing(false);

                            if (execResult.contains("гроза")) {
                                result.setRaining(true);
                            }

//                        result.setWindDirection(text);
                            Log.d(TAG, "getWeather weatherType: " + execResult);
                        }
                    }
                    break;}
                case "pressure":{
                    execResult = execXpathToString(configElement.getValue().getXpath());
                    if (execResult != null) {
                        try {
                            result.setPressure(Integer.parseInt(execResult));
                        }catch (Exception e){

                        }
                    }
                    break;}
                default:{
                    break;}
            }
        }
        return result;
    }
}
