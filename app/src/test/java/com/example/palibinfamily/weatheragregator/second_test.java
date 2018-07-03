package com.example.palibinfamily.weatheragregator;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.ConfigHelpers;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.FullWeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;
import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParserConfig;
import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)


public class second_test {

    WeatherParserConfig config;
    FullWeatherParserConfig fullConfig;
    LinkedHashMap<String, WeatherParserConfig> parameters = new LinkedHashMap<>();
    Elements elements;
    Elements elements2;


    @Mock
    Document doc;

    @InjectMocks
    WeatherParser parser;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        config = new WeatherParserConfig();
        config.setXpath("html>div");
        parameters.put("temperature",config);

        config = new WeatherParserConfig();
        config.setXpath("html>div>div");
        parameters.put("pressure",config);

        fullConfig = new FullWeatherParserConfig(parameters);

        elements = new Elements();
        Element firstElement = new Element("div");
        firstElement.append("123");
        elements.add(firstElement);

        elements2 = new Elements();
        firstElement = new Element("div");
        firstElement.append("1000");
        elements2.add(firstElement);

        parser.setConfig(fullConfig);
    }

    @Test
    public void test() throws IOException {

        when(doc.select("html>div")).thenReturn(elements);
        when(doc.select("html>div>div")).thenReturn(elements2);

        WeatherSnapshot snap =  parser.getWeather();
        assertEquals(123, snap.getTemperature());
        assertEquals(1000, snap.getPressure());
    }
}
