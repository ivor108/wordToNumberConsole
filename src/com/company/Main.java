package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static final String PATH = "C:\\Users\\pepega\\IdeaProjects\\wordToNumberConsole\\src\\";

    public static String[] rankSplit(String string){
        int targetLength = string.length();
        if (string.length()%3 != 0)
            targetLength = (string.length()/3 + 1)*3;

        String formatString = String.format("%0" + targetLength + "d", Long.parseLong(string));
        return formatString.split("(?<=\\G...)");
    }

    public static void main(String[] args) throws IOException {
        NumberTranslator numberTranslator = new NumberTranslator(Languages.RU, PATH);
        WordTranslator wordTranslator = new WordTranslator(Languages.RU, PATH);

        String textFrom = "123123123";
        System.out.println(numberTranslator.translate(textFrom));
        System.out.println(Arrays.toString(rankSplit(textFrom)));

        String textFrom2 = numberTranslator.translate(textFrom);
        //System.out.println(wordTranslator.translate(textFrom2));
        //System.out.println(Arrays.toString(textFrom2.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s)")));
        //System.out.println(wordTranslator.simpleTranslate("сто двадцать три"));
        System.out.println(wordTranslator.translate(textFrom2));
    }
}
