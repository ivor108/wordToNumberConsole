package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static final String PATH = "D:\\dict\\";

    public static String[] rankSplit(String string){
        if(!string.matches("\\d+"))
            return null;
        int targetLength = string.length();
        if (string.length()%3 != 0)
            targetLength = (string.length()/3 + 1)*3;

        String formatString = String.format("%0" + targetLength + "d", Long.parseLong(string));
        return formatString.split("(?<=\\G...)");
    }

    public static String[] rankSplit2(String string){
        String[] ranks = new String[4];
        String[] ranksName = new String[3];
        ranksName[0] = "миллиард";
        ranksName[1] = "миллион";
        ranksName[2] = "тысяч";
        Arrays.fill(ranks, "");

        for (int i = 0; i < 3; i++) {
            if(string.matches(".*" + ranksName[i] + ".*")){
                String[] tmp = string.split(" " + ranksName[i] + ".*?\\s");
                ranks[i] = tmp[0];
                if(tmp.length > 1)
                    string = tmp[1];
                else
                    string = "";
            }
        }

        ranks[3] = string;
        return ranks;
        //return string.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s| единиц.*?\\s)");
    }


    public static void main(String[] args) throws IOException {
        /*
        RuNumberTranslator numberTranslator = new RuNumberTranslator(Languages.RU, PATH);
        RuWordTranslator wordTranslator = new RuWordTranslator(Languages.RU, PATH);

        String textFrom = "102102";
        System.out.println(numberTranslator.translate(textFrom));
        System.out.println(Arrays.toString(rankSplit(textFrom)));

        String textFrom2 = numberTranslator.translate(textFrom);
        System.out.println(Arrays.toString(rankSplit2(textFrom2)));
        //System.out.println(textFrom2.matches(".*тысяч.*"));
        //System.out.println(Arrays.toString(textFrom2.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s)")));
        System.out.println(wordTranslator.translate(textFrom2));
         */

        /*
        TranslatorContext translator = new TranslatorContext(TranslatorStrategy.RU);
        System.out.println(translator.numberTranslate("123"));
        System.out.println(translator.wordTranslate("сто двадцать три"));
         */



    }
}
