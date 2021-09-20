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
        /*
        ranksName[0] = "миллиард";
        ranksName[1] = "миллион";
        ranksName[2] = "тысяч";
         */
        ranksName[0] = "billion";
        ranksName[1] = "million";
        ranksName[2] = "thousand";
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
    }


    public static void main(String[] args) throws IOException {

        Translator tr =  TranslatorStrategy.valueOf("RU").getTranslator();
        System.out.println(tr.translateNumber("1"));
        System.out.println(tr.translateWord("сто двадцать три"));

        EnTranslator enTranslator = new EnTranslator();
        String textTo = enTranslator.translateNumber("1113123123");
        System.out.println(textTo);
        System.out.println(enTranslator.translateWord(textTo));
        //System.out.println(Arrays.toString("one hundred twenty three".split(" hundred ")));

    }
}
