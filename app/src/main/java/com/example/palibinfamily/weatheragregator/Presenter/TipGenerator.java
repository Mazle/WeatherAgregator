package com.example.palibinfamily.weatheragregator.Presenter;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;

import java.util.ArrayList;
import java.util.Random;

public class TipGenerator {
    public static class filterItem{
        private int minTemperature;
        private int maxTemperarure;
        private String text;

        public filterItem(int minTemperature, int maxTemperarure, String text) {
            this.minTemperature = minTemperature;
            this.maxTemperarure = maxTemperarure;
            this.text = text;
        }

        public int getMinTemperature() {
            return minTemperature;
        }

        public int getMaxTemperarure() {
            return maxTemperarure;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static ArrayList<filterItem> getAllTips(){
        ArrayList<filterItem> result = new ArrayList<>();
        result.add(new filterItem(-100,100,"Не выспался?\nВозьми подушку с собой"));
        result.add(new filterItem(-100,100,"Заряди телефон"));
        result.add(new filterItem(-100,100,"Время чая"));
        result.add(new filterItem(-100,100,"А вы сегодня улыбались?"));
        result.add(new filterItem(25,100,"Что взять на пляж?"));
        result.add(new filterItem(-100,100,"Наслаждайтесь каждым\nмгновеньем"));
        result.add(new filterItem(-100,100,"Время чая"));
        result.add(new filterItem(20,100,"Езжай на пикник"));
        result.add(new filterItem(-100,15,"Похолодало,\nвозьми с собой кота"));
        result.add(new filterItem(18,100,"Покажи себя морю"));
        result.add(new filterItem(-100,100,"Плохой день?\n Возьми с собой бобра"));
        result.add(new filterItem(-100,100,"Грустно? Заведи корову"));
        result.add(new filterItem(-100,17,"Ночью будет холодно,\nспите в обнимку"));


        return result;
    }
//    весна
//    утро
//    доброе утро!
//    не забудьте зонт
//    не выспался, возьми подушку с собой
//    возьми с собой бутер
//    день
//    летний ливень застал тебя в расплох? возьми зонт
//    начни с себя
//    вечер
//    заряди телефон
//    время чая
//    а вы сегодня улыбались?
//    ночь
//    в гостях хорошо, а дома лучше
//
//    лето
//    утро
//    открывай глаза
//    улыбнись, глаз от тебя не оторвать
//    больше солнца, больше ласки
//    пора вставать
//    возьми очки
//
//    день
//    берегите сердце
//    что взять на пляж?
//    езжай на пикник
//    возьми очки
//
//    вечер
//    радуйтесь каждому дню
//    наслаждайтесь каждым мгновеньем
//
//    ночь
//    жизнь прекрасна
//
//    осень
//    утро
//    время чая
//    похолодало, возьми с собой кота
//    покажи себя морю
//    пора вставать
//
//    день
//    плохой день? возьми с собой бобра)
//    переходи на зеленый
//    не грусти на работе
//
//    вечер
//    советуем время провести с семьей
//    возьмите отпуск
//
//    ночь
//    берегите себя
//    любите
//
//    зима
//    утро
//    возьми кофе с собой
//    жизнь прекрасна!
//    Береги себя
//    любите
//    заряди телефон
//
//    день
//    не знаешь, как пережить этот день, котик тебе поможет
//    будь оптимистом
//    живите сейчас
//
//    вечер
//    грусто? Заведи корову
//    не расти усы, расти мышцы!
//    Заболел?
//
//    ночь
//    ночью будет холодно, спите в обнимку
//    крепких снов

    public String getTip(WeatherSnapshot snapshot){
        ArrayList<String> matchedTips = new ArrayList<>();
        for (filterItem item:getAllTips()){
            if ((snapshot.getTemperature() > item.getMinTemperature())&&
                    (snapshot.getTemperature() < item.getMaxTemperarure())){
                matchedTips.add(item.text);
            }
        }
        String result = "";
        Random random = new Random();
        try{
            result = matchedTips.get(random.nextInt(matchedTips.size()));
        }catch (Exception e){

        }
        return result;
    };
}
