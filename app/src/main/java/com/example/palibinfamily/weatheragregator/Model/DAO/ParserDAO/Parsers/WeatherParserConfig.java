package com.example.palibinfamily.weatheragregator.Model.DAO.ParserDAO.Parsers;

import java.util.List;

public class WeatherParserConfig {
    public enum ParseType{
        num,
        className
    }

    public enum ResultType{
        number,
        windDirection
    }

    public static class WPCitem{
        private ParseType parseType;
        private String name;
        private int num;

        public ParseType getParseType() {
            return parseType;
        }

        public String getName() {
            return name;
        }

        public int getNum() {
            return num;
        }

        public WPCitem(ParseType parseType, String name, int num) {
            this.parseType = parseType;
            this.name = name;
            this.num = num;
        }

        public WPCitem(ParseType parseType, String name) {
            this.parseType = parseType;
            this.name = name;
            this.num = -1;
        }

        public WPCitem(ParseType parseType, int num) {
            this.parseType = parseType;
            this.name = null;
            this.num = num;
        }
    }

    private String url;

    private List<WPCitem> pathItems;

    public WeatherParserConfig() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WPCitem> getPathItems() {
        return pathItems;
    }

    public void setPathItems(List<WPCitem> pathItems) {
        this.pathItems = pathItems;
    }
}
