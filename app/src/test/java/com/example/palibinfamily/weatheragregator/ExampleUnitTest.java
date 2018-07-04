package com.example.palibinfamily.weatheragregator;

import com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers.WeatherParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)


public class ExampleUnitTest {

    @Mock
    WeatherParser parser;

    @Before
    public void setUp() {

    }

    @Test
    public void fake_fake_test() {
        when(parser.execXpathToString("123")).thenReturn("test_loc");
        assertEquals("test_loc", parser.execXpathToString("123"));
    }
}
